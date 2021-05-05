package cegepst.example.octo.views.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.octo.R
import cegepst.example.octo.models.helpers.Formatter
import com.bumptech.glide.Glide
import org.jsoup.Jsoup

class ArtistAdapter(private val artists: List<String>) : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.artistImage)
        private val name = itemView.findViewById<TextView>(R.id.artistName)
        private val button = itemView.findViewById<ImageView>(R.id.actionSearchArtist)

        fun setContent(artistName: String) {
            val dom = Jsoup.connect(Formatter.makeArtistLink(artistName)).get()
            val div = dom.select(".five columns omega").first()
            val img = div.children().first()
            val link = img.absUrl("src")
            Log.d("ARTIST LINK", link)
            Glide.with(itemView).load(link).centerCrop().into(image)
            name.text = artistName
            // TODO : set on click listener for artist search
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent(artists[position])
    }

    override fun getItemCount(): Int {
        return artists.size
    }
}
