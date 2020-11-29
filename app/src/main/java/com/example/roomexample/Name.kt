package com.example.roomexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "name_table")
class Name(@PrimaryKey  @ColumnInfo(name = "name") val name: String)