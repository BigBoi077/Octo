package cegepst.example.octo.views.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.octo.R
import cegepst.example.octo.models.stored.StoredCard
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class WishListAdapter(
    private val cards: List<StoredCard>,
    private val alert: (String) -> Unit,
    private val delete: (Long, Long) -> Unit,
    private val userId: Long
) : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(R.id.cardName)
        private val quantity = itemView.findViewById<TextView>(R.id.cardQuantity)
        private val total = itemView.findViewById<TextView>(R.id.cardTotal)
        private val cart = itemView.findViewById<ImageView>(R.id.actionBuyCard)
        private val trash = itemView.findViewById<ImageView>(R.id.actionTrashCard)

        @SuppressLint("SetTextI18n")
        fun setContent(card: StoredCard) {
            name.text = card.cardName
            quantity.text = "${card.quantity}X"
            total.text = "Total:${card.total}$"
            cart.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(card.purchaseLink))
                itemView.context.startActivity(browserIntent)
            }
            trash.setOnClickListener {
                deleteFromList(card.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_stored_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent(cards[position])
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    private fun deleteFromList(id: Long) {
        for ((index, card) in cards.withIndex()) {
            if (card.id == id) {
                GlobalScope.launch {
                    delete(userId, card.id)
                }
                alert("Removed ${card.quantity} ${card.cardName} from your wish list.")
                cards.drop(index)
                return
            }
        }
        this.notifyDataSetChanged()
    }
}
