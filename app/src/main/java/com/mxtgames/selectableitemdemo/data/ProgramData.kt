package com.mxtgames.selectableitemdemo.data

data class ProgramData(
    val title: String,
    val subtitle: String,
    val programType: Int,
    val selected: Boolean,
)

data class ProgramsResult(
    val programs: List<ProgramData>? = null
)