package com.mxtgames.selectableitemdemo.repository

import com.mxtgames.selectableitemdemo.di.IOCoroutineDispatcher
import com.mxtgames.selectableitemdemo.domain.DefaultDomainError
import com.mxtgames.selectableitemdemo.domain.EitherResult
import com.mxtgames.selectableitemdemo.data.ProgramData
import com.mxtgames.selectableitemdemo.data.ProgramsResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProgramsRepositoryImpl @Inject constructor(
    @IOCoroutineDispatcher private val defaultDispatcher: CoroutineDispatcher,
): ProgramsRepository {
    private val _mockProgramData: MutableList<ProgramData> = mutableListOf(
        ProgramData(
            title = "Cotton Echo",
            subtitle = "Cupboard-dries cottons with maximum energy saving.",
            programType = 1,
            selected = false,
        ),
        ProgramData(
            title = "Cottons",
            subtitle = "100% cotton fabrics.",
            programType = 2,
            selected = false,
        ),
        ProgramData(
            title = "Synthetics",
            subtitle = "For a mix of mostly synthetic fabrics, like polyester, or polyamide.",
            programType = 3,
            selected = false,
        ),
        ProgramData(
            title = "Mixed+",
            subtitle = "Cotton, cotton-synthetic blends, and synthetics that don’t need ironing.",
            programType = 4,
            selected = false,
        ),
        ProgramData(
            title = "Delicates",
            subtitle = "Viscose, rayon, acrylic, and other blends.",
            programType = 5,
            selected = false,
        ),
        ProgramData(
            title = "Sports",
            subtitle = "Athletic clothes made of synthetics like polyester, elastane, or polyamide.",
            programType = 6,
            selected = false,
        ),
        ProgramData(
            title = "Bed linen XL",
            subtitle = "Up to 2 single and 1 double sets of bedding.",
            programType = 7,
            selected = false,
        ),
    )

    private val _programsFlow = MutableStateFlow(ProgramsResult())
    override fun observePrograms(): StateFlow<ProgramsResult> = _programsFlow.asStateFlow()

    override suspend fun updatePrograms(): EitherResult<DefaultDomainError, Unit> =
        withContext(defaultDispatcher) {
            // Faking delay
            delay(1000L)
            if (shouldRandomlyFail()) {
                _programsFlow.value = ProgramsResult()
                EitherResult.Failure(DefaultDomainError("I/O Error"))
            } else {
                _programsFlow.value = ProgramsResult(_mockProgramData)
                EitherResult.Success(Unit)
            }
        }

    override suspend fun setProgram(programType: Int) {
        _programsFlow.update { data ->
            data.copy(
                programs = data.programs?.map { program ->
                    program.copy(selected = program.programType == programType)
                }
            )
        }
    }

    private var _requests = 0
    private fun shouldRandomlyFail(): Boolean = ++_requests % 3 != 0
}