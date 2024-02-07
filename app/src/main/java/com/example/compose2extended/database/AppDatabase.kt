package com.example.compose2extended.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database
    (entities = [User::class],version=1, exportSchema = false)
abstract class AppDatabase:RoomDatabase(){
    abstract fun userDao():UserDao


    companion object{
        @Volatile
        private var INSTANCE: AppDatabase?=null
        fun getDatabase(context: Context):AppDatabase{
            //Log.d("AppDatabase","Initializing database")
            return INSTANCE?: synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database").fallbackToDestructiveMigration().build()
                    INSTANCE=instance

                }
                return instance

            }
        }
    }
}