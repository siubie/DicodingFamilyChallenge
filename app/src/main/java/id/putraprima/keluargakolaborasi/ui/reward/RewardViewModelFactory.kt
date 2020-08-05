package id.putraprima.keluargakolaborasi.ui.reward

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.putraprima.keluargakolaborasi.ui.database.ChallengeDao
import id.putraprima.keluargakolaborasi.ui.database.HistoryDao
import id.putraprima.keluargakolaborasi.ui.database.RewardDao
import java.lang.IllegalArgumentException

class RewardViewModelFactory(
    private val reward : RewardDao,
    private val challenge : ChallengeDao,
    private val history : HistoryDao,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RewardViewModel::class.java)){
            return RewardViewModel(reward,challenge,history,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}