package com.anchal.hotnew.home.newtab

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_new")
data class NewModel(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @Embedded
    val data: NewDataModel?

)


data class NewDataModel(
    @ColumnInfo(name = "thumbnail") var thumbnail: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "subreddit") var subreddit: String

)