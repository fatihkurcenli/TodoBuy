package com.autumnsun.todoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.room.Room
import androidx.room.RoomDatabase
import com.autumnsun.todoapp.R
import com.autumnsun.todoapp.arch.ToBuyViewModel
import com.autumnsun.todoapp.database.AppDataBase

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: ToBuyViewModel by viewModels()
        viewModel.init(AppDataBase.getDataBase(this))

    }
}