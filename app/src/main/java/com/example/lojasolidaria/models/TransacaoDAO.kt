package com.example.lojasolidaria.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TransacaoDAO {

    @Insert
    suspend fun cadastrar(transacao: Transacao)
    @Query("SELECT  SUM (quantidade) from Transacao")
    suspend fun saldoEstoque(): Int?

    @Query("SELECT * FROM Transacao WHERE dataEvento = :data ORDER BY id ")
    suspend fun listarPorData(data: String): List<Transacao>

    @Update
    suspend fun atualizar(transacao: Transacao)


}