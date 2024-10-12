package com.swcontest.somding.exception.project

import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import com.swcontest.somding.common.apiPayload.exception.GeneralException
import lombok.Getter


@Getter
class ProjectException(errorCode: BaseErrorCode?) : GeneralException(errorCode!!)

