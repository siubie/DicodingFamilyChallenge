package id.putraprima.keluargakolaborasi.ui.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "history")
class History(
    @PrimaryKey(autoGenerate = true)
    var historyId: Long = 0L,
    @ColumnInfo(name = "historyName")
    var historyName: String = "",
    @ColumnInfo(name = "historyPoint")
    var historyPoint: Int = 0
):Parcelable
