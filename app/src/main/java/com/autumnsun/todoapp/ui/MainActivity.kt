package com.autumnsun.todoapp.ui

import android.app.Activity
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.autumnsun.todoapp.R
import com.autumnsun.todoapp.arch.ToBuyViewModel
import com.autumnsun.todoapp.database.AppDataBase

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: ToBuyViewModel by viewModels()
        viewModel.init(AppDataBase.getDataBase(this))

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager =
            application.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }

    fun showKeyboard() {
        val inm: InputMethodManager =
            application.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}