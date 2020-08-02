package id.putraprima.keluargakolaborasi.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import id.putraprima.keluargakolaborasi.R
import id.putraprima.keluargakolaborasi.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    lateinit var binding:FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_menu,container,false);
        binding.btnReward.setOnClickListener {
            it.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToRewardFragment())
        }
        binding.btnStatistik.setOnClickListener {
            it.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToHistoryFragment())
        }
        binding.btnChallenge.setOnClickListener {
            it.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToChallengeFragment())
        }
        return binding.root
    }

}