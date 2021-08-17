package com.autumnsun.todoapp.arch

import com.autumnsun.todoapp.database.AppDataBase
import com.autumnsun.todoapp.database.entity.ItemEntity

class ToBuyRepository(
    private val appDataBase: AppDataBase
) {

    fun getAllItems(): List<ItemEntity> {
        return appDataBase.itemEntityDao().getAllItemEntity()
    }

    fun insertItem(itemEntity: ItemEntity) {
        appDataBase.itemEntityDao().insert(itemEntity)
    }

    fun deleteItem(itemEntity: ItemEntity) {
        appDataBase.itemEntityDao().delete(itemEntity)
    }

}