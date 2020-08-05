package id.putraprima.keluargakolaborasi.ui.menu


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.putraprima.keluargakolaborasi.ui.database.*
import kotlinx.coroutines.*

class MenuViewModel(
    val reward: RewardDao,
    val challenge: ChallengeDao,
    val history: HistoryDao,
    application: Application
) :
    AndroidViewModel(application) {

    private var menuViewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + menuViewModelJob)

    private val _getChallengeSelesai : LiveData<Int> = history.countChallengeSelesai()
    val getChallengeSelesai : LiveData<Int>
        get()  = _getChallengeSelesai

    private val _getRewardSelesai : LiveData<Int> = history.countRewardSelesai()
    val getRewardSelesai : LiveData<Int>
        get()  =_getRewardSelesai

    private val _getTotalPoin : LiveData<Int> = history.countTotalPoin()
    val getTotalPoin : LiveData<Int>
        get()  = _getTotalPoin

    private val _getCurrentPoint : LiveData<Int> = history.getCurrentPointLive()
    val getCurrentPoint : LiveData<Int>
        get() = _getCurrentPoint

}
