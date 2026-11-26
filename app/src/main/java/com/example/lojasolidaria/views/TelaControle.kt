package com.example.lojasolidaria.views


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lojasolidaria.models.Transacao
import com.example.lojasolidaria.ui.theme.LojaSolidariaTheme
import com.example.lojasolidaria.viewModel.MeuViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


private val formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy")

@Composable
fun TelaControle(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MeuViewModel
) {
    val contexto = LocalContext.current
    var visibilidadeAlertaC by remember {mutableStateOf(false)}
    var visibilidadeAlertaR by remember {mutableStateOf(false)}
    var visibilidadeLista by remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 2.dp, start = 2.dp, end = 2.dp)
            .background(color = Color(0xFFC4BFBE)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Loja Solidária Irmã Dulce")
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Controle de Estoque",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Quantidade de Peças:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Box(
            modifier = Modifier
                .size(width = 150.dp, height = 50.dp)
                .background(color = Color(0xFFBDA685)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${viewModel.saldoEstoque}",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {visibilidadeAlertaC = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBDA685),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Cadastrar",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            if (visibilidadeAlertaC == true) {
                AlertDialog(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            (Text(
                                text = "Cadastrar",
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp
                            )
                                    )
                        }
                    },
                    text = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Categoria:",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (viewModel.categoriaLida == "Masculino"),
                                    onClick = { viewModel.categoriaLida = "Masculino" }
                                )
                                Text("Masculino")
                                Spacer(modifier = Modifier.width(50.dp))


                                RadioButton(
                                    selected = (viewModel.categoriaLida == "Feminino"),
                                    onClick = { viewModel.categoriaLida = "Feminino" }
                                )
                                Text("Feminino")
                            }
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {


                                RadioButton(
                                    selected = (viewModel.categoriaLida == "Infantil"),
                                    onClick = {viewModel.categoriaLida = "Infantil" }
                                )
                                Text("Infantil ")
                                Spacer(modifier = Modifier.width(65.dp))


                                RadioButton(
                                    selected = (viewModel.categoriaLida == "Calçados"),
                                    onClick = {viewModel.categoriaLida = "Calçados" }
                                )
                                Text("Calçados")
                            }
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {


                                RadioButton(
                                    selected = (viewModel.categoriaLida == "Diversos"),
                                    onClick = {viewModel.categoriaLida = "Diversos" }
                                )
                                Text("Diversos")
                                Spacer(modifier = Modifier.width(10.dp))


                                RadioButton(
                                    selected = (viewModel.categoriaLida == "Não Especificado"),
                                    onClick = {viewModel.categoriaLida = "Não Especificado" }
                                )
                                Text("Não Especificado")
                            }
                            Spacer(modifier = Modifier.height(50.dp))
                            Text(
                                text = "Quantidade:",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            TextField(
                                value = viewModel.quantidadeLida,
                                onValueChange = { viewModel.quantidadeLida = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                placeholder = { Text("Digite apenas números.") }
                            )
                        }
                    },
                    onDismissRequest = {visibilidadeAlertaC = false },
                    confirmButton = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = {visibilidadeAlertaC = false },
                                modifier = Modifier.weight(1f),
                                border = BorderStroke(1.dp, Color(0xFF4A5984)),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color(0xFF4A5984)
                                )
                            ) {
                                Text(
                                    text = "Voltar",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp
                                )
                            }
                            Button(
                                onClick = {
                                    if (viewModel.categoriaLida.isBlank()) {
                                        Toast.makeText(
                                            contexto,
                                            "Selecione uma categoria",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else if (viewModel.quantidadeLida.isBlank()) {
                                        Toast.makeText(
                                            contexto,
                                            "Digite um valor na quantidade",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    else{
                                        viewModel.cadastrarEstoque()
                                        Toast.makeText(
                                            contexto,
                                            "Cadastrado com sucesso",
                                            Toast.LENGTH_SHORT
                                        ).show()}
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4A5984),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "Confirmar",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {visibilidadeAlertaR = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBDA685),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Remover",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            if (visibilidadeAlertaR == true) {
                AlertDialog(
                    title = {
                        (Text(
                            text = "Remover:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp
                        ))
                    },
                    text = {


                        Column() {
                            Text(
                                text = "Quantidade:",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            TextField(
                                value = viewModel.quantidadeLida,
                                onValueChange = {viewModel.quantidadeLida = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                placeholder = { Text("Digite apenas números.") }
                            )


                            if (viewModel.mensagemErro.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = viewModel.mensagemErro,
                                    color = Color.Red,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    onDismissRequest = {visibilidadeAlertaR = false },
                    confirmButton = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = {visibilidadeAlertaR = false
                                    viewModel.mensagemErro= ""
                                    viewModel.quantidadeLida= ""
                                },
                                modifier = Modifier.weight(1f),
                                border = BorderStroke(1.dp, Color(0xFF4A5984)),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color(0xFF4A5984)
                                )
                            ) {
                                Text(
                                    text = "Voltar",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp
                                )
                            }
                            Button(
                                onClick = {
                                    if(viewModel.quantidadeLida.isBlank()) {
                                        Toast.makeText(
                                            contexto,
                                            "Digite um valor na quantidade",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    else if (viewModel.removerEstoque()) {
                                        Toast.makeText(
                                            contexto,
                                            "Removido com sucesso",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        visibilidadeAlertaR = false
                                    }
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4A5984),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Confirmar")
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    viewModel.carregarRegistros()
                    visibilidadeLista = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBDA685),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Registros",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            if (visibilidadeLista) {
                AlertDialog(
                    onDismissRequest = { visibilidadeLista = false },
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Registros",
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp
                            )
                        }
                    },
                    text = {
                        if (viewModel.listaRegistros.isEmpty()) {
                            Text("Nenhum registro encontrado.")
                        } else {
                            LazyColumn(modifier = Modifier.heightIn(max = 400.dp)) {
                                item {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 6.dp)
                                    ) {
                                        Text("ID", modifier = Modifier.width(35.dp), fontWeight = FontWeight.Bold)
                                        Text("Data", modifier = Modifier.width(95.dp), fontWeight = FontWeight.Bold)
                                        Text("Categoria", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                                        Text("Qtd.", fontWeight = FontWeight.Bold)
                                    }
                                }
                                items(viewModel.listaRegistros) { registro ->
                                    val entrada = registro.tipo == "entrada"
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 6.dp)
                                    ) {
                                        Text("${registro.id}", modifier = Modifier.width(35.dp))
                                        Text(
                                            text = LocalDate.parse(registro.dataEvento).format(formatoData),
                                            modifier = Modifier.width(95.dp)
                                        )
                                        Text(
                                            text = if (entrada) registro.categoria else "Saída",
                                            modifier = Modifier.weight(1f)
                                        )
                                        Text(
                                            text = if (entrada) "+${registro.quantidade}" else "${registro.quantidade}",
                                            color = if (entrada) Color(0xFF2E7D32) else Color.Red,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = { visibilidadeLista = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4A5984),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Fechar", fontWeight = FontWeight.Bold)
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {navController . navigate ("TelaInicial")
                    //mensagem = ""
                    // loginOk=false
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBDA685),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Sair",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun ControlePreview() {
    val navController = rememberNavController()
    val viewModel : MeuViewModel = viewModel()


    LojaSolidariaTheme {
        TelaControle(navController=navController, viewModel = viewModel)
    }
}