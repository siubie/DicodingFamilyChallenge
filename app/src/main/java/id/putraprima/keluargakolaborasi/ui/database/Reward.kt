package id.putraprima.keluargakolaborasi.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reward")
data class Reward (
    @PrimaryKey(autoGenerate = true)
    var rewardId : Long = 0L,
    @ColumnInfo(name = "rewardName")
    var rewardName : String = "",
    @ColumnInfo(name = "rewardPrice")
    var rewardPrice : Long = 0
)
