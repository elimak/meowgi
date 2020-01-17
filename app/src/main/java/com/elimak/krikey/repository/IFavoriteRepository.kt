package com.elimak.krikey.repository

import com.elimak.krikey.db.vo.ResultPic
import kotlinx.coroutines.channels.ReceiveChannel

interface IFavoriteRepository {
    suspend fun updateFavorite(id: ResultPic, favorite: Boolean) : Boolean
    fun isFavorite(pic: ResultPic) : Boolean
    suspend fun initiateFavorite()
    val updateCount: ReceiveChannel<Int>
    val updateDelete: ReceiveChannel<ResultPic>
    fun getFavorites(): MutableList<ResultPic>
}