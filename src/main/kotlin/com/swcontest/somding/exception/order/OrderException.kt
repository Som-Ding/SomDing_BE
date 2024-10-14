package com.swcontest.somding.exception.order

import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import com.swcontest.somding.common.apiPayload.exception.GeneralException
import lombok.Getter


@Getter
class OrderException(errorCode: BaseErrorCode?) : GeneralException(errorCode!!)

