package ru.dimakron.paging.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.flowable
import moxy.MvpPresenter
import ru.dimakron.paging.data.DigitsRepository

class MainPresenter(private val digitsRepository: DigitsRepository): MvpPresenter<IMainActivity>() {

    private val pagingFlowable = Pager(PagingConfig(10), pagingSourceFactory = { digitsRepository.createPagingSource() })
        .flowable

    override fun onFirstViewAttach() {
        viewState.initDigitsPaging(pagingFlowable)
    }
}