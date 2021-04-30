package cegepst.example.octo.models.base

const val NBR_COLORS = 6

enum class MagicColors(private val color: String) {
    COLORLESS("colorless"),
    RED("red"),
    BLUE("blue"),
    BLACK("black"),
    WHITE("white"),
    GREEN("green")
}
