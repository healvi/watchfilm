package com.healvimaginer.watchfilm.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceFilm
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.remote.RemoteDataSource
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.domain.utils.DataDummy
import com.healvimaginer.watchfilm.domain.utils.LiveDataTestUtil
import com.healvimaginer.watchfilm.domain.utils.PageListUtil
import com.healvimaginer.watchfilm.domain.vo.Resource
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val local = Mockito.mock(LocalDataSourceFilm::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val filmRepository = FakeFilmRepository(remote, local, appExecutors)

    private val filmResponses = DataDummy.generateDummyFilm()
    private val filmId = filmResponses[0].contentId
    private val dummyFilm = DataDummy.generateDummyFilm()[6]
    private val dummyFavFilm = DataDummy.generateFavDummyFilm()[0]

    @Test
    fun getAllFilm() {
      val dummyfilm = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmsEntity>
        `when`(local.getAllFilm()).thenReturn(dummyfilm)
        filmRepository.getAllFilm()
        val filmEntities = Resource.success(PageListUtil.mockPagedList(DataDummy.generateDummyFilm()))
        Assert.assertNotNull(filmEntities)
        assertEquals(filmResponses.size.toLong(), filmEntities.data?.size?.toLong())
    }


    @Test
    fun getFilm() {
        val dummyfilm = MutableLiveData<FilmsEntity>()
        dummyfilm.value = DataDummy.generateDummyFilm()[0]
        `when`(local.getFilm(filmId)).thenReturn(dummyfilm)
        val filmEntities = Resource.success(LiveDataTestUtil.getValue(filmRepository.getFilm(filmId)))
        Assert.assertNotNull(filmEntities)
        Assert.assertNotNull(filmEntities.data?.data?.title)
        assertEquals(filmResponses[0].title, filmEntities.data?.data?.title)
    }

    @Test
    fun getAllFilmPagging() {
        val datasourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteFilmEntity>
        `when`(local.getAllFilmFavoritePagging()).thenReturn(datasourceFactory)
        filmRepository.getAllFilmPagging()
        val filmEntities = Resource.success(PageListUtil.mockPagedList(DataDummy.generateDummyFilm()))
        Assert.assertNotNull(filmEntities)
        assertEquals(filmResponses.size.toLong(), filmEntities.data?.size?.toLong())
    }

    @Test
    fun insert() {
        val dummyfilm = MutableLiveData<FavoriteFilmEntity>()
        dummyfilm.value = dummyFavFilm
        val film = dummyFavFilm
        filmRepository.insert(film)
    }

    @Test
    fun findFilm() {
        val dummyfilm = MutableLiveData<FavoriteFilmEntity>()
        dummyfilm.value = dummyFavFilm
        `when`(local.findFilmFavorite(filmId)).thenReturn(dummyfilm)
        val filmEntities = Resource.success(LiveDataTestUtil.getValue(filmRepository.findFilm(filmId)))
        Assert.assertNotNull(filmEntities)
        Assert.assertNotNull(filmEntities.data?.title)
        assertEquals(filmResponses[0].director, filmEntities.data?.director)
    }

    @Test
    fun delete() {
        val dummyfilm = MutableLiveData<FavoriteFilmEntity>()
        dummyfilm.value = dummyFavFilm
        val film = dummyFavFilm
        filmRepository.delete(film)
    }
}