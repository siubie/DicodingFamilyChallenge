package id.putraprima.keluargakolaborasi.ui.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    fun insert(history: History)

    @Query("Select * from history where historyId = :key")
    fun get(key: Long): History?

    @Query("DELETE from history where historyId = :key")
    fun deleteById(key: Long)

    @Query("Delete From history")
    fun clear()

    @Query("Select * from history order by historyId DESC")
    fun getAll(): LiveData<List<History>>

    @Query("SELECT SUM(historyPoint) as total from history")
    fun getCurrentPoint(): Int

    @Query("Select count(*) as total from history")
    fun countHistory(): LiveData<Int>
}