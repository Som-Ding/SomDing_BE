package com.swcontest.somding.exception.qna

import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import com.swcontest.somding.common.apiPayload.exception.GeneralException
import lombok.Getter


@Getter
class QnaException(errorCode: BaseErrorCode?) : GeneralException(errorCode!!)
