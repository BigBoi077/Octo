package cegepst.example.octo.models.helpers

import cegepst.example.octo.models.base.MagicColors
import cegepst.example.octo.models.base.NBR_COLORS
import kotlin.random.Random

private const val PREFIX = "color>="

class ColorQueryFormatter {

    companion object {

        private lateinit var currentColorPie: String

        fun getRandomColorQuery(): String {
            val colors = MagicColors.values().toList().shuffled()
            val times = Random.nextInt(1, NBR_COLORS)
            for (i in 0..times) {
                currentColorPie += colors[Random.nextInt(0, NBR_COLORS)]
            }
            return "$PREFIX$currentColorPie"
        }

        fun getSingleColorQuery(color: String): String {
            return "$PREFIX$color"
        }
    }
}
