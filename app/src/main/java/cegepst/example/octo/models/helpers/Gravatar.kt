package cegepst.example.octo.models.helpers

private const val URL = "https://www.gravatar.com/avatar/"

class Gravatar(private val email: String) {

    fun getUrl(): String {
        return "${URL}${Hash.md5(email)}"
    }
}
