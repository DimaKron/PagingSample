package ru.dimakron.paging.ui

import moxy.MvpView

interface IMainActivity: MvpView {

    fun showDigits(items: List<Int>)

}