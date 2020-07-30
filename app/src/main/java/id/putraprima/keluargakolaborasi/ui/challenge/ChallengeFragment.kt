package id.putraprima.keluargakolaborasi.ui.challenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import id.putraprima.keluargakolaborasi.R
import id.putraprima.keluargakolaborasi.databinding.FragmentChallengeBinding

class ChallengeFragment : Fragment() {

    lateinit var binding :FragmentChallengeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_challenge, container, false)
        return binding.root
    }

}