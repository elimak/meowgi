package com.elimak.krikey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        // Navigation component
        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(toolbar, navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if(destination.getId()==R.id.mainTabs){
                getSupportActionBar()!!.setIcon(R.drawable.menu_icon);
            } else {
                getSupportActionBar()!!.setIcon(null);
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null as DrawerLayout?)
    }

}
