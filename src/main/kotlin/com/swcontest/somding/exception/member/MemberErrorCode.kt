package com.swcontest.somding.exception.member

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

@Getter
@AllArgsConstructor
enum class MemberErrorCode(override val httpStatus: HttpStatus, override val code: String, override val message: String) : BaseErrorCode {
    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),

    // 해당 멤버가 없을 때
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER401", "해당 멤버를 찾을 수 없습니다"),

    // 비밀번호 재확인이 올바르지 않을 때
    PASSWORD_NOT_EQUAL(HttpStatus.BAD_REQUEST, "MEMBER402", "비밀번호가 일치하지 않습니다."),

    // 이메일 없을 때
    EMAIL_NOT_EXIST(HttpStatus.BAD_REQUEST, "USER405", "사용자 이메일이 존재하지 않습니다."),

    // 이메일이 중복될 때
    USER_ALREADY_EXIST(HttpStatus.CONFLICT, "MEMBER403", "사용자가 이미 존재합니다."),

    // 비밀번호를 잘못 입력했을 때
    PASSWORD_NOT_MATCH(HttpStatus.UNAUTHORIZED, "MEMBER404", "비밀번호가 일치하지 않습니다.");

    // errorResponse 필드는 필요 없으므로 제거
    override val errorResponse: ApiResponse<Void>
        get() = ApiResponse.onFailure(code, message)
}