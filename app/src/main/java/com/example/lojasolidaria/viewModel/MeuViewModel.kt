package com.example.lojasolidaria.viewModel

import android.R.attr.name
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.lojasolidaria.models.BDloja_solidaria
import com.example.lojasolidaria.models.Transacao
import com.example.lojasolidaria.models.TransacaoDAO
import kotlinx.coroutines.launch
import java.time.LocalDate


class MeuViewModel(application: Application) :AndroidViewModel(application) {

    private val bd= Room.databaseBuilder(
        context = application,
        klass = BDloja_solidaria::class.java,
        name = "BDloja_solidaria"
    ).build()

    var categoriaLida by mutableStateOf("")
    var quantidadeLida by mutableStateOf(value = "")
    var saldoEstoque by mutableStateOf(0)
    var mensagemErro by mutableStateOf("")



    var categorias =
        mutableListOf(
            "Masculino",
            "Feminino",
            "Infantil",
            "Calçados",
            "Diversos",
            "Não especificado"
        )

    var listaTransacao = mutableListOf<Transacao>()
    var listaRegistros by mutableStateOf<List<Transacao>>(emptyList())

    private val daoTransacao = bd.pegarTransacaoDao()
    var login = "admin"
    var senha = "1234"
    var loginLido by mutableStateOf("")
    var senhaLida by mutableStateOf("")
    var loginOk by mutableStateOf(false)
    var mensagem by mutableStateOf("")

    fun fazerLogin(): Boolean {
        mensagem = ""
        if ((loginLido == login) && (senhaLida == senha)) {
            loginOk = true
            mensagem = "Login com sucesso"
            mensagem = " "


        } else {
            loginOk = false
            if (loginLido != login) {
                mensagem = "Usuário incorreto"
            } else {
                mensagem = "Senha incorreta"
            }
        }
        loginLido = ""
        senhaLida = ""
        return loginOk
    }

    fun cadastrarEstoque() {
        val dataAtual = LocalDate.now().toString()
        val transacao = Transacao(
            tipo = "entrada",
            categoria = categoriaLida,
            quantidade = quantidadeLida.toIntOrNull() ?: 0,
            dataEvento = dataAtual
        )
        listaTransacao.add(transacao)
        viewModelScope.launch {
            daoTransacao.cadastrar(transacao)
            saldoEstoque=daoTransacao.saldoEstoque()?: 0
        }
        categoriaLida = ""
        quantidadeLida = ""
    }

    fun removerEstoque(): Boolean {
        val quantidadeConvertida = quantidadeLida.toIntOrNull() ?: 0
        val dataAtual = LocalDate.now().toString()
        if (saldoEstoque >= quantidadeConvertida) {
            val quantidadeNegativa = -quantidadeConvertida
            val transacao = Transacao(
                tipo = "saída",
                categoria = "SEM CATEGORIA",
                quantidade = quantidadeNegativa,
                dataEvento = dataAtual
            )
            listaTransacao.add(transacao)
            viewModelScope.launch {
                daoTransacao.cadastrar(transacao)
                saldoEstoque=daoTransacao.saldoEstoque()?: 0
            }
            mensagemErro = ""
            categoriaLida = ""
            quantidadeLida = ""
            return true
        } else {
            mensagemErro = "Saldo Insuficiente."
            return false
        }
    }
    fun carregarRegistros() {
        viewModelScope.launch {
            listaRegistros = daoTransacao.listarTransacoes()
        }
    }
    init {
        viewModelScope.launch {
            saldoEstoque=daoTransacao.saldoEstoque()?: 0
        }

    }
}