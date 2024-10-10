package com.swcontest.somding.common.apiPayload.exception

import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode

class GeneralException(val errorCode: BaseErrorCode) : RuntimeException()
