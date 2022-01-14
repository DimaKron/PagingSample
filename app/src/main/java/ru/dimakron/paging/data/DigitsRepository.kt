package ru.dimakron.paging.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DigitsRepository {

    fun getDigits(offset: Int, limit: Int) =
        Single.timer(3, TimeUnit.SECONDS)
            .map { (offset + 1..offset + limit).toList() }
            .subscribeOn(Schedulers.io())

}