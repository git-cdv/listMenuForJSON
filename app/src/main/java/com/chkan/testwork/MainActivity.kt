package com.chkan.testwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private var back_pressed : Long = 0
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)


    }

    //переопределяем нажатие кнопки назад - чтобы выходило а не переходило по стеку
    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            moveTaskToBack(true)
            finish()
        } else {
            Toast.makeText(
                this, "Для выхода нажмите \"назад\" еще раз",
                Toast.LENGTH_SHORT
            ).show();
            back_pressed = System.currentTimeMillis();
        }
    }

}