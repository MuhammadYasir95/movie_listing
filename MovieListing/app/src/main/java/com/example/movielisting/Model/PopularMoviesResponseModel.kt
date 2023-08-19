//package com.example.movielisting.Model
//
//// To parse the JSON, install kotlin's serialization plugin and do:
////
//// val json                       = Json { allowStructuredMapKeys = true }
//// val popularMoviesResponseModel = json.parse(PopularMoviesResponseModel.serializer(), jsonString)
//
//
//data class PopularMoviesResponseModel (
//    val page: Long? = null,
//    val results: List<Result>? = null,
//
//    //@SerialName("total_pages")
//    val total_pages: Long? = null,
//
//    //@SerialName("total_results")
//    val total_results: Long? = null
//)
//
//data class Result (
//    val adult: Boolean? = null,
//
//    //@SerialName("backdrop_path")
//    val backdrop_path: String? = null,
//
//    //@SerialName("genre_ids")
//    val genre_ids: List<Long>? = null,
//
//    val id: Long? = null,
//
//    //@SerialName("original_language")
//    val original_language: OriginalLanguage? = null,
//
//    //@SerialName("original_title")
//    val original_title: String? = null,
//
//    val overview: String? = null,
//    val popularity: Double? = null,
//
////    @SerialName("poster_path")
//    val poster_path: String? = null,
//
////    @SerialName("release_date")
//    val release_date: String? = null,
//
//    val title: String? = null,
//    val video: Boolean? = null,
//
////    @SerialName("vote_average")
//    val vote_average: Double? = null,
//
////    @SerialName("vote_count")
//    val vote_count: Long? = null
//)
//
//enum class OriginalLanguage(val value: String) {
//    En("en"),
//    Ja("ja"),
//    Pl("pl");
//}

package com.example.movielisting.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularMoviesResponseModel(
    val page: Long? = null,
    val results: List<Result>? = null,

    @SerialName("total_pages")
    val total_pages: Long? = null,

    @SerialName("total_results")
    val total_results: Long? = null
)

@Serializable
data class Result(
    val adult: Boolean? = null,

    @SerialName("backdrop_path")
    val backdrop_path: String? = null,

    @SerialName("genre_ids")
    val genre_ids: List<Long>? = null,

    val id: Long? = null,

    @SerialName("original_language")
    val original_language: OriginalLanguage? = null,

    @SerialName("original_title")
    val original_title: String? = null,

    val overview: String? = null,
    val popularity: Double? = null,

    @SerialName("poster_path")
    val poster_path: String? = null,

    @SerialName("release_date")
    val release_date: String? = null,

    val title: String? = null,
    val video: Boolean? = null,

    @SerialName("vote_average")
    val vote_average: Double? = null,

    @SerialName("vote_count")
    val vote_count: Long? = null
)

@Serializable
enum class OriginalLanguage(val value: String) {
    En("en"),
    Ja("ja"),
    Pl("pl");
}
