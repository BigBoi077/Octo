package cegepst.example.octo.views.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.octo.R
import cegepst.example.octo.models.base.Legality

private const val LEGAL = "legal"

class LegalityAdapter(private val legalities: List<Legality>) :
    RecyclerView.Adapter<LegalityAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.isLegal)
        private val name = itemView.findViewById<TextView>(R.id.legalityName)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun setContent(legality: Legality) {
            val drawable: Drawable = if (legality.isLegal == LEGAL) {
                itemView.resources.getDrawable(R.drawable.ic_check)
            } else {
                itemView.resources.getDrawable(R.drawable.ic_circle_cross)
            }
            image.setImageDrawable(drawable)
            name.text = legality.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_legality, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent(legalities[position])
    }

    override fun getItemCount(): Int {
        return legalities.size
    }
}
