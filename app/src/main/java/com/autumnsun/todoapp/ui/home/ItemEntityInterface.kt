package com.autumnsun.todoapp.ui.home

import com.autumnsun.todoapp.database.entity.ItemEntity

interface ItemEntityInterface {

    fun onDeleteItemEntity(itemEntity: ItemEntity)
    fun onBumpPriority(itemEntity: ItemEntity)
}