package id.putraprima.keluargakolaborasi.ui.reward

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.putraprima.keluargakolaborasi.ui.database.*
import kotlinx.coroutines.*

class RewardViewModel(
    val database: RewardDao,
    var challenge: ChallengeDao,
    var history: HistoryDao,
    application: Application
) :
    AndroidViewModel(application) {

    private var rewardViewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + rewardViewModelJob)
    private var reward = MutableLiveData<Reward?>()
    private var rewards = database.getAllReward()
    private var _dataCount = MutableLiveData<Int>()
    val currentRewardName = MutableLiveData<String>()
    val currentRewardPoint = MutableLiveData<String>()
    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    val dataCount: LiveData<Int> = database.countReward()
    val latestReward: LiveData<Reward> = database.getLatestReward()
    val allReward: LiveData<List<Reward>> = database.getAllReward()
    val currentReward = MutableLiveData<Reward>()
    private val _currentPoint = MutableLiveData<Int>()
    private val _noPoint = MutableLiveData<Boolean>()
    val noPoint: LiveData<Boolean>
        get() = _noPoint


    override fun onCleared() {
        super.onCleared()
        rewardViewModelJob.cancel()
    }

    fun onInsertReward() {
        uiScope.launch {
            val newReward =
                Reward(0L, currentRewardName.value!!, currentRewardPoint.value!!.toInt())
            insert(newReward)
            _navigateToList.value = true
        }
    }

    private suspend fun insert(reward: Reward) {
        withContext(Dispatchers.IO) {
            database.insert(reward)
        }
    }

    fun onClearReward() {
        uiScope.launch {
            clearReward()
        }
    }

    private suspend fun clearReward() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    fun onDeleteReward(reward: Reward) {
        uiScope.launch {
            deleteReward(reward.rewardId)
            _navigateToList.value = true
        }
    }

    private suspend fun deleteReward(rewardId: Long) {
        withContext(Dispatchers.IO) {
            database.deleteById(rewardId)
        }
    }

    fun onNavigatedToList() {
        _navigateToList.value = null
    }

    private val _navigateToDetail = MutableLiveData<Reward>()

    val navigateToDetail
        get() = _navigateToDetail

    fun onRewardClicked(reward: Reward) {
        currentReward.value = reward
        _navigateToDetail.value = reward
    }

    fun onRewardNavigated() {
        _navigateToDetail.value = null
    }

    fun onRewardClaim(reward: Reward) {
        onGetCurrentPoint(reward)
    }

    private fun onGetCurrentPoint(reward: Reward) {
        uiScope.launch {
            _currentPoint.value = getCurrentPoint()
            if (_currentPoint.value!! < reward.rewardPrice) {
                //ga d insert
                _noPoint.value = true
            } else {
                //insert ke history
                _noPoint.value = false
                val historyItem = History(0L, reward.rewardName, -reward.rewardPrice)
                onInsertHistory(historyItem)
            }
        }

    }

    private suspend fun onInsertHistory(item: History) {
        withContext(Dispatchers.IO) {
            history.insert(item)
        }
    }

    private suspend fun getCurrentPoint(): Int? {
        return withContext(Dispatchers.IO) {
            history.getCurrentPoint()
        }
    }
}