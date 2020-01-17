package com.elimak.krikey.db.vo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    /*** Get a favorite by id.
     * @return the favorite from the table with a specific id.
     */
    @Query("SELECT * FROM favorite_table WHERE id = :id")
    fun getPicById(id: Int): Flow<ResultPic>

    /**
     * Insert a favorite in the database. If the favorite already exists, replace it.
     * @param favorite to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPic(favorite: ResultPic): Long

    /**
     * Insert all favorite.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<ResultPic>): List<Long>

    /**
     * Delete one entry
     */
    @Query("DELETE FROM favorite_table WHERE id = :id")
    fun deletePicById(id: String): Int

    /*** Get all favorite
     * @return all the favorite from the table
     */
    @Query("SELECT * from favorite_table ORDER BY id ASC")
    fun getAll(): List<ResultPic>
}