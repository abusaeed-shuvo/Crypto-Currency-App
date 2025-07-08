package com.github.abusaeed_shuvo.cryptocurrencyapp.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.github.abusaeed_shuvo.cryptocurrencyapp.R
import com.github.abusaeed_shuvo.cryptocurrencyapp.databinding.ActivityMainBinding
import com.google.android.material.color.DynamicColors

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var navController: NavController
	private lateinit var appBarConfiguration: AppBarConfiguration


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
			DynamicColors.applyToActivityIfAvailable(this)
		}
		enableEdgeToEdge()
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)
		ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
			val systemBars = insets.getInsets(
				WindowInsetsCompat.Type.systemBars()
						or WindowInsetsCompat.Type.displayCutout()
						or WindowInsetsCompat.Type.ime()
			)
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}

		val navHostFragment =
			supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHost

		navController = navHostFragment.navController

		appBarConfiguration = AppBarConfiguration(
			navController.graph
		)

		setupActionBarWithNavController(navController, appBarConfiguration)
		supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
	}
}