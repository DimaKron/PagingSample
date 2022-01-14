package ru.dimakron.paging.data

class DigitsRepository(private val apiService: ApiService) {

    fun createPagingSource() = DigitsPagingSource(apiService)

}