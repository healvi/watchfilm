package com.healvimaginer.watchfilm.domain.utils

import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.data.source.remote.response.FilmResponse
import com.healvimaginer.watchfilm.data.source.remote.response.TvResponse

object DataDummy {
    fun generateDummyFilm() : List<FilmsEntity> {
        val film = ArrayList<FilmsEntity>()
        film.add(
            FilmsEntity("f1",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        film.add(
            FilmsEntity("f2",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        film.add(
            FilmsEntity("f3",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        film.add(
            FilmsEntity("f4",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        film.add(
            FilmsEntity("f5",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        film.add(
            FilmsEntity("f6",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        film.add(
            FilmsEntity("f7",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        film.add(
            FilmsEntity("f8",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        film.add(
            FilmsEntity("f9",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        film.add(
            FilmsEntity("f10",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        return film
    }

    fun generateRemoteDummyFilm(): java.util.ArrayList<FilmResponse> {

        val film = java.util.ArrayList<FilmResponse>()

        film.add(FilmResponse("f1",
            "Start Is Born",
            "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
            "Bradley Cooper",
            12F,
            "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
            "36 Juta Dollar",
            12F,
            "makan",
            "hai"))

        return film
    }

    fun generateFavDummyFilm(): java.util.ArrayList<FavoriteFilmEntity> {

        val film = java.util.ArrayList<FavoriteFilmEntity>()

        film.add(
            FavoriteFilmEntity("f1",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai")
        )

        return film
    }

    fun generateRemoteDummyTv(): java.util.ArrayList<TvResponse> {

        val tv = java.util.ArrayList<TvResponse>()

        tv.add(TvResponse("t1",
            "Start Is Born",
            "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
            "Bradley Cooper",
            12F,
            "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
            "36 Juta Dollar",
            12F,
            "makan",
            "hai"
        ))

        return tv
    }

    fun generateFavDummyTv(): java.util.ArrayList<FavoriteTvEntity> {

        val tv = java.util.ArrayList<FavoriteTvEntity>()

        tv.add(FavoriteTvEntity("t1",
            "Start Is Born",
            "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
            "Bradley Cooper",
            12F,
            "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
            "36 Juta Dollar",
            12F,
            "makan",
            "hai"
        ))

        return tv
    }

    fun generateDummyTv() : List<TvEntity> {
        val tv = ArrayList<TvEntity>()
        tv.add(
            TvEntity("t1",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        tv.add(
            TvEntity("t2",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        tv.add(
            TvEntity("t3",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        tv.add(
            TvEntity("t4",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        tv.add(
            TvEntity("t5",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        tv.add(
            TvEntity("t6",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        tv.add(
            TvEntity("t7",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        tv.add(
            TvEntity("t8",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        tv.add(
            TvEntity("t9",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        tv.add(
            TvEntity("t10",
                "Start Is Born",
                "Film Ini berisi tentang bagaimana kita terlahir dan bagaimana kita mendapat ha kita sebagai manusia abadi",
                "Bradley Cooper",
                12F,
                "https://image.tmdb.org/t/p/w500/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
                "36 Juta Dollar",
                12F,
                "makan",
                "hai"
            )
        )
        return tv
    }
}