package com.autumnsun.todoapp.ui.home

import android.content.res.ColorStateList
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyController
import com.autumnsun.todoapp.R
import com.autumnsun.todoapp.database.entity.ItemEntity
import com.autumnsun.todoapp.databinding.ModelHeaderItemBinding
import com.autumnsun.todoapp.databinding.ModelItemEntityBinding
import com.autumnsun.todoapp.ui.epoxy.EmptyEpoxyModel
import com.autumnsun.todoapp.ui.epoxy.LoadingEpoxyModel
import com.autumnsun.todoapp.ui.epoxy.ViewBindingKotlinModel

class HomeEpoxyController(private val itemEntityInterface: ItemEntityInterface) :
    EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var itemEntityList = ArrayList<ItemEntity>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }


    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading_state").addTo(this)
            return
        }
        if (itemEntityList.isEmpty()) {
            return EmptyEpoxyModel().id("empty_state").addTo(this)
        }

        var currentPriority: Int = -1

        itemEntityList.sortByDescending {
            it.priority
        }

        itemEntityList.forEach { item ->
            if (item.priority != currentPriority) {
                currentPriority = item.priority
                val text = getHeaderTextPriority(currentPriority)
                HeaderEpoxyModel(text).id(text).addTo(this)
            }
            ItemEntityEpoxyModel(item, itemEntityInterface).id(item.id).addTo(this)
        }
    }

    private fun getHeaderTextPriority(priority: Int): String {
        return when (priority) {
            1 -> "Low"
            2 -> "Medium"
            else -> "High"
        }
    }

    data class ItemEntityEpoxyModel(
        val itemEntity: ItemEntity,
        val itemEntityInterface: ItemEntityInterface
    ) : ViewBindingKotlinModel<ModelItemEntityBinding>(R.layout.model_item_entity) {
        override fun ModelItemEntityBinding.bind() {
            titleTextView.text = itemEntity.title
            if (itemEntity.description == null) {
                descriptionTextView.isGone = true
            } else {
                descriptionTextView.isVisible = true
                descriptionTextView.text = itemEntity.description
            }

            priorityTextView.setOnClickListener {
                itemEntityInterface.onBumpPriority(itemEntity)
            }

            val colorRes = when (itemEntity.priority) {
                1 -> android.R.color.holo_green_light
                2 -> android.R.color.holo_orange_dark
                3 -> android.R.color.holo_red_dark
                else -> R.color.purple_700
            }
            val color = ContextCompat.getColor(root.context, colorRes)
            priorityTextView.setBackgroundColor(color)
            cardView.setStrokeColor(ColorStateList.valueOf(color))
        }

    }

    data class HeaderEpoxyModel(val headerText: String) :
        ViewBindingKotlinModel<ModelHeaderItemBinding>(R.layout.model_header_item) {
        override fun ModelHeaderItemBinding.bind() {
            headerTitle.text = headerText
            headerTitle.setOnClickListener {
                if (headerText == "Low") {
                    Toast.makeText(root.context, "Hello from: Low level", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}