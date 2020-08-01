package id.putraprima.keluargakolaborasi.ui.reward

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.putraprima.keluargakolaborasi.R
import id.putraprima.keluargakolaborasi.databinding.FragmentRewardBinding
import id.putraprima.keluargakolaborasi.ui.database.KolaborasiDatabase
import id.putraprima.keluargakolaborasi.ui.database.Reward

class RewardFragment : Fragment() {

    lateinit var binding : FragmentRewardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_reward,container,false);

        val application = requireNotNull(this.activity).application
        val dataSource=KolaborasiDatabase.getInstance(application).RewardDao
        val rewardViewModelFactory =RewardViewModelFactory(dataSource,application)
        val rewardViewModel = ViewModelProvider(this,rewardViewModelFactory).get(RewardViewModel::class.java)

        binding.rewardViewModel = rewardViewModel
        binding.lifecycleOwner = this

        val adapter = RewardAdapter(ListRewardClickListener { item->
            item.let {
                rewardViewModel.onRewardClicked(item)
            }
        })

        rewardViewModel.allReward.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        rewardViewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { item->
            item?.let {
                view?.findNavController()!!.navigate(RewardFragmentDirections.actionRewardFragmentToRewardDetailFragment(item))
                rewardViewModel.onRewardNavigated()
            }
        })

        binding.rvReward.adapter = adapter
        binding.btnAddReward.setOnClickListener {
            it.findNavController().navigate(RewardFragmentDirections.actionRewardFragmentToRewardAddFragment())
        }

        return binding.root
    }
}