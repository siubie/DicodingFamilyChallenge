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
import id.putraprima.keluargakolaborasi.R
import id.putraprima.keluargakolaborasi.databinding.FragmentRewardAddBinding
import id.putraprima.keluargakolaborasi.ui.database.KolaborasiDatabase

class RewardAddFragment : Fragment() {

    lateinit var binding:FragmentRewardAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_reward_add, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource= KolaborasiDatabase.getInstance(application).RewardDao
        val rewardViewModelFactory =RewardViewModelFactory(dataSource,application)
        val rewardViewModel = ViewModelProvider(this,rewardViewModelFactory).get(RewardViewModel::class.java)

        binding.rewardViewModel = rewardViewModel
        rewardViewModel.navigateToList.observe(viewLifecycleOwner, Observer {navigate->
            navigate?.let {
                Log.d("SOKO","Clicked")
                view?.findNavController()!!.navigate(RewardAddFragmentDirections.actionRewardAddFragmentToRewardFragment())
                rewardViewModel.onNavigatedToList()
            }
        })
        binding.lifecycleOwner = this

        return binding.root
    }

}