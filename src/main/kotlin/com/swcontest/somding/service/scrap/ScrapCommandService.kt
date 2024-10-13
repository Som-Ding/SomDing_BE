package com.swcontest.somding.service.scrap

interface ScrapCommandService {
    fun createScrap(projectId: Long)

    fun deleteScrap(projectId: Long)
}