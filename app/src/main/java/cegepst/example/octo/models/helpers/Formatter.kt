package cegepst.example.octo.models.helpers

import cegepst.example.octo.models.base.MagicColors
import kotlin.random.Random

private const val COLOR_PREFX = "color>="
private const val ARTIST_PREFIX = "a:"

class Formatter {

    companion object {
        const val IS_COMMANDER = "is:commander"

        fun formatArtistSearch(artistName: String): String {
            return "${ARTIST_PREFIX}\"${artistName}\""
        }

        fun getRandomColorQuery(): String {
            val diceRoll = Random.nextInt()
            if (diceRoll % 2 == 0) {
                return randomColor()
            }
            return "${COLOR_PREFX}colorless"
        }

        private fun randomColor(): String {
            val color = MagicColors.values().toList().shuffled().first()
            return "$COLOR_PREFX$color"
        }

        fun getSingleColorQuery(color: String): String {
            return "$COLOR_PREFX$color"
        }
    }
}
