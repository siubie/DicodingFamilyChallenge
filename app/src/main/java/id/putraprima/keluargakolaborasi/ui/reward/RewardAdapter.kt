package id.putraprima.keluargakolaborasi.ui.reward

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.putraprima.keluargakolaborasi.databinding.ItemRewardBinding
import id.putraprima.keluargakolaborasi.ui.database.Reward

class RewardAdapter(val clickListener: ListRewardClickListener) :
    ListAdapter<Reward, RewardAdapter.RewardViewHolder>(ListRewardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        return RewardViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
    class RewardViewHolder private constructor(val binding: ItemRewardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Reward,
            clickListener: ListRewardClickListener
        ) {
            binding.reward = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RewardViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRewardBinding.inflate(layoutInflater, parent, false)
                return RewardViewHolder(binding)
            }
        }
    }




}

class ListRewardClickListener(val clickListener: (item: Reward) -> Unit) {
    fun onClick(item: Reward) = clickListener(item)
}

class ListRewardDiffCallback : DiffUtil.ItemCallback<Reward>() {
    override fun areItemsTheSame(oldItem: Reward, newItem: Reward): Boolean {
        return oldItem.rewardId == newItem.rewardId
    }

    override fun areContentsTheSame(oldItem: Reward, newItem: Reward): Boolean {
        return oldItem == newItem
    }}
