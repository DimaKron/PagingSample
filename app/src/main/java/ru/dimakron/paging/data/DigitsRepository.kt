package ru.dimakron.paging.data

class DigitsRepository(private val apiService: ApiService) {

    fun createPagingSource() = DigitsPagingSource(apiService)

    fun getDigits(lastDigit: Int?, limit: Int = 10) =
        apiService.getDigits(lastDigit, limit)

}