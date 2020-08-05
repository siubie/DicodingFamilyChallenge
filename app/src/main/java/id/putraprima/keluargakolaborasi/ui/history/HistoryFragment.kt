package id.putraprima.keluargakolaborasi.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.putraprima.keluargakolaborasi.R
import id.putraprima.keluargakolaborasi.databinding.FragmentChallengeBinding
import id.putraprima.keluargakolaborasi.databinding.FragmentHistoryBinding
import id.putraprima.keluargakolaborasi.ui.challenge.*
import id.putraprima.keluargakolaborasi.ui.database.HistoryDao
import id.putraprima.keluargakolaborasi.ui.database.KolaborasiDatabase

class HistoryFragment : Fragment() {

    lateinit var binding: FragmentHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        val application = requireNotNull(this.activity).application
        val rewardDao= KolaborasiDatabase.getInstance(application).RewardDao
        val challengeDao= KolaborasiDatabase.getInstance(application).ChallengeDao
        val historyDao= KolaborasiDatabase.getInstance(application).HistoryDao
        val historyViewModelFactory = HistoryViewModelFactory(challengeDao,rewardDao,historyDao,application)
        val historyViewModel = ViewModelProvider(this,historyViewModelFactory).get(
            HistoryViewModel::class.java)

        binding.historyViewModel =historyViewModel
        binding.lifecycleOwner = this
        val adapter = HistoryAdapter(ListHistoryClickListener { item->
            item.let {
                historyViewModel.onHistoryClicked(item)
            }
        })
        binding.rvHistory.adapter=adapter

        historyViewModel.histories.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        historyViewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { item->
            item?.let {
                view?.findNavController()!!.navigate(HistoryFragmentDirections.actionHistoryFragmentToHistoryDetailFragment(item))
                historyViewModel.onHistoryNavigated()
            }
        })
        return binding.root
    }

}