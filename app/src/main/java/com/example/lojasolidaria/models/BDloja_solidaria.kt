package com.example.lojasolidaria.models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Transacao::class],
    version = 3,
)

abstract class BDloja_solidaria: RoomDatabase() {
    abstract fun pegarTransacaoDao(): TransacaoDAO
}