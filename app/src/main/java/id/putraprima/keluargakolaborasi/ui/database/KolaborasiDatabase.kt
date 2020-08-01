package id.putraprima.keluargakolaborasi.ui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Reward::class,Challenge::class,History::class],version = 3,exportSchema = false)
abstract class KolaborasiDatabase : RoomDatabase(){
    abstract val RewardDao:RewardDao
    abstract val ChallengeDao:ChallengeDao
    abstract val HistoryDao:HistoryDao

    companion object{
        @Volatile
        private var INSTANCE : KolaborasiDatabase?=null
        fun getInstance(context: Context) :KolaborasiDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KolaborasiDatabase::class.java,
                        "kolaborasi_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}