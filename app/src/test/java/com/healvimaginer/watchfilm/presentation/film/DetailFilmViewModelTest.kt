package com.healvimaginer.watchfilm.presentation.film

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.healvimaginer.watchfilm.data.FakeFilmRepository
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceFilm
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.data.source.remote.RemoteDataSource
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.domain.utils.DataDummy
import com.healvimaginer.watchfilm.domain.utils.LiveDataTestUtil
import com.healvimaginer.watchfilm.domain.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailFilmViewModelTest {

    private lateinit var viewModel: DetailFilmViewModel
    private val dummyFilm = DataDummy.generateDummyFilm()[0]
    private val dummyFavFilm = DataDummy.generateFavDummyFilm()[0]
    private val courseId = dummyFilm.contentId
    private val local = Mockito.mock(LocalDataSourceFilm::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val filmResponses = DataDummy.generateDummyFilm()
    private val filmId = filmResponses[0].contentId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository
    private val fakeFilmRepository = FakeFilmRepository(remote, local, appExecutors)
    @Mock
    private lateinit var courseObserver: Observer<Resource<FilmsEntity>>


    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel(filmRepository)
        viewModel.setSelectedFilm(courseId)
    }

    @Test
    fun getFilm() {
        val film = MutableLiveData<Resource<FilmsEntity>>()
        film.value = Resource.success(dummyFilm) as Resource<FilmsEntity>

        Mockito.`when`(filmRepository.getFilm(courseId)).thenReturn(film)
        viewModel.getFilm().observeForever(courseObserver)
        Mockito.verify(courseObserver).onChanged(film.value)
    }
    @Test
    fun insert() {
        val dummyfilm = MutableLiveData<FavoriteFilmEntity>()
        dummyfilm.value = dummyFavFilm
        val film = dummyFavFilm
        fakeFilmRepository.insert(film)
    }

    @Test
    fun findFilm() {
        val dummyfilm = MutableLiveData<FavoriteFilmEntity>()
        dummyfilm.value = dummyFavFilm
        Mockito.`when`(local.findFilmFavorite(filmId)).thenReturn(dummyfilm)
        val filmEntities = Resource.success(LiveDataTestUtil.getValue(fakeFilmRepository.findFilm(filmId)))
        Assert.assertNotNull(filmEntities)
        Assert.assertNotNull(filmEntities.data?.title)
        junit.framework.Assert.assertEquals(filmResponses[0].director, filmEntities.data?.director)
    }

    @Test
    fun delete() {
        val dummyfilm = MutableLiveData<FavoriteFilmEntity>()
        dummyfilm.value = dummyFavFilm
        val film = dummyFavFilm
        fakeFilmRepository.delete(film)
    }

}