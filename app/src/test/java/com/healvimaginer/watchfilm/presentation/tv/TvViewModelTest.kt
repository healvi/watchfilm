package com.healvimaginer.watchfilm.presentation.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.domain.utils.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvRepository: TvRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = TvViewModel(tvRepository)
    }

    @Test
    fun getTv() {

        val dummyTv = Resource.success(pagedList)
        Mockito.`when`(dummyTv.data?.size).thenReturn(10)
        val tv = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tv.value = dummyTv

        Mockito.`when`(tvRepository.getAllTv()).thenReturn(tv)
        val tvEntitys = viewModel.getTv().value?.data
        Mockito.verify(tvRepository).getAllTv()
        Assert.assertNotNull(tvEntitys )
        Assert.assertEquals(10, tvEntitys?.size)

        viewModel.getTv().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTv)
    }
}