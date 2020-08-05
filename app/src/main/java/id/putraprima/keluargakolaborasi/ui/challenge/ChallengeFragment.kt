package id.putraprima.keluargakolaborasi.ui.challenge

import android.os.Bundle
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
import id.putraprima.keluargakolaborasi.databinding.FragmentChallengeBinding
import id.putraprima.keluargakolaborasi.ui.database.HistoryDao
import id.putraprima.keluargakolaborasi.ui.database.KolaborasiDatabase
import id.putraprima.keluargakolaborasi.ui.reward.*

class ChallengeFragment : Fragment() {

    lateinit var binding: FragmentChallengeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_challenge, container, false)
        binding.txtAddChallenge.setOnClickListener {
            it.findNavController()
                .navigate(ChallengeFragmentDirections.actionChallengeFragmentToChallengeAddFragment())
        }

        val application = requireNotNull(this.activity).application
        val rewardDao = KolaborasiDatabase.getInstance(application).RewardDao
        val challengeDao = KolaborasiDatabase.getInstance(application).ChallengeDao
        val historyDao = KolaborasiDatabase.getInstance(application).HistoryDao
        val challengeViewModelFactory =
            ChallengeViewModelFactory(challengeDao, rewardDao, historyDao, application)
        val challengeViewModel =
            ViewModelProvider(this, challengeViewModelFactory).get(ChallengeViewModel::class.java)

        binding.challengeViewModel = challengeViewModel
        binding.lifecycleOwner = this

        val adapter = ChallengeAdapter(ListChallengeClickListener { item ->
            item.let {
                challengeViewModel.onChallengeClicked(item)
            }
        })
        binding.rvChallenge.adapter = adapter

        challengeViewModel.challenges.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                if (it.isEmpty()) {
                    Snackbar.make(binding.root, "Belum Ada Challenge", Snackbar.LENGTH_SHORT).show()
                }
            }
        })

        challengeViewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { item ->
            item?.let {
                view?.findNavController()!!.navigate(
                    ChallengeFragmentDirections.actionChallengeFragmentToChallengeDetailFragment(
                        item
                    )
                )
                challengeViewModel.onChallengeNavigated()
            }
        })
        return binding.root
    }

}