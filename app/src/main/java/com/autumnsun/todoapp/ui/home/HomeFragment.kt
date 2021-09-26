package com.autumnsun.todoapp.ui.home

import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyTouchHelper
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

        //setup swip-to-delete item
        EpoxyTouchHelper.initSwiping(binding.epoxyRecyclerView).right()
            .withTarget(HomeEpoxyController.ItemEntityEpoxyModel::class.java)
            .andCallbacks(object :
                EpoxyTouchHelper.SwipeCallbacks<HomeEpoxyController.ItemEntityEpoxyModel>() {
                override fun onSwipeCompleted(
                    model: HomeEpoxyController.ItemEntityEpoxyModel?,
                    itemView: View?,
                    position: Int,
                    direction: Int
                ) {
                    val itemThatWasRemoved = model?.itemEntity ?: return
                    sharedViewModel.deleteItem(itemThatWasRemoved)
                }


                override fun onSwipeProgressChanged(
                    model: HomeEpoxyController.ItemEntityEpoxyModel?,
                    itemView: View?,
                    swipeProgress: Float,
                    canvas: Canvas?
                ) {
                    super.onSwipeProgressChanged(model, itemView, swipeProgress, canvas)
                    if (swipeProgress > 0.15) {
                        itemView?.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.delete_color
                            )
                        )
                    } else {
                        itemView?.setBackgroundColor(Color.WHITE)
                    }
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDeleteItemEntity(itemEntity: ItemEntity) {
        sharedViewModel.deleteItem(itemEntity)
    }

    override fun onBumpPriority(itemEntity: ItemEntity) {
        TODO("Not yet implemented")
    }

}