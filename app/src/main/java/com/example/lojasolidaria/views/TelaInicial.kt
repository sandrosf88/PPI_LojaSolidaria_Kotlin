package com.example.lojasolidaria.views


import android.R.attr.top
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lojasolidaria.R
import com.example.lojasolidaria.ui.theme.LojaSolidariaTheme
import com.example.lojasolidaria.viewModel.MeuViewModel



@Composable
fun TelaInicial(modifier: Modifier = Modifier,
                navController: NavHostController,
                viewModel: MeuViewModel
) {
    val contexto = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFFE1D0B6))
            .padding(top = 60.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,


        ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo Loja",
                modifier = Modifier
                    .width (300.dp)
                    .padding(top=24.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Login:",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            TextField(
                value = viewModel.loginLido,
                onValueChange = { viewModel.loginLido = it },
                modifier = Modifier.width(150.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Senha:",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            TextField(
                value = viewModel.senhaLida,
                onValueChange = { viewModel.senhaLida = it },
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                modifier = Modifier.width(150.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (viewModel.fazerLogin()) {
                    Toast.makeText(
                        contexto,
                        "Login com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate("TelaControle")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFBDA685),
                contentColor = Color.Black
            )


        ) {
            Text(
                text = "Entrar",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Text(
            text = viewModel.mensagem,
            color = Color.Blue,
            fontWeight = FontWeight.Bold
        )




        /*Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.irma),
                contentDescription = "Irmã",
                modifier = Modifier.size(300.dp)
                //.border(width = 3.dp, Color.Black)
            )
        }*/
    }
}




@Preview(showBackground = true)
@Composable
fun TelaInicialPreview() {
    val navController = rememberNavController()
    val viewModel : MeuViewModel = viewModel()
    LojaSolidariaTheme {
        TelaInicial(navController=navController,
            viewModel = viewModel)
    }
}
