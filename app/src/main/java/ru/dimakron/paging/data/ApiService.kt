package ru.dimakron.paging.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ApiService {

    fun getDigits(lastDigit: Int?, limit: Int): Single<List<Int>>{
        val from = if(lastDigit == null) 1 else (lastDigit + 1)
        return Single.timer(1, TimeUnit.SECONDS)
            .map { (from until from + limit).toList() }
            .subscribeOn(Schedulers.io())
    }
}