package ru.dimakron.paging.data

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import io.reactivex.Single

class DigitsPagingSource(private val apiService: ApiService): RxPagingSource<Int, Int>() {

    override fun getRefreshKey(state: PagingState<Int, Int>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Int>> =
        apiService.getDigits(params.key, params.loadSize)
            .map { it.toLoadResult() }
            .onErrorReturn { LoadResult.Error(it) }

    private fun List<Int>.toLoadResult(): LoadResult<Int, Int> =
        LoadResult.Page(this, null, this.lastOrNull(), LoadResult.Page.COUNT_UNDEFINED, LoadResult.Page.COUNT_UNDEFINED)

}