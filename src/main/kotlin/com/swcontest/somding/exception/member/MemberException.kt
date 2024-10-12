package com.swcontest.somding.exception.member

import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import com.swcontest.somding.common.apiPayload.exception.GeneralException
import lombok.Getter


@Getter
class MemberException(errorCode: BaseErrorCode?) : GeneralException(errorCode!!)

