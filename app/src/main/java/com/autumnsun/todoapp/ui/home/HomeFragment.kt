package com.autumnsun.todoapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.autumnsun.todoapp.R
import com.autumnsun.todoapp.database.entity.ItemEntity
import com.autumnsun.todoapp.databinding.FragmentHomeBinding
import com.autumnsun.todoapp.ui.BaseFragment


class HomeFragment : BaseFragment(), ItemEntityInterface {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            navigateViaNavGraph(R.id.action_homeFragment_to_addItemEntityFragment)
        }

        val controller = HomeEpoxyController(this)
        binding.epoxyRecyclerView.setController(controller)


        sharedViewModel.itemEntitiesLiveData.observe(viewLifecycleOwner) { itemEntitiyList ->
            controller.itemEntityList = itemEntitiyList as ArrayList<ItemEntity>
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDeleteItemEntity(itemEntity: ItemEntity) {
        //todo implements
    }

    override fun onBumpPriority(itemEntity: ItemEntity) {
        TODO("Not yet implemented")
    }

}