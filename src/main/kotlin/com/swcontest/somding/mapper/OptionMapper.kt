package com.swcontest.somding.mapper

import com.swcontest.somding.model.entity.enums.OptionCategory
import com.swcontest.somding.model.entity.option.Option
import com.swcontest.somding.model.entity.project.Project
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface OptionMapper {

    @Mapping(target = "optionId", ignore = true)
    @Mapping(target = "project", source = "project")
    @Mapping(target = "option", source = "option")
    @Mapping(target = "optionCategory", source = "optionCategory")
    fun toEntity(project: Project, option: String, optionCategory: OptionCategory): Option
}