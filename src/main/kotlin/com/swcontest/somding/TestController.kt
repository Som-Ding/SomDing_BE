package com.swcontest.somding

import com.swcontest.somding.common.apiPayload.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/test")
    fun test(): ApiResponse<String>{
        return ApiResponse.onSuccess("sss")
    }
}