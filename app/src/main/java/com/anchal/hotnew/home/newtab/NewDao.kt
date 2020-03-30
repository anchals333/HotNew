package com.anchal.hotnew.home.newtab

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NewDao {
    @Query("SELECT * FROM table_new")
    fun getAll(): LiveData<MutableList<NewModel>>

    @Insert
    fun insertAll(model: List<NewModel>)

    @Delete
    fun delete(user: NewModel)

    @Query("DELETE FROM table_new")
    fun deleteItem()

    @Query("SELECT COUNT(*) from table_new")
    fun dataCount() : LiveData<Int>

}