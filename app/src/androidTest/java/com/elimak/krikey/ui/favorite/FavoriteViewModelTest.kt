//package com.elimak.krikey.ui.favorite
//
//import android.app.Application
//import junit.framework.Assert.assertEquals
//import org.junit.Test
//
//import org.mockito.Mockito.mock
//
//class FavoriteViewModelTest {
//
//    private fun createViewModel() : FavoriteViewModel {
//        val application: Application = mock(Application::class.java)
//        return FavoriteViewModel(application)
//    }
//
//    @Test
//    fun getTitleFavCount() {
//        val viewModel = createViewModel()
//        viewModel.titleFavCount.get()
//        assertEquals(viewModel.titleFavCount.get(), "test")
//    }
//}