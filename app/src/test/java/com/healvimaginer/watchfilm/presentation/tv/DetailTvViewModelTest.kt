package com.healvimaginer.watchfilm.presentation.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.healvimaginer.watchfilm.data.FakeTvRepository
import com.healvimaginer.watchfilm.data.TvRepository
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceTv
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.data.source.remote.RemoteDataSource
import com.healvimaginer.watchfilm.domain.utils.AppExecutors
import com.healvimaginer.watchfilm.domain.utils.DataDummy
import com.healvimaginer.watchfilm.domain.utils.LiveDataTestUtil
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
class DetailTvViewModelTest {
    private lateinit var viewModel: DetailTvViewModel
    private val dummyTv = DataDummy.generateDummyTv()[0]
    private val contentId = dummyTv.contentId
    private val local = Mockito.mock(LocalDataSourceTv::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val dummyFavTv = DataDummy.generateFavDummyTv()[0]
    private val tvResponses = DataDummy.generateDummyTv()
    private val tvId = tvResponses[0].contentId
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvRepository: TvRepository
    private val fakeTvRepository = FakeTvRepository(remote,local,appExecutors)
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

    @Test
    fun insert() {
        val dummytv = MutableLiveData<FavoriteTvEntity>()
        dummytv.value = dummyFavTv
        val tv = dummyFavTv
        local.insertTvFavorite(tv)
        Mockito.verify(local, Mockito.times(1)).insertTvFavorite(tv)
    }

    @Test
    fun findFilm() {
        val dummytv = MutableLiveData<FavoriteTvEntity>()
        dummytv.value = dummyFavTv
        Mockito.`when`(local.findTvFavorite(tvId)).thenReturn(dummytv)
        val tvEntities = Resource.success(LiveDataTestUtil.getValue(fakeTvRepository.findTv(tvId)))
        Assert.assertNotNull(tvEntities)
        Assert.assertNotNull(tvEntities.data?.title)
        junit.framework.Assert.assertEquals(tvResponses[0].kreator, tvEntities.data?.kreator)
    }

    @Test
    fun delete() {
        val dummytv = MutableLiveData<FavoriteTvEntity>()
        dummytv.value = dummyFavTv
        val tv = dummyFavTv
        local.deleteTvFavorite(tv)
        Mockito.verify(local, Mockito.times(1)).deleteTvFavorite(tv)
    }
}