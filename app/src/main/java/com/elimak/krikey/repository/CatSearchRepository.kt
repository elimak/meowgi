package com.elimak.krikey.repository

import android.content.Context
import com.elimak.krikey.App
import com.elimak.krikey.api.IApiServices
import com.elimak.krikey.db.AppDatabase
import com.elimak.krikey.db.vo.Category
import com.elimak.krikey.db.vo.ResultPic
import com.elimak.krikey.repository.base.RepositoryBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CatSearchRepository @Inject constructor(private var context: Context) : RepositoryBase(), ICatSearchRepository {

    @Inject
    lateinit var api: IApiServices
    private var database: AppDatabase

    init {
        App.injector.inject(this)
        database = AppDatabase.getDatabase(context)
    }

    override suspend fun fetchCategories(force: Boolean): List<Category> {
        return withContext(Dispatchers.IO) {

            // check if we have the data in the DB, if we do we wont load it again unless the "force" arg is true
            // we could imagine refreshing this data every n days and thats where we would use "force"
            var list: List<Category> = database.categoryDao().getAll()

            // if we don't have entries in the DB or if we want to refresh the data with the online source
            if(list.isEmpty() || force) {
                val response = api.getCategories()
                if(response.isNotEmpty()) {
                    // insert / update new data, return data inserted
                    database.categoryDao().insertAll(response)
                    list = database.categoryDao().getAll()
                }
            }
            list
        }
    }

    override suspend fun getCategoryById(id: Int): Flow<Category> {
        return withContext(Dispatchers.IO) { database.categoryDao().getCategoryById(id) }
    }

    // TODO: we should have a paginated request here obviously
    // TODO: but with the time frame given there are compromises that need to be made :)
    // TODO: Ideally, we would create in "infinite" scrolling view to hold the result
    override suspend fun getSearchByCatId(id: Int): List<ResultPic> {
        return withContext(Dispatchers.IO) {
            val list = api.getImagesByCategory(id, 100, 0, "DESC")
            list
        }
    }
}