package ru.dimakron.paging.di

import org.koin.dsl.module
import ru.dimakron.paging.ui.MainPresenter

val presenterModule = module {

    factory { MainPresenter(get()) }

}