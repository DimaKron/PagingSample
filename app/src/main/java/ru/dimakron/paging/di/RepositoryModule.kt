package ru.dimakron.paging.di

import org.koin.dsl.module
import ru.dimakron.paging.data.ApiService
import ru.dimakron.paging.data.DigitsRepository

val dataModule = module {

    single { ApiService() }

    single { DigitsRepository(get()) }

}