package id.putraprima.keluargakolaborasi.ui.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChallengeDao {
    @Insert
    fun insert(challenge: Challenge)

    @Update
    fun update(challenge: Challenge)

    @Query("Select * from challenge where challengeId = :key")
    fun get(key:Long): Challenge?

    @Query("DELETE from challenge where challengeId = :key")
    fun deleteById(key:Long)

    @Query("Delete From challenge")
    fun clear()

    @Query("Select * from challenge order by challengeId DESC")
    fun getAll() : LiveData<List<Challenge>>

    @Query("Select count(*) as total from challenge")
    fun countChallenge() : LiveData<Int>

    @Query("Select * from challenge order by challengeId DESC LIMIT 1")
    fun getLatestChallenge() : LiveData<Challenge>
}
