package com.example.ratemyfood

import com.example.ratemyfood.data.model.Businesses
import com.example.ratemyfood.data.rest.YelpRepository
import com.example.ratemyfood.data.rest.YelpService
import com.example.ratemyfood.viewmodel.BusinessCardViewModel
import io.reactivex.Maybe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*

@RunWith(JUnit4::class)
class BusinessCardViewModelTest {

    @Mock
    lateinit var yelpService: YelpService
    @Mock
    lateinit var yelpRepo: YelpRepository

    lateinit var businessCardViewModel: BusinessCardViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        this.businessCardViewModel = BusinessCardViewModel(this.yelpRepo)
    }

    @Test
    fun fetchRepoBusinessGoodResponse(){
        Mockito.`when`(this.yelpService.getBusinesses(ArgumentMatchers.anyString(),ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<Businesses>())
        }
        //this.businessCardViewModel.fetchBusinessCards(0,12,"American")
        //assertNotNull(this.businessCardViewModel.businessCardResult().value)
    }

    @Test
    fun fetchRepoReviewGoodResponse(){
        Mockito.`when`(this.yelpService.getReviews(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<Businesses>())
        }
        //this.businessCardViewModel.fetchBusinessCards(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyString())
        //assertNotNull(this.businessCardViewModel.businessCardResult().value)
    }

    @Test
    fun fetchRepoReviewErrorResponse(){
        Mockito.`when`(this.yelpService.getReviews(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<Businesses>())
        }
        //this.businessCardViewModel.fetchBusinessCards(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyString())
        //assertNotNull(this.businessCardViewModel.businessCardError().value)
    }

    @Test
    fun fetchRepoBusinessErrorResponse(){
        Mockito.`when`(this.yelpService.getReviews(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<Businesses>())
        }
        //this.businessCardViewModel.fetchBusinessCards(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyString())
        //assertNotNull(this.businessCardViewModel.businessCardError().value)
    }
}