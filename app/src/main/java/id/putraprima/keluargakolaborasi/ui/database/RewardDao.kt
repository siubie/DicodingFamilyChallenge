package id.putraprima.keluargakolaborasi.ui.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RewardDao {
    @Insert
    fun insert(reward: Reward)

    @Update
    fun update(reward: Reward)

    @Query("Select * from reward where rewardId = :key")
    fun get(key:Long): Reward?

    @Query("Delete From reward")
    fun clear()

    @Query("Select * from reward order by rewardId DESC")
    fun getAllReward() : LiveData<List<Reward>>

    @Query("Select count(*) as total from reward")
    fun countReward() : LiveData<Int>

}