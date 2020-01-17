package com.elimak.krikey.db.vo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    /*** Get a category by id.
     * @return the category from the table with a specific id.
     */
    @Query("SELECT * FROM category_table WHERE id = :id")
    fun getCategoryById(id: Int): Flow<Category>

    /**
     * Insert a category in the database. If the category already exists, replace it.
     * @param category to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category): Long

    /**
     * Insert all categories.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Category>): List<Long>

    /**
     * Delete all categories.
     */
    @Query("DELETE FROM category_table")
    fun deleteAll(): Int

    /*** Get all categories
     * @return all the categories from the table
     */
    @Query("SELECT * from category_table ORDER BY id ASC")
    fun getAll(): List<Category>
}