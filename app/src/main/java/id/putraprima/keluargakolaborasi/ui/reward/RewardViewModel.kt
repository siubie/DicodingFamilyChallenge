package id.putraprima.keluargakolaborasi.ui.reward

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.putraprima.keluargakolaborasi.ui.database.Reward
import id.putraprima.keluargakolaborasi.ui.database.RewardDao
import kotlinx.coroutines.*

class RewardViewModel(val database: RewardDao, application: Application) :
    AndroidViewModel(application) {

    private var rewardViewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + rewardViewModelJob)
    private var reward = MutableLiveData<Reward?>()
    private var rewards = database.getAllReward()
    private var _dataCount = MutableLiveData<Int>()
    val currentRewardName = MutableLiveData<String>()
    val currentRewardPoint = MutableLiveData<String>()
    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList : LiveData<Boolean>
        get() =_navigateToList

    val dataCount: LiveData<Int> = database.countReward()
    val latestReward: LiveData<Reward> = database.getLatestReward()
    val allReward : LiveData<List<Reward>> = database.getAllReward()

    init{
        currentRewardName.value="soko"
        currentRewardPoint.value = "30"
    }
    override fun onCleared() {
        super.onCleared()
        rewardViewModelJob.cancel()
    }

    fun onInsertReward() {
        uiScope.launch {
            val newReward = Reward(0L, currentRewardName.value!!, currentRewardPoint.value!!.toLong())
            insert(newReward)
            _navigateToList.value = true
        }
    }

    private suspend fun insert(reward: Reward) {
        withContext(Dispatchers.IO) {
            database.insert(reward)
        }
    }

    fun onClearReward(){
        uiScope.launch {
            clearReward()
        }
    }

    private suspend fun clearReward() {
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

    fun onNavigatedToList() {
        _navigateToList.value=null
    }

    fun onRewardClicked(){

    }


}