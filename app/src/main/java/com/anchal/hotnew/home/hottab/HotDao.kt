package com.anchal.hotnew.home.hottab

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface HotDao {
    @Query("SELECT * FROM table_hot")
    fun getAll(): LiveData<MutableList<HotModel>>

    @Insert
    fun insertAll(model: List<HotModel>)

    @Delete
    fun delete(user: HotModel)

    @Query("DELETE FROM table_hot")
    fun deleteItem()

    @Query("SELECT COUNT(*) from table_hot")
    fun dataCount() : LiveData<Int>

}