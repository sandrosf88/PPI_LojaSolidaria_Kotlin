package com.example.lojasolidaria.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "Transacao")
data class Transacao (
    @PrimaryKey(true)
                      val id: Int=0,
                      val tipo: String,
                      val categoria: String,
                      val quantidade: Int,
                     var dataEvento: String
)