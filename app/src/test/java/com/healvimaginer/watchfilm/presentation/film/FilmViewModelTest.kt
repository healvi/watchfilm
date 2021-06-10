package com.healvimaginer.watchfilm.presentation.film

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
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
import com.healvimaginer.watchfilm.domain.utils.vo.Resource
import org.junit.Rule

@RunWith(MockitoJUnitRunner::class)
class FilmViewModelTest {
    private lateinit var viewModel: FilmViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<FilmsEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<FilmsEntity>

    @Before
    fun setUp() {
        viewModel = FilmViewModel(filmRepository)
    }

    @Test
    fun getFilm() {

        val dummyFilm = Resource.success(pagedList)
        `when`(dummyFilm.data?.size).thenReturn(10)
        val film = MutableLiveData<Resource<PagedList<FilmsEntity>>>()
        film.value = dummyFilm

        `when`(filmRepository.getAllFilm()).thenReturn(film)
        val filmEntities = viewModel.getFilm().value?.data
        verify(filmRepository).getAllFilm()
        Assert.assertNotNull(filmEntities )
        Assert.assertEquals(10, filmEntities?.size)

        viewModel.getFilm().observeForever(observer)
        verify(observer).onChanged(dummyFilm)
    }
}