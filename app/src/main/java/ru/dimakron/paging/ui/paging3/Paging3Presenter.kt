package ru.dimakron.paging.ui.paging3

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.flowable
import moxy.MvpPresenter
import ru.dimakron.paging.data.DigitsRepository

class Paging3Presenter(private val digitsRepository: DigitsRepository): MvpPresenter<IPaging3Activity>() {

    private val pagingFlowable = Pager(PagingConfig(10), pagingSourceFactory = { digitsRepository.createPagingSource() })
        .flowable
        //.cachedIn(viewModelScope) TODO Не получается кешировать прогресс пагинации

    override fun onFirstViewAttach() {
        viewState.initDigitsPaging(pagingFlowable)
    }
}