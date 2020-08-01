package id.putraprima.keluargakolaborasi.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import id.putraprima.keluargakolaborasi.R
import id.putraprima.keluargakolaborasi.databinding.FragmentHistoryDetailBinding

class HistoryDetailFragment : Fragment() {

    lateinit var binding: FragmentHistoryDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_history_detail, container, false)
        return binding.root
    }

}