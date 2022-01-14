package ru.dimakron.paging.ui

import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

class MainPresenter: MvpPresenter<IMainActivity>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        loadDigits()
    }

    private fun loadDigits(){
        viewState.showDigits(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}