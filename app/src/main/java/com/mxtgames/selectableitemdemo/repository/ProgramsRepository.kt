package com.mxtgames.selectableitemdemo.repository

import com.mxtgames.selectableitemdemo.domain.DefaultDomainError
import com.mxtgames.selectableitemdemo.domain.EitherResult
import com.mxtgames.selectableitemdemo.data.ProgramsResult
import kotlinx.coroutines.flow.StateFlow

interface ProgramsRepository {
    suspend fun updatePrograms(): EitherResult<DefaultDomainError, Unit>
    fun observePrograms(): StateFlow<ProgramsResult>
    suspend fun setProgram(programType: Int)
}