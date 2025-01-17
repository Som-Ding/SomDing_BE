package com.swcontest.somding.exception.project

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

@Getter
@AllArgsConstructor
enum class ProjectErrorCode(override val httpStatus: HttpStatus, override val code: String, override val message: String) : BaseErrorCode {

   // 프로젝트가 없을 때
   PROJECT_NOT_FOUND(HttpStatus.BAD_REQUEST, "PROJECT401", "프로젝트를 찾을 수 없습니다"),

 PROJECT_DELETED_FAILED(HttpStatus.BAD_REQUEST, "PROJECT402", "프로젝트를 이미 후원한 사용자가 있어 삭제 불가능합니다."),

   //이미 좋아요 있을 때
   SCRAP_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "PROJECT403", "이미 관심 프로젝트에 등록 된 프로젝트입니다."),

    
    SCRAP_NOT_FOUND(HttpStatus.NOT_FOUND, "SCRAP401", "스크랩을 찾을 수 없습니다"),

   OPTION_NOT_FOUND(HttpStatus.BAD_REQUEST, "ORDER401", "해당 옵션을 찾을 수 없습니다.");


    // errorResponse 필드는 필요 없으므로 제거
    override val errorResponse: ApiResponse<Void>
        get() = ApiResponse.onFailure(code, message)
}
