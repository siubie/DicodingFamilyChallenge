package id.putraprima.keluargakolaborasi.ui.reward

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.putraprima.keluargakolaborasi.ui.database.RewardDao
import java.lang.IllegalArgumentException

class RewardViewModelFactory(
    private val dataSource : RewardDao,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RewardViewModel::class.java)){
            return RewardViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}