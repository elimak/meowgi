package com.elimak.krikey.repository

import android.content.Context
import com.elimak.krikey.db.AppDatabase
import com.elimak.krikey.db.vo.ResultPic
import com.elimak.krikey.repository.base.ASignal
import com.elimak.krikey.repository.base.RepositoryBase
import kotlinx.coroutines.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FavoriteRepository @Inject constructor(private val context: Context) : RepositoryBase(), IFavoriteRepository {

    private val database: AppDatabase = AppDatabase.getDatabase(context)
    private var favoriteList: MutableList<ResultPic> = mutableListOf()

    class DeleteFavorite(data: ResultPic) : ASignal<ResultPic>(data)
    class FavoriteCount(data: Int) : ASignal<Int>(data)

    override suspend fun updateFavorite(resultPic: ResultPic, favorite: Boolean) {
        withContext(Dispatchers.IO) {
            if(favorite) {
                database.favoriteDao().insertPic(resultPic)
            } else {
                database.favoriteDao().deletePicById(resultPic.id)
                publisher.send(DeleteFavorite(resultPic))
            }
            favoriteList.clear()
            favoriteList.addAll(database.favoriteDao().getAll())
        }
    }

    override fun isFavorite(pic: ResultPic) : Boolean {
        return favoriteList.filter { it.id == pic.id }.isNotEmpty()
    }

    override suspend fun initiateFavorite() {
        withContext(Dispatchers.IO) {
            if (favoriteList.size == 0) {
                favoriteList.addAll(database.favoriteDao().getAll())
            }
            publisher.send(FavoriteCount(favoriteList.size))
        }
    }

    override fun getFavorites(): MutableList<ResultPic> {
        return favoriteList
    }
}