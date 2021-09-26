package com.autumnsun.todoapp.arch

import com.autumnsun.todoapp.database.AppDataBase
import com.autumnsun.todoapp.database.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

class ToBuyRepository(
    private val appDataBase: AppDataBase
) {

    fun getAllItems(): Flow<List<ItemEntity>> {
        return appDataBase.itemEntityDao().getAllItemEntity()
    }

    suspend fun insertItem(itemEntity: ItemEntity) {
        appDataBase.itemEntityDao().insert(itemEntity)
    }

    suspend fun updateItem(itemEntity: ItemEntity) {
        appDataBase.itemEntityDao().updateItem(itemEntity)
    }

    suspend fun deleteItem(itemEntity: ItemEntity) {
        appDataBase.itemEntityDao().delete(itemEntity)
    }

}