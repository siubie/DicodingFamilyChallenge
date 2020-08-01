package id.putraprima.keluargakolaborasi.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.putraprima.keluargakolaborasi.ui.database.Challenge
import id.putraprima.keluargakolaborasi.ui.database.ChallengeDao
import id.putraprima.keluargakolaborasi.ui.database.HistoryDao
import id.putraprima.keluargakolaborasi.ui.database.RewardDao
import kotlinx.coroutines.*

class HistoryViewModel(val reward: RewardDao, val challenge: ChallengeDao,val history : HistoryDao, application: Application) :
    AndroidViewModel(application) {

    private var historyViewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + historyViewModelJob)

}
