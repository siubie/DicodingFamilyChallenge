package id.putraprima.keluargakolaborasi.ui.menu

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
import id.putraprima.keluargakolaborasi.databinding.FragmentMenuBinding
import id.putraprima.keluargakolaborasi.ui.challenge.ChallengeViewModel
import id.putraprima.keluargakolaborasi.ui.challenge.ChallengeViewModelFactory
import id.putraprima.keluargakolaborasi.ui.database.KolaborasiDatabase

class MenuFragment : Fragment() {

    lateinit var binding:FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val rewardDao = KolaborasiDatabase.getInstance(application).RewardDao
        val challengeDao = KolaborasiDatabase.getInstance(application).ChallengeDao
        val historyDao = KolaborasiDatabase.getInstance(application).HistoryDao
        val menuViewModelFactory =
            MenuViewModelFactory(challengeDao, rewardDao, historyDao, application)
        val menuViewModel =
            ViewModelProvider(this, menuViewModelFactory).get(MenuViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_menu,container,false);

        binding.menuViewModel = menuViewModel
        binding.lifecycleOwner = this
        binding.containerChallenge.setOnClickListener{
            it.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToChallengeFragment())
        }
        binding.containerReward.setOnClickListener{
            it.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToRewardFragment())
        }
        binding.btnStatistik.setOnClickListener {
            it.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToHistoryFragment())
        }

        menuViewModel.getChallengeSelesai.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.txtNilaiChallengeSelesai.text = it.toString()
            }
        })

        menuViewModel.getRewardSelesai.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.txtNilaiRewardDiclaim.text = it.toString()
            }
        })

        menuViewModel.getTotalPoin.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.textView.text = it.toString()
            }
        })

        menuViewModel.getCurrentPoint.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.txtNilaiPoinTersedia.text = it.toString()
            }
        })

        return binding.root
    }

}