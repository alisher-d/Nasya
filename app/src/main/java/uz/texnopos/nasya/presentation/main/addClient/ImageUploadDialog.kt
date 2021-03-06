package uz.texnopos.nasya.presentation.main.addClient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.texnopos.nasya.R
import uz.texnopos.nasya.databinding.DialogImageUploadBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.texnopos.nasya.utils.onClick


class ImageUploadDialog(private val mFragment: AddClientFragment) : BottomSheetDialogFragment() {

    private lateinit var binding: DialogImageUploadBinding
    private var savedViewInstance: View? = null

    init {
        show(mFragment.requireActivity().supportFragmentManager, "tag")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return if (savedInstanceState != null) {
            savedViewInstance
        } else {
            savedViewInstance =
                inflater.inflate(R.layout.dialog_image_upload, container, true)
            savedViewInstance
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogImageUploadBinding.bind(view)

        binding.apply {
            tvFromCamera.onClick {
                selectCamera()
                dismiss()
            }

            tvFromGallery.onClick {
                selectGallery()
                dismiss()
            }
        }
    }

    private var selectCamera: () -> Unit = {}
    private var selectGallery: () -> Unit = {}

    fun onCameraSelected(selectCamera: () -> Unit) {
        this.selectCamera = selectCamera
    }

    fun onGallerySelected(selectGallery: () -> Unit) {
        this.selectGallery = selectGallery
    }
}

