package ru.dimakron.paging.ui.paging3

import androidx.paging.PagingData
import io.reactivex.Flowable
import moxy.MvpView

interface IPaging3Activity: MvpView {

    fun initDigitsPaging(flowable: Flowable<PagingData<Int>>)

}