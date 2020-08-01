package id.putraprima.keluargakolaborasi.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.putraprima.keluargakolaborasi.R
import id.putraprima.keluargakolaborasi.databinding.FragmentChallengeDetailBinding
import id.putraprima.keluargakolaborasi.ui.database.KolaborasiDatabase
import id.putraprima.keluargakolaborasi.ui.reward.RewardDetailFragmentArgs
import id.putraprima.keluargakolaborasi.ui.reward.RewardDetailFragmentDirections

class ChallengeDetailFragment : Fragment() {

    lateinit var binding :FragmentChallengeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_challenge_detail, container, false)
        val application = requireNotNull(this.activity).application
        val rewardDao= KolaborasiDatabase.getInstance(application).RewardDao
        val challengeDao= KolaborasiDatabase.getInstance(application).ChallengeDao
        val challengeViewModelFactory = ChallengeViewModelFactory(challengeDao,rewardDao,application)
        val challengeViewModel = ViewModelProvider(this,challengeViewModelFactory).get(ChallengeViewModel::class.java)

        val args = ChallengeDetailFragmentArgs.fromBundle(requireArguments())

        binding.challengeViewModel =challengeViewModel
        binding.lifecycleOwner = this
        binding.challenge = args.challenge

        challengeViewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate->
            navigate?.let {
                view?.findNavController()?.navigate(ChallengeDetailFragmentDirections.actionChallengeDetailFragmentToChallengeFragment2())
                challengeViewModel.onChallengeNavigated()
            }

        })
        return binding.root
    }

}