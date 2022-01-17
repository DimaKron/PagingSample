package ru.dimakron.paging.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.dimakron.paging.model.Digits
import java.util.concurrent.TimeUnit

class ApiService {

    companion object{
        private const val MaxDigit = 100
    }

    fun getDigits(lastDigit: Int?, limit: Int): Single<Digits> {
        val from = if (lastDigit == null) 1 else (lastDigit + 1)

        var to = from + limit
        val hasMore: Boolean
        if (to > MaxDigit) {
            to = MaxDigit + 1
            hasMore = false
        } else {
            hasMore = true
        }
        
        return Single.timer(1, TimeUnit.SECONDS)
            .map { Digits((from until to).toList(), hasMore) }
            .subscribeOn(Schedulers.io())
    }
}