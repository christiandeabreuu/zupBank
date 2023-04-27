package com.zup.zupbank.ui.preLogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.zup.zupbank.R
import com.zup.zupbank.common.extension.findFragmentContainerNavController
import com.zup.zupbank.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController by lazy {
        findFragmentContainerNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.mainToolbar)
        this.appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, _, _ ->
//            when (destination.id) {
//                R.id.loginFragment -> hideBottomNav()
//                R.id.registerFragment -> hideBottomNav()
//                R.id.profileFragment -> hideBottomNav()
//                else -> showBottomNav()
//            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
