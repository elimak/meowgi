package com.elimak.krikey.repository

import com.elimak.krikey.db.vo.ResultPic
import com.elimak.krikey.repository.base.IRepository
import kotlinx.coroutines.channels.ReceiveChannel

interface IFavoriteRepository: IRepository {
    suspend fun updateFavorite(resultPic: ResultPic, favorite: Boolean)
    fun isFavorite(pic: ResultPic) : Boolean
    suspend fun initiateFavorite()
    fun getFavorites(): MutableList<ResultPic>
}