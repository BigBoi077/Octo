package cegepst.example.octo.models.helpers

import cegepst.example.octo.models.base.ArtPiece
import cegepst.example.octo.models.base.Artist
import cegepst.example.octo.models.base.ArtistShowcase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class WebScraper(private val absoluteUrl: String) {

    fun findArtists(callback: (List<Artist>) -> Unit) {
        GlobalScope.launch {
            val artists = ArrayList<Artist>()
            val dom = Jsoup.connect(absoluteUrl).get()
            val items = dom.select(".dropdown_container[data-dropdown=\"artist-stores\"] li a")
            for (tag in items) {
                artists.add(Artist(tag.text(), "$absoluteUrl${tag.attr("href")}"))
            }
            withContext(Dispatchers.Main) {
                callback(artists)
            }
        }
    }

    fun findArtistInformation(artistName: String, callback: (ArtistShowcase) -> Unit) {
        GlobalScope.launch {
            val artPieces = ArrayList<ArtPiece>()
            val dom = Jsoup.connect(absoluteUrl).get()
            val profilePicDiv = dom.select(".omega").select("img")
            val profilePic = profilePicDiv.attr("src")
            val items = dom.select(".quick_shop")
            for (item in items) {
                artPieces.add(
                    ArtPiece(
                        item.attr("data-title"),
                        item.attr("data-regular-description"),
                        item.attr("data-feat-img")
                    )
                )
            }
            val artistShowcase = ArtistShowcase(artistName, artPieces, profilePic)
            withContext(Dispatchers.Main) {
                callback(artistShowcase)
            }
        }
    }
}
