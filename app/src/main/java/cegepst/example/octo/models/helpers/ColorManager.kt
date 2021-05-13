package cegepst.example.octo.models.helpers

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import cegepst.example.octo.R
import cegepst.example.octo.views.BaseActivity

const val COLORLESS = "Colorless"
const val RED = "Red"
const val BLUE = "Blue"
const val BLACK = "Black"
const val WHITE = "White"
const val GREEN = "Green"

class ColorManager {

    companion object {

        fun getAccordingIconForColor(color: String, activity: BaseActivity): Drawable {
            when (color) {
                COLORLESS -> return makeIcon(R.drawable.ic_colorless_mana_symbol, activity)
                RED -> return makeIcon(R.drawable.ic_red_mana_symbol, activity)
                BLUE -> return makeIcon(R.drawable.ic_blue_mana_symbol, activity)
                BLACK -> return makeIcon(R.drawable.ic_black_mana_symbol, activity)
                WHITE -> return makeIcon(R.drawable.ic_white_mana_symbol, activity)
                GREEN -> return makeIcon(R.drawable.ic_green_mana_symbol, activity)
            }
            return makeIcon(R.drawable.ic_colorless_mana_symbol, activity)
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun makeIcon(drawableId: Int, activity: BaseActivity): Drawable {
            return activity.baseContext.getDrawable(drawableId)!!
        }
    }
}
