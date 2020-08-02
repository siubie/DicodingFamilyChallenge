package id.putraprima.keluargakolaborasi.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.putraprima.keluargakolaborasi.ui.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HistoryViewModel(val reward: RewardDao, val challenge: ChallengeDao, val history : HistoryDao, application: Application) :
    AndroidViewModel(application) {

    private var historyViewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + historyViewModelJob)

    val histories : LiveData<List<History>> = history.getAll()
    val currentHistory = MutableLiveData<History>()

    private val _navigateToDetail = MutableLiveData<History>()

    val navigateToDetail
        get() = _navigateToDetail

    fun onHistoryClicked(history: History){
        currentHistory.value =history
        _navigateToDetail.value =history
    }
    fun onHistoryNavigated(){
        _navigateToDetail.value = null
    }
}
