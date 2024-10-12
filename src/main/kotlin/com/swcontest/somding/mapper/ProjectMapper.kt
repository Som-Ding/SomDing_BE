package com.swcontest.somding.mapper

import com.swcontest.somding.model.dto.request.ProjectRequestDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.project.Project
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface ProjectMapper {

    @Mapping(target = "projectId", ignore = true)
    @Mapping(target = "member", source = "member")
    fun toEntity(projectRequestDTO: ProjectRequestDTO, member: Member): Project

}
