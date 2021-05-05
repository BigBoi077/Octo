package cegepst.example.octo.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.octo.R
import cegepst.example.octo.models.base.Card
import com.bumptech.glide.Glide

class CardAdapter(private val cards: List<Card>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.cardImage)
        private val name = itemView.findViewById<TextView>(R.id.cardName)
        private val set = itemView.findViewById<TextView>(R.id.cardSet)
        private val price = itemView.findViewById<TextView>(R.id.cardPrice)
        private val actionSingle = itemView.findViewById<ImageView>(R.id.actionSingleCard)

        fun setContent(card: Card) {
            Glide.with(itemView).load(card.imageUris?.get("normal")).centerCrop().into(image)
            name.text = card.name
            set.text = card.setName
            price.text = card.prices?.get("usd")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent(cards[position])
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}
