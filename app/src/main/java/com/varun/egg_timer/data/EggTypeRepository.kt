package com.varun.egg_timer.data

import com.varun.egg_timer.models.EggType

class EggTypeRepository {

    fun getEggTypes(): List<EggType> = listOf(
        EggType("Soft-Boiled", 1, "Runny yolk, set whites"),
        EggType("Medium-Boiled", 7, "Slightly soft yolk"),
        EggType("Hard-Boiled", 10, "Fully cooked yolk and whites")
    )
}