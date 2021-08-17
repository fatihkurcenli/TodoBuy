package com.autumnsun.todoapp.arch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.autumnsun.todoapp.database.AppDataBase
import com.autumnsun.todoapp.database.entity.ItemEntity

class ToBuyViewModel() : ViewModel() {
    private lateinit var repository: ToBuyRepository

    val itemEntitiesLiveData = MutableLiveData<List<ItemEntity>>()

    fun init(appDataBase: AppDataBase) {
        repository = ToBuyRepository(appDataBase)
        val items = repository.getAllItems()
        itemEntitiesLiveData.postValue(items)
    }

    fun insertItem(itemEntity: ItemEntity) {
        repository.insertItem(itemEntity)
    }

    fun deleteItem(itemEntity: ItemEntity) {
        repository.deleteItem(itemEntity)
    }
}