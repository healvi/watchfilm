package com.healvimaginer.watchfilm.presentation.favorite.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
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
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.tv.FavTvViewModel
import org.junit.Rule

@RunWith(MockitoJUnitRunner::class)
class FavTvViewModelTest {
    private lateinit var viewModel: FavTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvRepository: TvRepository

    @Mock
    private lateinit var observer: Observer<PagedList<FavoriteTvEntity>>

    @Mock
    private lateinit var pagedList: PagedList<FavoriteTvEntity>

    @Before
    fun setUp() {
        viewModel = FavTvViewModel(tvRepository)
    }

    @Test
    fun getAllTvPag() {
        val dummyTv = pagedList
        `when`(dummyTv.size).thenReturn(10)
        val film = MutableLiveData<PagedList<FavoriteTvEntity>>()
        film.value = dummyTv

        `when`(tvRepository.getAllTvPagging()).thenReturn(film)
        val filmEntities = viewModel.getAllTvPag()
        verify(tvRepository).getAllTvPagging()
        Assert.assertNotNull(filmEntities.value)
        Assert.assertEquals(10, filmEntities?.value?.size)

        viewModel.getAllTvPag().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}