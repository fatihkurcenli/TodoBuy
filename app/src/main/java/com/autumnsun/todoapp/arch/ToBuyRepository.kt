package com.autumnsun.todoapp.arch

import com.autumnsun.todoapp.database.AppDataBase
import com.autumnsun.todoapp.database.entity.ItemEntity

class ToBuyRepository(
    private val appDataBase: AppDataBase
) {

    suspend fun getAllItems(): List<ItemEntity> {
        return appDataBase.itemEntityDao().getAllItemEntity()
    }

    suspend fun insertItem(itemEntity: ItemEntity) {
        appDataBase.itemEntityDao().insert(itemEntity)
    }

    suspend fun deleteItem(itemEntity: ItemEntity) {
        appDataBase.itemEntityDao().delete(itemEntity)
    }

}