package uz.texnopos.nasya.presentation.main.calc

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import uz.texnopos.nasya.R
import uz.texnopos.nasya.databinding.CalcDialogBinding
import uz.texnopos.nasya.domain.models.Amount
import uz.texnopos.nasya.utils.MinMaxFilter
import uz.texnopos.nasya.utils.changeFormat
import uz.texnopos.nasya.utils.checkIsEmpty
import uz.texnopos.nasya.utils.mask.MaskWatcherPrice
import uz.texnopos.nasya.utils.onClick

class CalculatorDialog : DialogFragment() {

    private lateinit var binding: CalcDialogBinding
    private val amount = MediatorLiveData<Amount>().apply { value = Amount() }
    private var livePrice = MutableLiveData<Double>()
    private var liveFirstPay = MutableLiveData<Double>()
    private var livePercent = MutableLiveData<Int>()
    private var liveMonth = MutableLiveData<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        dialog!!.setCanceledOnTouchOutside(false)
        return inflater.inflate(R.layout.calc_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CalcDialogBinding.bind(view)
        binding.apply {
            etPrice.addTextChangedListener(MaskWatcherPrice(etPrice))
            etFirstPay.addTextChangedListener(MaskWatcherPrice(etFirstPay))
            etMonth.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
            etPercent.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
            btnClose.onClick {
                dialog!!.dismiss()
            }
            merge()
            observe()
            etPrice.doOnTextChanged { it, _, _, _ ->
                livePrice.apply {
                    if (it.isNullOrEmpty()) postValue(0.0)
                    else postValue(it.toString().getOnlyDigits().toDouble())
                }
            }
            etFirstPay.doOnTextChanged { it, _, _, _ ->
                liveFirstPay.apply {
                    if (it.isNullOrEmpty()) postValue(0.0)
                    else postValue(it.toString().getOnlyDigits().toDouble())
                }
            }
            etPercent.doAfterTextChanged {
                livePercent.apply {
                    if (it.isNullOrEmpty()) postValue(0)
                    else postValue(it.getOnlyDigits().toInt())
                }

            }
            etMonth.doAfterTextChanged {
                liveMonth.apply {
                    if (it.isNullOrEmpty()) postValue(0)
                    else postValue(it.getOnlyDigits().toInt())
                }
            }
        }
    }

    private fun merge() {
        amount.addSource(livePrice) {
            val previous = amount.value
            amount.value = previous?.copy(productPrice = it)
        }
        amount.addSource(liveFirstPay) {
            val previous = amount.value
            amount.value = previous?.copy(firstPay = it)
        }
        amount.addSource(livePercent) {
            val previous = amount.value
            amount.value = previous?.copy(percent = it)
        }
        amount.addSource(liveMonth) {
            val previous = amount.value
            amount.value = previous?.copy(month = it)
        }
    }

    private fun observe() {
        amount.observe(requireActivity()) {
            binding.apply {
                val productPrice = it.productPrice
                val firstPay = it.firstPay
                val remain = (productPrice - firstPay)
                val added = (it.percent * it.month).toDouble() / 100 + 1
                val result = (remain * added / it.month).toLong()

                if (remain >= 0 && validate()) tvResultAmount.text =
                    getString(R.string.result_amount, result.changeFormat())
                if (remain > 0) tvResultAllDebt.text =
                    getString(R.string.result_general, (remain * added + firstPay).changeFormat())

                tvResultAmount.isInvisible = !(remain >= 0 && validate())
                tvResultAllDebt.isVisible = remain > 0
            }
        }
    }

    private fun CharSequence.getOnlyDigits(): String {
        var s = ""
        this.forEach { if (it.isDigit()) s += it }
        return if (s == "") "0"
        else s
    }

    private fun validate(): Boolean {
        return (!binding.etPrice.checkIsEmpty()
                && !binding.etMonth.checkIsEmpty())
    }
}