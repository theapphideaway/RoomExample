package com.example.roomexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Name::class], version = 1, exportSchema = false)
public abstract class NameRoomDatabase: RoomDatabase() {

    abstract fun nameDao(): NameDao

    private class NameDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.nameDao()

                    // Delete all content here.
                    wordDao.deleteAll()

                    // Add sample words.
                    var word = Name("Mike")
                    wordDao.insert(word)
                    word = Name("Jane")
                    wordDao.insert(word)
                    word = Name("Kevin")
                    wordDao.insert(word)
                    word = Name("Tanya")
                    wordDao.insert(word)
                }
            }
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: NameRoomDatabase? = null

        fun getDatabase(
            context: Context,
        scope: CoroutineScope): NameRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NameRoomDatabase::class.java,
                    "name_database"
                )
                    .addCallback(NameDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}