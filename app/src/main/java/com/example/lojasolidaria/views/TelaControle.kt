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
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.draw.alpha
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
import com.example.lojasolidaria.ui.theme.LojaSolidariaTheme
import com.example.lojasolidaria.viewModel.MeuViewModel
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.window.DialogProperties
import java.time.Instant
import java.time.ZoneOffset
import java.time.LocalDate
import java.time.format.DateTimeFormatter


private val formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy")

@OptIn(ExperimentalMaterial3Api::class)
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
    var modoEdicao by remember { mutableStateOf(false) }
    var mostrarCalendario by remember { mutableStateOf(false) }
    var confirmarVoltar by remember { mutableStateOf(false) }
    var confirmarSalvar by remember { mutableStateOf(false) }
    var erroEdicao by remember { mutableStateOf("") }
    var dataSelecionada by remember { mutableStateOf(LocalDate.now()) }
    val edicoes = remember { mutableStateMapOf<Int, String>() }
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 2.dp, start = 2.dp, end = 2.dp)
            .background(color = Color(0xFFC4BFBE)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(start = 8.dp,top=16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Loja Solidária Irmã Dulce")
        }
        Spacer(modifier = Modifier.height(34.dp))
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
        Spacer(modifier = Modifier.height(16.dp))
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
        Spacer(modifier = Modifier.height(35.dp))
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
            val houveAlteracao = viewModel.listaRegistros.any { registro ->
                edicoes[registro.id]?.let { it != kotlin.math.abs(registro.quantidade).toString() } == true
            }
            val edicoesValidas = edicoes.values.all { (it.toIntOrNull() ?: 0) > 0 }
            val podeSalvar = houveAlteracao && edicoesValidas
            val sairDaEdicao: () -> Unit = {
                edicoes.clear()
                erroEdicao = ""
                modoEdicao = false
            }
            val tentarVoltar: () -> Unit = {
                if (houveAlteracao) {
                    confirmarVoltar = true
                } else {
                    sairDaEdicao()
                }
            }
            Button(
                onClick = {
                    dataSelecionada = LocalDate.now()
                    sairDaEdicao()
                    viewModel.carregarRegistros(dataSelecionada.toString())
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
                    onDismissRequest = {
                        if (modoEdicao) {
                            tentarVoltar()
                        } else {
                            visibilidadeLista = false
                        }
                    },
                    properties = DialogProperties(usePlatformDefaultWidth = false),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Registros: ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp
                            )
                        }
                    },
                    text = {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Data: ${dataSelecionada.format(formatoData)}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    modifier = Modifier.width(125.dp)
                                )
                                Box(
                                    modifier = Modifier.weight(1f),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    IconButton(
                                        onClick = { mostrarCalendario = true },
                                        enabled = !modoEdicao
                                    ) {
                                        Text(
                                            text = "📅",
                                            fontSize = 22.sp,
                                            modifier = Modifier.alpha(if (modoEdicao) 0.5f else 1f)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier.width(96.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    if (!modoEdicao) {
                                        val podeEditar = viewModel.listaRegistros.isNotEmpty()
                                        IconButton(
                                            onClick = { modoEdicao = true },
                                            enabled = podeEditar
                                        ) {
                                            Text(
                                                text = "✏️",
                                                fontSize = 22.sp,
                                                modifier = Modifier.alpha(if (podeEditar) 0.8f else 0.3f)
                                            )
                                        }
                                    }
                                }
                            }

                            if (viewModel.listaRegistros.isEmpty()) {
                                Text("Nenhum registro nesta data.")
                            } else {
                                LazyColumn(modifier = Modifier.heightIn(max = 300.dp)) {
                                    item {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 6.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text("ID", modifier = Modifier.width(35.dp), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                            Text("Data", modifier = Modifier.width(90.dp), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                            Text("Categoria", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                            Text("Qtd.", modifier = Modifier.width(96.dp), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                        }
                                    }
                                    items(viewModel.listaRegistros, key = { it.id }) { registro ->
                                        val entrada = registro.tipo == "entrada"
                                        val corQuantidade = if (entrada) Color(0xFF2E7D32) else Color.Red
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 6.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text("${registro.id}", modifier = Modifier.width(35.dp), fontSize = 14.sp)
                                            Text(
                                                text = LocalDate.parse(registro.dataEvento).format(formatoData),
                                                modifier = Modifier.width(90.dp),
                                                fontSize = 14.sp
                                            )
                                            Text(
                                                text = if (entrada) registro.categoria else "Saída",
                                                modifier = Modifier.weight(1f),
                                                fontSize = 14.sp
                                            )
                                            if (modoEdicao) {
                                                OutlinedTextField(
                                                    value = edicoes[registro.id]
                                                        ?: kotlin.math.abs(registro.quantidade).toString(),
                                                    onValueChange = { novo ->
                                                        if (novo.length <= 5 && novo.all { it.isDigit() }) {
                                                            edicoes[registro.id] = novo
                                                            erroEdicao = ""
                                                        }
                                                    },
                                                    prefix = {
                                                        Text(
                                                            text = if (entrada) "+" else "-",
                                                            color = corQuantidade,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    },
                                                    singleLine = true,
                                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                                    textStyle = TextStyle(fontSize = 14.sp),
                                                    modifier = Modifier.width(96.dp)
                                                )
                                            } else {
                                                Text(
                                                    text = if (entrada) "+${registro.quantidade}" else "${registro.quantidade}",
                                                    modifier = Modifier.width(96.dp),
                                                    color = corQuantidade,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            if (erroEdicao.isNotEmpty()) {
                                Text(
                                    text = erroEdicao,
                                    color = Color.Red,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    confirmButton = {
                        if (modoEdicao) {
                            Row {
                                OutlinedButton(onClick = { tentarVoltar() }) {
                                    Text("Voltar", fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = { confirmarSalvar = true },
                                    enabled = podeSalvar,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF4A5984),
                                        contentColor = Color.White,
                                        disabledContainerColor = Color(0xFFBDBDBD),
                                        disabledContentColor = Color(0xFF757575)
                                    )
                                ) {
                                    Text("Salvar", fontWeight = FontWeight.Bold)
                                }
                            }
                        } else {
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
                    }
                )
                if (mostrarCalendario) {
                    val estadoData = rememberDatePickerState(
                        initialSelectedDateMillis = dataSelecionada
                            .atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
                    )
                    DatePickerDialog(
                        onDismissRequest = { mostrarCalendario = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    estadoData.selectedDateMillis?.let { milissegundos ->
                                        dataSelecionada = Instant.ofEpochMilli(milissegundos)
                                            .atZone(ZoneOffset.UTC).toLocalDate()
                                        viewModel.carregarRegistros(dataSelecionada.toString())
                                    }
                                    mostrarCalendario = false
                                }
                            ) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { mostrarCalendario = false }) {
                                Text("Cancelar")
                            }
                        }
                    ) {
                        DatePicker(state = estadoData)
                    }
                }
                if (confirmarVoltar) {
                    AlertDialog(
                        onDismissRequest = { confirmarVoltar = false },
                        title = { Text("Descartar alterações?", fontWeight = FontWeight.Bold) },
                        text = { Text("Se voltar agora, as alterações não salvas serão perdidas.") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    confirmarVoltar = false
                                    sairDaEdicao()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4A5984),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Descartar")
                            }
                        },
                        dismissButton = {
                            OutlinedButton(onClick = { confirmarVoltar = false }) {
                                Text("Continuar editando")
                            }
                        }
                    )
                }
                if (confirmarSalvar) {
                    AlertDialog(
                        onDismissRequest = { confirmarSalvar = false },
                        title = { Text("Salvar alterações?", fontWeight = FontWeight.Bold) },
                        text = { Text("Deseja salvar as alterações ou descartá-las e voltar?") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    confirmarSalvar = false
                                    if (viewModel.salvarEdicoes(edicoes.toMap())) {
                                        sairDaEdicao()
                                        Toast.makeText(
                                            contexto,
                                            "Alterações salvas",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        erroEdicao = "Saldo insuficiente para essa alteração."
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4A5984),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Salvar")
                            }
                        },
                        dismissButton = {
                            OutlinedButton(
                                onClick = {
                                    confirmarSalvar = false
                                    sairDaEdicao()
                                }
                            ) {
                                Text("Descartar")
                            }
                        }
                    )
                }
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