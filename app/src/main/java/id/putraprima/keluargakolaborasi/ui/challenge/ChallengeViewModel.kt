package id.putraprima.keluargakolaborasi.ui.challenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.putraprima.keluargakolaborasi.ui.database.*
import kotlinx.coroutines.*

class ChallengeViewModel(
    val reward: RewardDao,
    val challenge: ChallengeDao,
    val history: HistoryDao,
    application: Application
) :
    AndroidViewModel(application) {

    private var challengeViewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + challengeViewModelJob)

    val currentChallenge = MutableLiveData<Challenge>()
    val challenges: LiveData<List<Challenge>> = challenge.getAll()

    private val _navigateToDetail = MutableLiveData<Challenge>()

    val navigateToDetail
        get() = _navigateToDetail


    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    val currentChallengeName = MutableLiveData<String>()
    val currentChallengePoint = MutableLiveData<String>()


    fun onNavigatedToList() {
        _navigateToList.value = null
    }

    fun onChallengeClicked(challenge: Challenge) {
        currentChallenge.value = challenge
        _navigateToDetail.value = challenge
    }

    fun onChallengeNavigated() {
        _navigateToDetail.value = null
    }

    fun onInsertChallenge() {
        uiScope.launch {
            val newChallenge =
                Challenge(0L, currentChallengeName.value!!, currentChallengePoint.value!!.toInt())
            insert(newChallenge)
            _navigateToList.value = true
        }
    }

    private suspend fun insert(item: Challenge) {
        withContext(Dispatchers.IO) {
            challenge.insert(item)
        }
    }

    fun onDeleteChallenge(challenge: Challenge) {
        uiScope.launch {
            deleteChallenge(challenge.challengeId)
            _navigateToList.value = true
        }
    }

    private suspend fun deleteChallenge(challengeId: Long) {
        withContext(Dispatchers.IO) {
            challenge.deleteById(challengeId)
        }
    }

    private val _selesaiChallenge = MutableLiveData<Boolean>()
    val selesaiChalenge: LiveData<Boolean>
        get() = _selesaiChallenge

    fun onSelesaiChallenge(item: Challenge) {
        uiScope.launch {
            val historyItem = History(0L, item.challengeName, item.challengePoin)
            onInsertHistory(historyItem)
            _selesaiChallenge.value = true
        }
    }

    fun onNavigatedToMenu() {
        _selesaiChallenge.value = null
    }

    private suspend fun onInsertHistory(item: History) {
        withContext(Dispatchers.IO) {
            history.insert(item)
        }
    }
}
