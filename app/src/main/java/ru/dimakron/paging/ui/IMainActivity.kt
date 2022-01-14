package ru.dimakron.paging.ui

import androidx.paging.PagingData
import io.reactivex.Flowable
import moxy.MvpView

interface IMainActivity: MvpView {

    fun initDigitsPaging(flowable: Flowable<PagingData<Int>>)

}