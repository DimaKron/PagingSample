package ru.dimakron.paging.ui.pages

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.dimakron.paging.data.DigitsRepository
import ru.dimakron.paging.model.Digits
import ru.dimakron.paging.model.LoadingState
import ru.dimakron.paging.putIn

class PagesPresenter(private val digitsRepository: DigitsRepository): MvpPresenter<IPagesActivity>() {

    private val disposables = CompositeDisposable()

    private val items = mutableListOf<Int>()
    private var hasMore = true

    fun processLoadMore(){
        if (!hasMore) {
            return
        }

        val lastDigit = items.lastOrNull()

        viewState.showLoadingState(LoadingState.LOADING)
        digitsRepository.getDigits(lastDigit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ onLoadingSuccess(it, lastDigit == null) }, this::onLoadingError)
            .putIn(disposables)
    }

    private fun onLoadingSuccess(result: Digits, isReload: Boolean){
        if(isReload){
            items.clear()
        }
        items.addAll(result.items)
        hasMore = result.hasMore
        viewState.showDigits(items, hasMore)
        viewState.showLoadingState(LoadingState.NONE)
    }

    private fun onLoadingError(throwable: Throwable){
        viewState.showLoadingState(LoadingState.ERROR)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}