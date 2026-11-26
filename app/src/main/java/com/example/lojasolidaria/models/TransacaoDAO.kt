package com.example.lojasolidaria.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransacaoDAO {

    @Insert
    suspend fun cadastrar(transacao: Transacao)
    @Query("SELECT  SUM (quantidade) from Transacao")
    suspend fun saldoEstoque(): Int?

    @Query("SELECT * FROM Transacao ORDER BY id ")
    suspend fun listarTransacoes(): List<Transacao>// adicionei essa consulta para listar as transações em ordem


}