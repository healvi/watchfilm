package com.healvimaginer.watchfilm.presentation.favorite.film

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.healvimaginer.watchfilm.data.FilmRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.film.FavFilmViewModel
import org.junit.Rule

@RunWith(MockitoJUnitRunner::class)
class FavFilmViewModelTest {
    private lateinit var viewModel: FavFilmViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<PagedList<FavoriteFilmEntity>>

    @Mock
    private lateinit var pagedList: PagedList<FavoriteFilmEntity>

    @Before
    fun setUp() {
        viewModel = FavFilmViewModel(filmRepository)
    }

    @Test
    fun getAllFilmPag() {
        val dummyFilm = pagedList
        `when`(dummyFilm.size).thenReturn(10)
        val film = MutableLiveData<PagedList<FavoriteFilmEntity>>()
        film.value = dummyFilm

        `when`(filmRepository.getAllFilmPagging()).thenReturn(film)
        val filmEntities = viewModel.getAllFilmPag()
        verify(filmRepository).getAllFilmPagging()
        Assert.assertNotNull(filmEntities.value)
        Assert.assertEquals(10, filmEntities?.value?.size)

        viewModel.getAllFilmPag().observeForever(observer)
        verify(observer).onChanged(dummyFilm)
    }
}