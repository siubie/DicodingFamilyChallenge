package id.putraprima.keluargakolaborasi.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge")
data class Challenge(
    @PrimaryKey(autoGenerate = true)
    var challengeId: Long = 0L,
    @ColumnInfo(name = "challengeName")
    var challengeName: String = "",
    @ColumnInfo(name = "challengePoin")
    var challengePoin: Int = 0

)