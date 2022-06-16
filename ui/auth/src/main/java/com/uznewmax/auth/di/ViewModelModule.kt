package com.uznewmax.auth.di

import com.uznewmax.auth.usecase.validation.ValidateMail
import com.uznewmax.auth.usecase.validation.ValidatePassword
import com.uznewmax.auth.usecase.utils.MailMatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

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