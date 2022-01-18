package ru.dimakron.paging.di

import org.koin.dsl.module
import ru.dimakron.paging.ui.pages.PagesPresenter
import ru.dimakron.paging.ui.paginate.PaginatePresenter
import ru.dimakron.paging.ui.paging3.Paging3Presenter

val presenterModule = module {

    factory { Paging3Presenter(get()) }
    factory { PaginatePresenter(get()) }
    factory { PagesPresenter(get()) }

}