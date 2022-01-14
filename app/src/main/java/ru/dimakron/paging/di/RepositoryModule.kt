package ru.dimakron.paging.di

import org.koin.dsl.module
import ru.dimakron.paging.data.DigitsRepository

val repositoryModule = module {

    single { DigitsRepository() }

}