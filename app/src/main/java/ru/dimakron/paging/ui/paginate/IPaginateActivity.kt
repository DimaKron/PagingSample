package ru.dimakron.paging.ui.paginate

import moxy.MvpView
import ru.dimakron.paging.model.LoadingState

interface IPaginateActivity: MvpView {

    fun showLoadingState(loadingState: LoadingState)

    fun showDigits(items: List<Int>, hasMore: Boolean)

}