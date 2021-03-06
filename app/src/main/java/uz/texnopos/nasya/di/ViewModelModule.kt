package uz.texnopos.nasya.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.texnopos.nasya.domain.usecase.util.MailMatcher
import uz.texnopos.nasya.domain.usecase.validation.ValidateMail
import uz.texnopos.nasya.domain.usecase.validation.ValidatePassword

/**
 * Created by Alisher Kazakbaev on 06.06.2022.
 */

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideValidateMail(mailMatcher: MailMatcher): ValidateMail = ValidateMail(mailMatcher)

    @Provides
    @ViewModelScoped
    fun provideValidatePassword(): ValidatePassword = ValidatePassword()
}