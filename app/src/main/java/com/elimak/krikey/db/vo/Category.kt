package com.elimak.krikey.db.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "category_table")
class Category constructor(name: String){

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String? = name

    override fun toString(): String {
        return Gson().toJson(this)
    }
}