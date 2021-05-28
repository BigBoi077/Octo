package cegepst.example.octo.views.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.octo.R
import cegepst.example.octo.models.base.Artist
import cegepst.example.octo.views.artists.SingleArtistActivity

class ArtistAdapter(private val artists: List<Artist>) : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(R.id.artistName)
        private val button = itemView.findViewById<ImageView>(R.id.actionSearchArtist)

        fun setContent(artist: Artist) {
            name.text = artist.name
            button.setOnClickListener {
                actionSingleArtistPage(artist, itemView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_artist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent(artists[position])
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    private fun actionSingleArtistPage(artist: Artist, itemView: View) {
        val intent = Intent(itemView.context, SingleArtistActivity::class.java)
        intent.putExtra("artistUrl", artist.artistUrl)
        intent.putExtra("artistName", artist.name)
        itemView.context.startActivity(intent)
    }
}
