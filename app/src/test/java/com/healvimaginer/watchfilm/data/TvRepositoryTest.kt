package com.healvimaginer.watchfilm.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceTv
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
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

class TvRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val local = Mockito.mock(LocalDataSourceTv::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val tvRepository = FakeTvRepository(remote,local,appExecutors)
    private val testExecutors = AppExecutors()
    private val tvResponses = DataDummy.generateDummyTv()
    private val tvId = tvResponses[0].contentId
    private val dummyFavTv = DataDummy.generateFavDummyTv()[0]

    @Test
    fun getAllTv() {
        val dummytv = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getAllTv()).thenReturn(dummytv)
        tvRepository.getAllTv()
        val tvEntities = Resource.success(PageListUtil.mockPagedList(DataDummy.generateDummyTv()))
        Assert.assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }


    @Test
    fun getTv() {
        val dummytv = MutableLiveData<TvEntity>()
        dummytv.value = DataDummy.generateDummyTv()[0]
        Mockito.`when`(local.getTv(tvId)).thenReturn(dummytv)
        val tvEntities = Resource.success(LiveDataTestUtil.getValue(tvRepository.getTv(tvId)))
        Assert.assertNotNull(tvEntities)
        Assert.assertNotNull(tvEntities.data?.data?.title)
        assertEquals(tvResponses[0].title, tvEntities.data?.data?.title)
    }

    @Test
    fun getAllTvPagging() {
        val datasourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteTvEntity>
        Mockito.`when`(local.getAllTvFavoritePagging()).thenReturn(datasourceFactory)
        tvRepository.getAllTvPagging()
        val filmEntities = Resource.success(PageListUtil.mockPagedList(DataDummy.generateDummyFilm()))
        Assert.assertNotNull(filmEntities)
        assertEquals(tvResponses.size.toLong(), filmEntities.data?.size?.toLong())
    }

    @Test
    fun insert() {
        val dummytv = MutableLiveData<FavoriteTvEntity>()
        dummytv.value = dummyFavTv
        val tv = dummyFavTv
        local.insertTvFavorite(tv)
        Mockito.`when`(appExecutors.diskIO()).thenReturn(testExecutors.diskIO())
        Mockito.doNothing().`when`(local).insertTvFavorite(tv)
        Mockito.verify(local, Mockito.times(1)).insertTvFavorite(tv)
    }

    @Test
    fun findFilm() {
        val dummytv = MutableLiveData<FavoriteTvEntity>()
        dummytv.value = dummyFavTv
        Mockito.`when`(local.findTvFavorite(tvId)).thenReturn(dummytv)
        val tvEntities = Resource.success(LiveDataTestUtil.getValue(tvRepository.findTv(tvId)))
        Assert.assertNotNull(tvEntities)
        Assert.assertNotNull(tvEntities.data?.title)
        assertEquals(tvResponses[0].kreator, tvEntities.data?.kreator)
    }

    @Test
    fun delete() {
        val dummytv = MutableLiveData<FavoriteTvEntity>()
        dummytv.value = dummyFavTv
        val tv = dummyFavTv
        local.deleteTvFavorite(tv)
        Mockito.`when`(appExecutors.diskIO()).thenReturn(testExecutors.diskIO())
        Mockito.doNothing().`when`(local).deleteTvFavorite(tv)
        Mockito.verify(local, Mockito.times(1)).deleteTvFavorite(tv)
    }
}