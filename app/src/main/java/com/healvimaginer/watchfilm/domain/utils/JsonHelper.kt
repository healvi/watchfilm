package com.healvimaginer.watchfilm.domain.utils

import android.content.Context
import com.healvimaginer.watchfilm.data.source.remote.response.FilmResponse
import com.healvimaginer.watchfilm.data.source.remote.response.TvResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.ArrayList

class JsonHelper(private val context : Context) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadAllFilm(): List<FilmResponse> {
        val list = ArrayList<FilmResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("FilmResponses.json").toString())
            val listArray = responseObject.getJSONArray("film")
            for (i in 0 until listArray.length()) {
                val film = listArray.getJSONObject(i)
                val id          = film.getString("contentId")
                val title       = film.getString("title")
                val description = film.getString("description")
                val director    = film.getString("director")
                val rilis       = film.getString("rilis")
                val image       = film.getString("image")
                val anggaran    = film.getString("anggaran")
                val pendapatan  = film.getString("pendapatan")

                val filmResponse = FilmResponse(id,title,description,director,rilis,image,anggaran,pendapatan)
                list.add(filmResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadAllTv(): List<TvResponse> {
        val list = ArrayList<TvResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvResponses.json").toString())
            val listArray = responseObject.getJSONArray("tv")
            for (i in 0 until listArray.length()) {
                val tv = listArray.getJSONObject(i)
                val id          = tv.getString("contentId")
                val title       = tv.getString("title")
                val description = tv.getString("description")
                val kreator    = tv.getString("kreator")
                val rilis       = tv.getString("rilis")
                val image       = tv.getString("image")
                val status    = tv.getString("status")
                val network  = tv.getString("network")
                val tvResponse = TvResponse(id,title,description,kreator,rilis,image,status,network)
                list.add(tvResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadFilm(filmId: String): FilmResponse {
        val fileName = String.format("film_%s.json", filmId)
        var filmResponse : FilmResponse? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val film = JSONObject(result)
                val id          = film.getString("contentId")
                val title       = film.getString("title")
                val description = film.getString("description")
                val director    = film.getString("director")
                val rilis       = film.getString("rilis")
                val image       = film.getString("image")
                val anggaran    = film.getString("anggaran")
                val pendapatan  = film.getString("pendapatan")

                filmResponse = FilmResponse(id,title,description,director,rilis,image,anggaran,pendapatan)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return filmResponse as FilmResponse
    }

    fun loadTv(tvId: String):TvResponse {
        val fileName = String.format("tv_%s.json", tvId)
        var tvResponse : TvResponse? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val tv = JSONObject(result)
                val id          = tv.getString("contentId")
                val title       = tv.getString("title")
                val description = tv.getString("description")
                val kreator    = tv.getString("kreator")
                val rilis       = tv.getString("rilis")
                val image       = tv.getString("image")
                val status    = tv.getString("status")
                val network  = tv.getString("network")
                tvResponse = TvResponse(id,title,description,kreator,rilis,image,status,network)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return tvResponse as TvResponse
    }
}