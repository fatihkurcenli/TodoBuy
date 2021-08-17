package com.autumnsun.todoapp.ui

import androidx.fragment.app.Fragment
import com.autumnsun.todoapp.database.AppDataBase

abstract class BaseFragment : Fragment() {



    protected val mainActivity: MainActivity
        get() = activity as MainActivity

    protected val appDataBase: AppDataBase
        get() = AppDataBase.getDataBase(requireActivity())
}