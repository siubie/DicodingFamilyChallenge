package id.putraprima.keluargakolaborasi.ui.menu

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.putraprima.keluargakolaborasi.ui.database.ChallengeDao
import id.putraprima.keluargakolaborasi.ui.database.HistoryDao
import id.putraprima.keluargakolaborasi.ui.database.RewardDao

class MenuViewModelFactory(
    private val challenge : ChallengeDao,
    private val reward : RewardDao,
    private val history : HistoryDao,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MenuViewModel::class.java)){
            return MenuViewModel(reward,challenge,history,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}
