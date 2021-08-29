package com.autumnsun.todoapp.ui.addItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.autumnsun.todoapp.R
import com.autumnsun.todoapp.database.entity.ItemEntity
import com.autumnsun.todoapp.databinding.FragmentAddItemEntityBinding
import com.autumnsun.todoapp.ui.BaseFragment
import java.util.*

class AddItemEntityFragment : BaseFragment() {

    private var _binding: FragmentAddItemEntityBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemEntityBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            saveItemEntityDatabase()
        }

    }

    private fun saveItemEntityDatabase() {
        val itemTitle = binding.titleEditText.text.toString().trim()
        if (itemTitle.isEmpty()) {
            binding.titleTextField.error = "Requeired field"
            return
        }
        binding.titleTextField.error = null
        val itemDescription = binding.descriptionEditText.text.toString().trim()
        val itemPriority = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioButtonLow -> 1
            R.id.radioButtonMedium -> 2
            R.id.radioButtonHigh -> 3
            else -> 0
        }

        val itemEntity = ItemEntity(
            id = UUID.randomUUID().toString(),
            title = itemTitle,
            description = itemDescription,
            priority = itemPriority,
            createdAt = System.currentTimeMillis(),
            categoryId = ""//todo update this later
        )

        sharedViewModel.insertItem(itemEntity)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}