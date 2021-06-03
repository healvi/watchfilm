package com.healvimaginer.watchfilm.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.healvimaginer.watchfilm.data.source.local.LocalDataSourceTv
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

    private val tvResponses = DataDummy.generateDummyTv()
    private val tvId = tvResponses[0].contentId

    @Test
    fun getAllTv() {
        val dummytv = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getAllTv()).thenReturn(dummytv)
        tvRepository.getAllTv()
        val tvEntities = Resource.success(PageListUtil.mockPagedList(DataDummy.generateDummyTv()))
//        verify(remote).getAllTv()
        Assert.assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }


    @Test
    fun getTv() {
        val dummytv = MutableLiveData<TvEntity>()
        dummytv.value = DataDummy.generateDummyTv()[0]
        Mockito.`when`(local.getTv(tvId)).thenReturn(dummytv)
        val tvEntities = Resource.success(LiveDataTestUtil.getValue(tvRepository.getTv(tvId)))

//        verify(remote).getTv(tvId)
        Assert.assertNotNull(tvEntities)
        Assert.assertNotNull(tvEntities.data?.data?.title)
        assertEquals(tvResponses[0].title, tvEntities.data?.data?.title)
    }
}