package com.healvimaginer.watchfilm.domain.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.domain.model.Film
import com.healvimaginer.watchfilm.domain.model.Tv

object DataMapper {
    fun mapDomainToFavFilmEntity(it : Film) = FavoriteFilmEntity(
        contentId=it.contentId,
        title=it.title,
        overview=it.overview,
        popularity=it.popularity,
        poster_path=it.poster_path,
        backdrop_path=it.backdrop_path,
        vote_average=it.vote_average,
        release_date=it.release_date,
    )

    fun mapDomainToFavTvEntity(it : Tv) = FavoriteTvEntity(
        contentId=it.contentId,
        name=it.name,
        overview=it.overview,
        popularity=it.popularity,
        poster_path=it.poster_path,
        backdrop_path=it.backdrop_path,
        vote_average=it.vote_average,
        first_air_date=it.first_air_date
    )

    fun mapEntitiesToDomainFavFilm(input: List<FavoriteFilmEntity>): List<Film> =
        input.map {
            Film(
                contentId=it.contentId,
                title=it.title,
                overview=it.overview,
                popularity=it.popularity,
                poster_path=it.poster_path,
                backdrop_path=it.backdrop_path,
                vote_average=it.vote_average,
                release_date=it.release_date,
            )
        }

    fun mapEntitiesToDomainFavTv(input: List<FavoriteTvEntity>): List<Tv> =
        input.map {
            Tv(
                contentId=it.contentId,
                name=it.name,
                overview=it.overview,
                popularity=it.popularity,
                poster_path=it.poster_path,
                backdrop_path=it.backdrop_path,
                vote_average=it.vote_average,
                first_air_date=it.first_air_date
            )
        }

    fun mapEntitiesToDomainFilm(input: List<FilmsEntity>): List<Film> =
        input.map {
            Film(
                contentId=it.contentId,
                title=it.title,
                overview=it.overview,
                popularity=it.popularity,
                poster_path=it.poster_path,
                backdrop_path=it.backdrop_path,
                vote_average=it.vote_average,
                release_date=it.release_date,
            )
        }

    fun mapEntitiesToDomainFilmOne(it: FilmsEntity) = Film(
            contentId = it.contentId,
            title = it.title,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            backdrop_path = it.backdrop_path,
            vote_average = it.vote_average,
            release_date = it.release_date,
        )

    fun mapDomainToEntityFilm(it: Film) = FilmsEntity(
        contentId= it.contentId,
        title=it.title,
        overview=it.overview,
        popularity=it.popularity,
        poster_path=it.poster_path,
        backdrop_path=it.backdrop_path,
        vote_average=it.vote_average,
        release_date=it.release_date,
    )

    fun mapEntitiesToDomainTv(input: List<TvEntity>): List<Tv> =
        input.map {
            Tv(
                contentId=it.contentId,
                name=it.name,
                overview=it.overview,
                popularity=it.popularity,
                poster_path=it.poster_path,
                backdrop_path=it.backdrop_path,
                vote_average=it.vote_average,
                first_air_date=it.first_air_date
            )
        }

    fun mapEntitiesToDomainTvOne(it: TvEntity) = Tv(
        contentId = it.contentId,
        name = it.name,
        overview = it.overview,
        popularity = it.popularity,
        poster_path = it.poster_path,
        backdrop_path = it.backdrop_path,
        vote_average = it.vote_average,
        first_air_date = it.first_air_date
    )

    fun mapDomainToEntityTv(it: Tv) = TvEntity(
        contentId= it.contentId,
        name=it.name,
        overview=it.overview,
        popularity=it.popularity,
        poster_path=it.poster_path,
        backdrop_path=it.backdrop_path,
        vote_average=it.vote_average,
        first_air_date=it.first_air_date
    )
}