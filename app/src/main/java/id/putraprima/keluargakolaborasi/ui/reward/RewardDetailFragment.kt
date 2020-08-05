package id.putraprima.keluargakolaborasi.ui.reward

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
import com.google.android.material.snackbar.Snackbar
import id.putraprima.keluargakolaborasi.R
import id.putraprima.keluargakolaborasi.databinding.FragmentRewardDetailBinding
import id.putraprima.keluargakolaborasi.ui.database.KolaborasiDatabase

class RewardDetailFragment : Fragment() {
    lateinit var binding : FragmentRewardDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_reward_detail, container, false)
        val application = requireNotNull(this.activity).application
        val reward=KolaborasiDatabase.getInstance(application).RewardDao
        val challenge=KolaborasiDatabase.getInstance(application).ChallengeDao
        val history=KolaborasiDatabase.getInstance(application).HistoryDao
        val rewardViewModelFactory =RewardViewModelFactory(reward,challenge,history,application)
        val rewardViewModel = ViewModelProvider(this,rewardViewModelFactory).get(RewardViewModel::class.java)

        val args = RewardDetailFragmentArgs.fromBundle(requireArguments())
        binding.rewardViewModel = rewardViewModel
        binding.reward = args.reward
        rewardViewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate->
            navigate?.let {
                view?.findNavController()?.navigate(RewardDetailFragmentDirections.actionRewardDetailFragmentToRewardFragment())
                rewardViewModel.onRewardNavigated()
            }

        })
        rewardViewModel.noPoint.observe(viewLifecycleOwner, Observer { noPoint ->
            noPoint?.let {
                if (noPoint) {
                    view?.let { view ->
                        Snackbar.make(
                            binding.root,
                            "Point Anda Tidak Cukup",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    view?.let { view ->
                        Snackbar.make(
                            binding.root,
                            "Berhasil Ambil Reward",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        })
        binding.lifecycleOwner = this
        return binding.root
    }

}