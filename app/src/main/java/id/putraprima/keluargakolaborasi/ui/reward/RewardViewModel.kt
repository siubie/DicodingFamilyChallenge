package id.putraprima.keluargakolaborasi.ui.reward

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.putraprima.keluargakolaborasi.ui.database.Reward
import id.putraprima.keluargakolaborasi.ui.database.RewardDao
import kotlinx.coroutines.*

class RewardViewModel (val database: RewardDao,application: Application): AndroidViewModel(application){

    private var rewardViewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main+rewardViewModelJob)
    private var reward  = MutableLiveData<Reward?>()
    private var rewards = database.getAllReward()
    private var _dataCount = MutableLiveData<Int>()
    val dataCount : LiveData<Int> = database.countReward()

    override fun onCleared() {
        super.onCleared()
        rewardViewModelJob.cancel()
    }

    fun onInsertReward(){
        uiScope.launch {
            val newReward = Reward(0L,"soko",10)
            insert(newReward)
        }
    }

    private suspend fun insert(reward: Reward){
        withContext(Dispatchers.IO){
            database.insert(reward)
        }
    }


}