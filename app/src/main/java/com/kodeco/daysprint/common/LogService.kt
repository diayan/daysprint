package com.kodeco.daysprint.common

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}