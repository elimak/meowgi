package com.elimak.krikey.repository

import com.elimak.krikey.db.vo.Category
import com.elimak.krikey.db.vo.ResultPic
import kotlinx.coroutines.flow.Flow

interface ICatSearchRepository {
    suspend fun fetchCategories(force: Boolean): List<Category>
    suspend fun getCategoryById(id: Int): Flow<Category>
    suspend fun getSearchByCatId(id: Int): List<ResultPic>
}