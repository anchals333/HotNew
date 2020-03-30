package com.anchal.hotnew.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.anchal.hotnew.database.AppDatabase
import com.anchal.hotnew.home.hottab.HotModel
import com.anchal.hotnew.home.newtab.NewModel

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    var list = MutableLiveData<MutableList<HotModel>>()
    var newList = MutableLiveData<MutableList<NewModel>>()
    private val _index = MutableLiveData<Int>()
    val repository: HomeRepo
    val allLocations: List<HotModel>? = null

    init {
        val hotDao = AppDatabase.getDatabase(application).hotDao()
        val newDao = AppDatabase.getDatabase(application).newDao()
        repository = HomeRepo(hotDao, newDao)
    }

    fun  insertHot() {
        repository.insertHotModel(list.value!!)
    }


    fun getAllHotList() : LiveData<MutableList<HotModel>> {
       return repository.getAllHotList()
    }

    fun  insertNew() {
        repository.insertNewModel(newList.value!!)
    }


    fun getAllNewList() : LiveData<MutableList<NewModel>> {
        return repository.getAllNewList()
    }

    fun getHotList(context: Context) = repository.getHotList(context)

    fun getNewList(context: Context) = repository.getNewList(context)

    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}