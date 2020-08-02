package id.putraprima.keluargakolaborasi.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.putraprima.keluargakolaborasi.databinding.ItemHistoryBinding
import id.putraprima.keluargakolaborasi.ui.database.History

class HistoryAdapter(val clickListener: ListHistoryClickListener) :
    ListAdapter<History, HistoryAdapter.HistoryViewHolder>(ListHistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class HistoryViewHolder private constructor(val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: History,
            clickListener: ListHistoryClickListener
        ) {
            binding.history = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HistoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHistoryBinding.inflate(layoutInflater, parent, false)
                return HistoryViewHolder(binding)
            }
        }
    }


}

class ListHistoryClickListener(val clickListener: (item: History) -> Unit) {
    fun onClick(item: History) = clickListener(item)
}

class ListHistoryDiffCallback : DiffUtil.ItemCallback<History>() {
    override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem.historyId == newItem.historyId
    }

    override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem == newItem
    }
}
