package uz.texnopos.nasya.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.texnopos.nasya.presentation.main.addCategory.usecase.AddCategoryFragmentUC
import uz.texnopos.nasya.presentation.main.addCategory.usecase.AddCategoryFragmentUCImpl
import uz.texnopos.nasya.presentation.main.addClient.usecase.AddClientFragmentUC
import uz.texnopos.nasya.presentation.main.addClient.usecase.AddClientFragmentUCImpl
import uz.texnopos.nasya.presentation.main.addOrder.usecase.AddOrderFragmentUC
import uz.texnopos.nasya.presentation.main.addOrder.usecase.AddOrderFragmentUCImpl
import uz.texnopos.nasya.presentation.main.addProduct.usecase.AddProductFragmentUC
import uz.texnopos.nasya.presentation.main.addProduct.usecase.AddProductFragmentUCImpl
import uz.texnopos.nasya.presentation.main.clients.usecase.ClientsFragmentUC
import uz.texnopos.nasya.presentation.main.clients.usecase.ClientsFragmentUCImpl
import uz.texnopos.nasya.presentation.main.orders.usecase.OrdersFragmentUC
import uz.texnopos.nasya.presentation.main.orders.usecase.OrdersFragmentUCImpl
import uz.texnopos.nasya.presentation.main.payment.usecase.PaymentFragmentUC
import uz.texnopos.nasya.presentation.main.payment.usecase.PaymentFragmentUCImpl
import uz.texnopos.nasya.presentation.main.products.usecase.ProductsFragmentUC
import uz.texnopos.nasya.presentation.main.products.usecase.ProductsFragmentUCImpl
import uz.texnopos.nasya.presentation.main.transactions.usecase.TransactionsFragmentUC
import uz.texnopos.nasya.presentation.main.transactions.usecase.TransactionsFragmentUCImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun provideClientsFragmentUseCase(impl: ClientsFragmentUCImpl): ClientsFragmentUC

    @Binds
    fun provideAddClientFragmentUseCase(impl: AddClientFragmentUCImpl): AddClientFragmentUC

    @Binds
    fun provideOrdersFragmentUseCase(impl: OrdersFragmentUCImpl): OrdersFragmentUC

    @Binds
    fun provideAddCategoryFragmentUseCase(impl: AddCategoryFragmentUCImpl): AddCategoryFragmentUC

    @Binds
    fun provideProductsFragmentUseCase(impl: ProductsFragmentUCImpl): ProductsFragmentUC

    @Binds
    fun provideAddProductFragmentUseCase(impl: AddProductFragmentUCImpl): AddProductFragmentUC

    @Binds
    fun provideTransactionsFragmentUseCase(impl: TransactionsFragmentUCImpl): TransactionsFragmentUC

    @Binds
    fun providePaymentFragmentUseCase(impl: PaymentFragmentUCImpl): PaymentFragmentUC

    @Binds
    fun provideAddOrderFragmentUseCase(impl: AddOrderFragmentUCImpl): AddOrderFragmentUC
}

