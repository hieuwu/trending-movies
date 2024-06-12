package com.hieuwu.trendingmovies.presentation.moviedetails

import com.hieuwu.trendingmovies.data.repository.MovieRepository
import com.hieuwu.trendingmovies.domain.model.Movie
import com.hieuwu.trendingmovies.presentation.movielist.MovieListViewModel
import com.hieuwu.trendingmovies.presentation.movielist.ScreenState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MovieDetailsViewModelTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    val mockMovieRepository: MovieRepository = mockk(relaxed = true)

    lateinit var testee: MovieListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(mainThreadSurrogate)

        testee = MovieListViewModel(
            mockMovieRepository
        )
    }

    @Test
    fun whenSearchQueryChange_thenEmitCorrectState() {
        assertEquals("", testee.query.value)

        testee.onSearchQueryChange("Something")

        assertEquals("Something", testee.query.value)
    }

    @Test
    fun givenEmptySearchQuery_whenSearchMovie_thenEmitTrendingMovies() {
        assertEquals("", testee.query.value)

        testee.onSearchMovie()

        assertTrue(testee.screenState.value is ScreenState.Trending)
    }

    @Test
    fun givenSearchQuery_whenSearchMovie_thenEmitSearchMoviesWithResult() = runTest {
        testee.onSearchQueryChange("Something")
        assertEquals("Something", testee.query.value)
        coEvery { mockMovieRepository.searchMovie("Something") } returns listOf(
            Movie(
                id = 1,
                backdropPath = ",",
                title = "",
                voteAverage = 0.2,
                releaseDate = ""
            )
        )
        mockMovieRepository.searchMovie("Something")

        testee.onSearchMovie()

        val result = testee.searchedMovie.value
        assertEquals(1, result.size)
        assertTrue(testee.screenState.value is ScreenState.Search)
    }
}