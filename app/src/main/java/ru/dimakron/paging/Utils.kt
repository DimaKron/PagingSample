package ru.dimakron.paging

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.putIn(disposables: CompositeDisposable) =
    disposables.add(this)