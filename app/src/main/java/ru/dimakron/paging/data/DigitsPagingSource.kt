package ru.dimakron.paging.data

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import io.reactivex.Single
import ru.dimakron.paging.model.Digits

class DigitsPagingSource(private val apiService: ApiService): RxPagingSource<Int, Int>() {

    override fun getRefreshKey(state: PagingState<Int, Int>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Int>> =
        apiService.getDigits(params.key, params.loadSize)
            .map { it.toLoadResult() }
            .onErrorReturn { LoadResult.Error(it) }

    private fun Digits.toLoadResult(): LoadResult<Int, Int> =
        LoadResult.Page(items, null, if (hasMore) items.lastOrNull() else null, LoadResult.Page.COUNT_UNDEFINED, LoadResult.Page.COUNT_UNDEFINED)

}