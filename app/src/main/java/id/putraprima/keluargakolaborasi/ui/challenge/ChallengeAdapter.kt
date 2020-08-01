package id.putraprima.keluargakolaborasi.ui.challenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.putraprima.keluargakolaborasi.databinding.ItemChallengeBinding
import id.putraprima.keluargakolaborasi.databinding.ItemRewardBinding
import id.putraprima.keluargakolaborasi.ui.database.Challenge
import id.putraprima.keluargakolaborasi.ui.database.Reward

class ChallengeAdapter(val clickListener: ListChallengeClickListener) :
    ListAdapter<Challenge, ChallengeAdapter.ChallengeViewHolder>(ListChallengeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        return ChallengeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
    class ChallengeViewHolder private constructor(val binding: ItemChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Challenge,
            clickListener: ListChallengeClickListener
        ) {
            binding.challenge = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ChallengeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemChallengeBinding.inflate(layoutInflater, parent, false)
                return ChallengeViewHolder(binding)
            }
        }
    }




}

class ListChallengeClickListener(val clickListener: (item: Challenge) -> Unit) {
    fun onClick(item: Challenge) = clickListener(item)
}

class ListChallengeDiffCallback : DiffUtil.ItemCallback<Challenge>() {
    override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
        return oldItem.challengeId== newItem.challengeId
    }

    override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
        return oldItem == newItem
    }}
