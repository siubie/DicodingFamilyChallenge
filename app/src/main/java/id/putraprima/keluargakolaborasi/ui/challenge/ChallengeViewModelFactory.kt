package id.putraprima.keluargakolaborasi.ui.challenge

import id.putraprima.keluargakolaborasi.ui.reward.RewardViewModel
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.putraprima.keluargakolaborasi.ui.database.ChallengeDao
import id.putraprima.keluargakolaborasi.ui.database.RewardDao
import java.lang.IllegalArgumentException

class ChallengeViewModelFactory(
    private val challenge : ChallengeDao,
    private val reward : RewardDao,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ChallengeViewModel::class.java)){
            return ChallengeViewModel(reward,challenge,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}