package com.anchal.hotnew.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anchal.hotnew.R
import com.anchal.hotnew.base.BaseModel
import com.anchal.hotnew.base.BaseResponse
import com.anchal.hotnew.home.hottab.HotDao
import com.anchal.hotnew.home.hottab.HotModel
import com.anchal.hotnew.home.newtab.NewDao
import com.anchal.hotnew.home.newtab.NewModel
import com.anchal.hotnew.network.RetrofitInstance
import com.anchal.hotnew.network.RetrofitInterface

import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepo(val hotDao: HotDao, val newDao: NewDao) {

    private val retrofitInterface: RetrofitInterface

    init {
        retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface::class.java)
    }


//    val allLocations = hotDao.getAll()


    fun insertNewModel(newModel: List<NewModel>) {
        doAsync {
            newDao.deleteItem()
            newDao.insertAll(newModel)
        }

    }

    fun getAllNewList() : LiveData<MutableList<NewModel>> {
        return newDao.getAll()
    }

    fun insertHotModel(hotModel: List<HotModel>) {
        doAsync {
            hotDao.deleteItem()
            hotDao.insertAll(hotModel)
        }

    }

    fun getAllHotList() : LiveData<MutableList<HotModel>> {
        return hotDao.getAll()
    }

    fun checkDataExist() : LiveData<Int>{
       return hotDao.dataCount()
    }


    fun getHotList(context: Context): LiveData<Pair<Boolean, BaseModel<MutableList<HotModel>>?>> {

        val data = MutableLiveData<Pair<Boolean, BaseModel<MutableList<HotModel>>?>>()

        retrofitInterface.hotList.enqueue(object : Callback<BaseResponse<BaseModel<MutableList<HotModel>>>>{
            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            override fun onFailure(
                call: Call<BaseResponse<BaseModel<MutableList<HotModel>>>>,
                t: Throwable
            ) {
                Toast.makeText(
                    context,
                    context.getString(R.string.api_error),
                    Toast.LENGTH_SHORT
                ).show()
                data.value = Pair(false, null)
            }

            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(
                call: Call<BaseResponse<BaseModel<MutableList<HotModel>>>>,
                response: Response<BaseResponse<BaseModel<MutableList<HotModel>>>>
            ) {
                if (response.isSuccessful && response.body()!!.data.data != null){
                    data.value = Pair(true, response.body()!!.data)
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.api_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    data.value = Pair(false, null)
                }
            }

        })
        return data

    }

    fun getNewList(context: Context): LiveData<Pair<Boolean, BaseModel<MutableList<NewModel>>?>> {

        val data = MutableLiveData<Pair<Boolean, BaseModel<MutableList<NewModel>>?>>()

        retrofitInterface.newList.enqueue(object : Callback<BaseResponse<BaseModel<MutableList<NewModel>>>>{
            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            override fun onFailure(
                call: Call<BaseResponse<BaseModel<MutableList<NewModel>>>>,
                t: Throwable
            ) {
                Toast.makeText(
                    context,
                    context.getString(R.string.api_error),
                    Toast.LENGTH_SHORT
                ).show()
                data.value = Pair(false, null)
            }

            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(
                call: Call<BaseResponse<BaseModel<MutableList<NewModel>>>>,
                response: Response<BaseResponse<BaseModel<MutableList<NewModel>>>>
            ) {
                if (response.isSuccessful && response.body()!!.data.data != null){
                    data.value = Pair(true, response.body()!!.data)
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.api_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    data.value = Pair(false, null)
                }
            }

        })
        return data

    }
}