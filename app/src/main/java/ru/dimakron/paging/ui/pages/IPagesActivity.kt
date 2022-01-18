package ru.dimakron.paging.ui.pages

import moxy.MvpView
import ru.dimakron.paging.model.LoadingState

interface IPagesActivity: MvpView {

    fun showLoadingState(loadingState: LoadingState)

    fun showDigits(items: List<Int>, hasMore: Boolean)

}