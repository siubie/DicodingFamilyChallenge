package id.putraprima.keluargakolaborasi.ui.challenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.putraprima.keluargakolaborasi.R
import id.putraprima.keluargakolaborasi.databinding.FragmentChallengeAddBinding
import id.putraprima.keluargakolaborasi.databinding.FragmentChallengeDetailBinding
import id.putraprima.keluargakolaborasi.ui.database.KolaborasiDatabase
import id.putraprima.keluargakolaborasi.ui.reward.RewardAddFragmentDirections

class ChallengeAddFragment : Fragment() {
    lateinit var binding :FragmentChallengeAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_challenge_add, container, false)

        val application = requireNotNull(this.activity).application
        val rewardDao= KolaborasiDatabase.getInstance(application).RewardDao
        val challengeDao= KolaborasiDatabase.getInstance(application).ChallengeDao
        val historyDao= KolaborasiDatabase.getInstance(application).HistoryDao

        val challengeViewModelFactory = ChallengeViewModelFactory(challengeDao,rewardDao,historyDao,application)
        val challengeViewModel = ViewModelProvider(this,challengeViewModelFactory).get(ChallengeViewModel::class.java)

        binding.challengeViewModel =challengeViewModel
        binding.lifecycleOwner = this
        challengeViewModel.navigateToList.observe(viewLifecycleOwner, Observer {navigate->
            navigate?.let {
                view?.findNavController()!!.navigate(ChallengeAddFragmentDirections.actionChallengeAddFragmentToChallengeFragment())
                challengeViewModel.onNavigatedToList()
            }
        })
        return binding.root
    }

}