package cegepst.example.octo.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.octo.R
import cegepst.example.octo.models.base.ArtPiece
import com.bumptech.glide.Glide

class SingleArtistAdapter(private val artPieces: List<ArtPiece>) :
    RecyclerView.Adapter<SingleArtistAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.artPieceImage)
        private val name = itemView.findViewById<TextView>(R.id.artPieceTitle)
        private val description = itemView.findViewById<TextView>(R.id.artPieceDescription)

        fun setContent(artPiece: ArtPiece) {
            Glide.with(itemView).load("https:${artPiece.imageUrl}").centerCrop().into(image)
            name.text = artPiece.name
            description.text = artPiece.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_single_art_piece, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent(artPieces[position])
    }

    override fun getItemCount(): Int {
        return artPieces.size
    }
}
