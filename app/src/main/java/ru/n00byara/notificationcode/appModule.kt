package ru.n00byara.notificationcode

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.n00byara.notificationcode.settings.Settings
import ru.n00byara.notificationcode.ui.viewmodels.ApplicationsScreenViewModel
import ru.n00byara.notificationcode.ui.viewmodels.SettingsActivityViewModel
import ru.n00byara.notificationcode.ui.viewmodels.SettingsScreenViewModel

val appModule = module {
    single { Settings() }

    viewModel { SettingsActivityViewModel(get()) }
    viewModel { SettingsScreenViewModel(get()) }
    viewModel { ApplicationsScreenViewModel(get()) }
}