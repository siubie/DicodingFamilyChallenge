package id.putraprima.keluargakolaborasi.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import id.putraprima.keluargakolaborasi.R
import id.putraprima.keluargakolaborasi.databinding.FragmentChallengeDetailBinding

class ChallengeDetailFragment : Fragment() {

    lateinit var binding :FragmentChallengeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_challenge_detail, container, false)
        return binding.root
    }

}