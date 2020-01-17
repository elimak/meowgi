package com.elimak.krikey.db.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "favorite_table")
class ResultPic constructor(url: String, width: Int, height: Int){
    @PrimaryKey(autoGenerate = false)
    var id: String = ""

    @ColumnInfo(name = "url")
    var url: String? = url

    @ColumnInfo(name = "width")
    var width: Int? = width

    @ColumnInfo(name = "height")
    var height: Int? = height

    override fun toString(): String {
        return Gson().toJson(this)
    }
}