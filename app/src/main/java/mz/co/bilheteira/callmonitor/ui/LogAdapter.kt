package mz.co.bilheteira.callmonitor.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import mz.co.bilheteira.callmonitor.data.Log
import mz.co.bilheteira.callmonitor.databinding.GenericCallItemBinding

class LogAdapter(
    private val onLogCLick: (Log) -> (Unit)
) : RecyclerView.Adapter<LogAdapter.LogViewHolder>() {

    private val listOfLog = mutableListOf<Log>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitItems(items: List<Log>) {
        listOfLog.clear()
        listOfLog.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder =
        LogViewHolder(
            binding = GenericCallItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bind(log = listOfLog[position])
    }

    override fun getItemCount(): Int = listOfLog.size

    inner class LogViewHolder(
        private val binding: GenericCallItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(log: Log) = binding.apply {
            binding.itemText.text = log.name
            binding.itemSubText.text = log.duration

            view.isGone = adapterPosition == itemCount - 1
        }
    }
}