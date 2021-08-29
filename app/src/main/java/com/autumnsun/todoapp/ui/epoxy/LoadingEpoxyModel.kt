package com.autumnsun.todoapp.ui.epoxy

import com.autumnsun.todoapp.R
import com.autumnsun.todoapp.databinding.EpoxyModelLoadingBinding

class LoadingEpoxyModel :
    ViewBindingKotlinModel<EpoxyModelLoadingBinding>(R.layout.epoxy_model_loading) {
    override fun EpoxyModelLoadingBinding.bind() {
        //nothing to do because of loading view
    }
}