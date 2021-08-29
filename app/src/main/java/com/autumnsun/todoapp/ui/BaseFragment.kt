package com.autumnsun.todoapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.autumnsun.todoapp.arch.ToBuyViewModel
import com.autumnsun.todoapp.database.AppDataBase

abstract class BaseFragment : Fragment() {


    protected val mainActivity: MainActivity
        get() = activity as MainActivity

    protected val appDataBase: AppDataBase
        get() = AppDataBase.getDataBase(requireActivity())

    //    protected val sharedViewModel: ToBuyViewModel by viewModels() //fragment yok edildiginde yok olur
    protected val sharedViewModel: ToBuyViewModel by activityViewModels() //activity yok edildiginde yok olur

    //region Navigation helper methods
    protected fun navigateUp() {
        mainActivity.navController.navigateUp()
    }

    protected fun navigateViaNavGraph(actionId: Int) {
        mainActivity.navController.navigate(actionId)
    }
    //endregion Navigation helper methods
}