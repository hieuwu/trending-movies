package com.hieuwu.trendingmovies.data.repository.impl

import androidx.paging.Pager
import com.hieuwu.trendingmovies.data.local.movie.TrendingMovieDatabase
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieDetailsEntity
import com.hieuwu.trendingmovies.data.local.movie.entity.MovieEntity
import com.hieuwu.trendingmovies.data.network.movie.MovieService
import com.hieuwu.trendingmovies.data.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {
    lateinit var testee: MovieRepository
    private val movieService: MovieService = mockk(relaxed = true)
    private val pager: Pager<Int, MovieEntity> = mockk(relaxed = true)
    private val movieDatabase: TrendingMovieDatabase = mockk(relaxed = true)
    val dispatcher: TestDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        testee = MovieRepositoryImpl(
            movieService = movieService,
            pager = pager,
            movieDatabase = movieDatabase,
            ioDispatcher = dispatcher
        )
    }

    @Test
    fun whenGetTrendingMovies_then() {
        testee.getTrendingMovies()
        verify { pager.flow }
    }

    @Test
    fun whenSearchMovie_thenMakeApiCall() = runTest {
        testee.searchMovie("Search")

        coVerify { movieService.searchMovie("Search") }
    }

    @Test
    fun givenMovieExistsInDb_whenGetMovieDetails_thenMakeApiCallToReturnResult() = runTest {
        assertTrue(true)
        coEvery {
            movieDatabase.movieDao().getMovieDetails(1)
        } returns MovieDetailsEntity(
            id = 1,
            releaseDate = "releaseDate",
            title = "title"
        )

        val result = testee.getMovieDetails(1)

        assertNotNull(result)
        assertEquals(1, result?.id)
        coVerify { movieDatabase.movieDao().getMovieDetails(1) }
    }
}