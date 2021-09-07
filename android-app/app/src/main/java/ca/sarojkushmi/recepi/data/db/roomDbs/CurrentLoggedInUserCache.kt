package ca.sarojkushmi.recepi.data.db.roomDbs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.sarojkushmi.recepi.data.db.DAOs.UserAccountDAO
import ca.sarojkushmi.recepi.data.models.roomModels.UserModel
import ca.sarojkushmi.recepi.data.models.roomModels.UserPost

@Database(entities= [UserModel::class, UserPost::class], version = 11, exportSchema = false)
abstract class CurrentLoggedInUserCache : RoomDatabase() {
    abstract fun userAccountDAO() : UserAccountDAO

    companion object{
        @Volatile
        private var INSTANCE: CurrentLoggedInUserCache ?= null

        fun getInstance(context: Context) : CurrentLoggedInUserCache {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, CurrentLoggedInUserCache::class.java, "currentLoggedInUserDbv11").addMigrations(
                        MIGRATION_10_11).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}