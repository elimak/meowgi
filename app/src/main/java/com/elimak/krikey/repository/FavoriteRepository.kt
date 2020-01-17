package com.elimak.krikey.repository

import android.content.Context
import com.elimak.krikey.db.AppDatabase
import com.elimak.krikey.db.vo.ResultPic
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private var context: Context) : IFavoriteRepository {

    private var database: AppDatabase = AppDatabase.getDatabase(context)
    private var favoriteList: MutableList<ResultPic> = mutableListOf()

    private val publishUpdateCount = BroadcastChannel<Int>(1)
    override val updateCount: ReceiveChannel<Int>
        get() = publishUpdateCount.openSubscription()

    private val publishDelete = BroadcastChannel<ResultPic>(1)
    override val updateDelete: ReceiveChannel<ResultPic>
        get() = publishDelete.openSubscription()

    override suspend fun updateFavorite(resultPic: ResultPic, favorite: Boolean): Boolean {
        return withContext(Dispatchers.IO) {
            var success = false
            if(favorite) {
                val result = database.favoriteDao().insertPic(resultPic)
                success = true
            } else {
                val result = database.favoriteDao().deletePicById(resultPic.id)
                publishDelete.send(resultPic)
                success = true
            }
            favoriteList.clear()
            favoriteList.addAll(database.favoriteDao().getAll())
            success
        }
    }

    override fun isFavorite(pic: ResultPic) : Boolean {
        return favoriteList.filter { it.id == pic.id}.size > 0
    }

    override suspend fun initiateFavorite() {
        return withContext(Dispatchers.IO) {
            if (favoriteList.size == 0) {
                favoriteList.addAll(database.favoriteDao().getAll())
            }
            publishUpdateCount.send(favoriteList.size)
        }
    }

    override fun getFavorites(): MutableList<ResultPic> {
        return favoriteList
    }
}