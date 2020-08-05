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

    @Query("SELECT SUM(historyPoint) as total from history")
    fun getCurrentPointLive():LiveData<Int>

    @Query("Select count(*) as total from history")
    fun countHistory(): LiveData<Int>

    @Query("Select count(*) as total from history where historyPoint > 0")
    fun countChallengeSelesai(): LiveData<Int>

    @Query("Select SUM(historyPoint) as total from history where historyPoint > 0")
    fun countTotalPoin(): LiveData<Int>

//    @Query("Select count(historyPoint) as total from history")
//    fun countTotalPoin(): LiveData<Int>

    @Query("Select count(*) as total from history where historyPoint < 0")
    fun countRewardSelesai(): LiveData<Int>
}