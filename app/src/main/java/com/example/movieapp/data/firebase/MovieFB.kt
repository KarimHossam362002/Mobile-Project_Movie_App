import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class MovieFB(
    var averageRating: Int =0,
    var id: String? = null,
    var title: String = "",
    var originalTitle: String = "",
    var year: String? = null,
    var releaseDate: String? = null,
    var contentRating: String? = null,
    var storyline: String? = null,
    var imdbRating:  String? = null,
    var duration: String = "",
    var poster: String? = null,
    var posterurl: String? = null,

    ){ var isFavorite by mutableStateOf(false)}