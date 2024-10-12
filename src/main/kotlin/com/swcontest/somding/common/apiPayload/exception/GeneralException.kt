package com.swcontest.somding.common.apiPayload.exception

import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode

open class GeneralException(val errorCode: BaseErrorCode) : RuntimeException()
