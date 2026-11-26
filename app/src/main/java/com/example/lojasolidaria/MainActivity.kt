package com.example.lojasolidaria


import com.example.lojasolidaria.viewModel.MeuViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lojasolidaria.ui.theme.LojaSolidariaTheme

import com.example.lojasolidaria.views.TelaControle
import com.example.lojasolidaria.views.TelaInicial


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LojaSolidariaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val viewModel: MeuViewModel = viewModel()
                    NavHost(
                        navController = navController,
                        startDestination = "TelaInicial"
                    ) {
                        composable(route = "TelaInicial") {
                            TelaInicial(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                        composable(route = "TelaControle") {
                            TelaControle(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                        /* Controle(
                             modifier = Modifier.padding(innerPadding)
                         )*/
                    }
                }
            }
        }
    }
}

