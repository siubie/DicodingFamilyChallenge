package id.putraprima.keluargakolaborasi.ui.challenge
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.putraprima.keluargakolaborasi.ui.database.Challenge
import id.putraprima.keluargakolaborasi.ui.database.ChallengeDao
import id.putraprima.keluargakolaborasi.ui.database.Reward
import id.putraprima.keluargakolaborasi.ui.database.RewardDao
import kotlinx.coroutines.*

class ChallengeViewModel(val reward: RewardDao, val challenge: ChallengeDao, application: Application) :
    AndroidViewModel(application) {

    private var challengeViewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + challengeViewModelJob)

    val currentChallenge = MutableLiveData<Challenge>()
    val challenges : LiveData<List<Challenge>> = challenge.getAll()

    private val _navigateToDetail = MutableLiveData<Challenge>()

    val navigateToDetail
        get() = _navigateToDetail


    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList : LiveData<Boolean>
        get() =_navigateToList

    val currentChallengeName = MutableLiveData<String>()
    val currentChallengePoint = MutableLiveData<String>()

    fun onNavigatedToList() {
        _navigateToList.value=null
    }
    fun onChallengeClicked(challenge: Challenge){
        currentChallenge.value = challenge
        _navigateToDetail.value = challenge
    }

    fun onChallengeNavigated(){
        _navigateToDetail.value = null
    }
    fun onInsertChallenge() {
        uiScope.launch {
            val newChallenge = Challenge(0L, currentChallengeName.value!!, currentChallengePoint.value!!.toInt())
            insert(newChallenge)
            _navigateToList.value = true
        }
    }

    private suspend fun insert(item: Challenge) {
        withContext(Dispatchers.IO) {
            challenge.insert(item)
        }
    }
}
