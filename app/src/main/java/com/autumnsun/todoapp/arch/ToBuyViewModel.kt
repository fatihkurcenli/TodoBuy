package com.autumnsun.todoapp.arch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.todoapp.database.AppDataBase
import com.autumnsun.todoapp.database.entity.ItemEntity
import kotlinx.coroutines.launch

class ToBuyViewModel() : ViewModel() {
    private lateinit var repository: ToBuyRepository

    val itemEntitiesLiveData = MutableLiveData<List<ItemEntity>>()



    fun init(appDataBase: AppDataBase) {
        repository = ToBuyRepository(appDataBase)
        Log.i("Coroutine","1")
        viewModelScope.launch {
            val items = repository.getAllItems()
            itemEntitiesLiveData.postValue(items)
            Log.i("Coroutine","3")
        }
        Log.i("Coroutine","2")
    }

    fun insertItem(itemEntity: ItemEntity) {
        viewModelScope.launch {
            repository.insertItem(itemEntity)
        }

    }

    fun deleteItem(itemEntity: ItemEntity) {
        viewModelScope.launch {
            repository.deleteItem(itemEntity)
        }

    }
}