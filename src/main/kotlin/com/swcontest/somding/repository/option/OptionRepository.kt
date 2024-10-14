package com.swcontest.somding.repository.option

import com.swcontest.somding.model.entity.option.Option
import org.springframework.data.jpa.repository.JpaRepository

interface OptionRepository:JpaRepository<Option,Long> {
}