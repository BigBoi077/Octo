package cegepst.example.octo.models.helpers

import cegepst.example.octo.models.base.Artist
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
            callback(artists)
        }
    }
}
