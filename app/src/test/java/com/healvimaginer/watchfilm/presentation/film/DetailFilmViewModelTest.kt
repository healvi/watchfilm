package com.healvimaginer.watchfilm.presentation.film

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.healvimaginer.watchfilm.data.FilmRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.domain.utils.DataDummy
import com.healvimaginer.watchfilm.domain.vo.Resource
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
    private val courseId = dummyFilm.contentId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

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


}