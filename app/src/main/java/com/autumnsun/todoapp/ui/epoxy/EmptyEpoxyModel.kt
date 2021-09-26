package com.autumnsun.todoapp.ui.epoxy

import com.autumnsun.todoapp.R
import com.autumnsun.todoapp.databinding.EpoxyModelLoadingBinding


/*
 Created by Fatih Kurcenli on 9/26/2021
*/


class EmptyEpoxyModel :
    ViewBindingKotlinModel<EpoxyModelLoadingBinding>(R.layout.empty_model_state) {
    override fun EpoxyModelLoadingBinding.bind() {
    }
}