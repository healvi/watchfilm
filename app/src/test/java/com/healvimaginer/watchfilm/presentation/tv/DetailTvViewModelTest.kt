package com.healvimaginer.watchfilm.presentation.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
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
class DetailTvViewModelTest {
    private lateinit var viewModel: DetailTvViewModel
    private val dummyTv = DataDummy.generateDummyTv()[0]
    private val contentId = dummyTv.contentId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvRepository: TvRepository

    @Mock
    private lateinit var courseObserver: Observer<Resource<TvEntity>>

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel(tvRepository)
        viewModel.setSelectedTv(contentId)
    }

    @Test
    fun testGetTv() {
        val tv = MutableLiveData<Resource<TvEntity>>()
        tv.value = Resource.success(dummyTv) as Resource<TvEntity>

        Mockito.`when`(tvRepository.getTv(contentId)).thenReturn(tv)
        viewModel.getTv().observeForever(courseObserver)
        Mockito.verify(courseObserver).onChanged(tv.value)
    }
}