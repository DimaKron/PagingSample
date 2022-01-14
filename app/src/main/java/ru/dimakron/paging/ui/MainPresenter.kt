package ru.dimakron.paging.ui

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.dimakron.paging.data.DigitsRepository
import ru.dimakron.paging.putIn

class MainPresenter(private val digitsRepository: DigitsRepository): MvpPresenter<IMainActivity>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        loadDigits()
    }

    private fun loadDigits(){
        digitsRepository.getDigits(0, 30)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(viewState::showDigits)
            .putIn(disposables)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}