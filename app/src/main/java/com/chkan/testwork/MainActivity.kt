package com.chkan.testwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.chkan.testwork.utils.Constans

open class MainActivity : AppCompatActivity() {
    private var back_pressed : Long = 0
    private lateinit var navController: NavController
    var arg1 : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
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

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Constans.TAG, "MainActivity ->onDestroy()")
    }
   open fun onMenuClicked (id:Int){
       Log.d(Constans.TAG, "MainActivity ->onMenuClicked()")
       arg1 = id
       val bundle = Bundle()
       bundle.putInt("arg", id)
       navController.navigate(R.id.menu_frag,bundle)
    }
    open fun onItemsClicked (id:Int){
        Log.d(Constans.TAG, "MainActivity ->onItemsClicked()")
        val bundle = Bundle()
        bundle.putInt("argCat", arg1)
        bundle.putInt("argItem", id)
        navController.navigate(R.id.items_frag,bundle)
    }
}