package com.swcontest.somding.service.scrap

import com.swcontest.somding.model.entity.member.Member

interface ScrapCommandService {
    fun createScrap(projectId: Long, member: Member)

    fun deleteScrap(projectId: Long, member: Member)
}