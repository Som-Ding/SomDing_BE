package com.swcontest.somding.mapper

import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.project.Project
import com.swcontest.somding.model.entity.scrap.Scrap
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ScrapMapper {

    @Mapping(target = "scrapId", ignore = true)
    @Mapping(target = "member", source = "member")
    @Mapping(target = "project", source = "project")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    fun toEntity(project: Project, member: Member): Scrap
}