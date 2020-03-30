package com.anchal.hotnew.home.hottab

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_hot")
data class HotModel(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @Embedded
    val data: DataModel?

)


data class DataModel(
    @ColumnInfo(name = "thumbnail") var thumbnail: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "subreddit") var subreddit: String

)