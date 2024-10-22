package com.swcontest.somding.exception.s3

import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import com.swcontest.somding.common.apiPayload.exception.GeneralException

class S3Exception(errorCode: BaseErrorCode?) : GeneralException(errorCode!!)