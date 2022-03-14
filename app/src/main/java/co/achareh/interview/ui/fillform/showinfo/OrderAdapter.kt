package co.achareh.interview.ui.fillform.showinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.achareh.interview.data.AcharehResponse
import co.achareh.interview.databinding.ResponseItemBinding

class OrderAdapter : ListAdapter<AcharehResponse, OrderAdapter.ViewHolder>(AcharehResponseDiffCallback()) {

    class ViewHolder( private val binding: ResponseItemBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(item:AcharehResponse){
            binding.item=item
        }




        companion object{
            fun from( parent:ViewGroup):ViewHolder{
                val layoutInflater= LayoutInflater.from(parent.context)
                val binding= ResponseItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem((position)))
    }


}

class AcharehResponseDiffCallback:DiffUtil.ItemCallback<AcharehResponse>() {
    override fun areItemsTheSame(oldItem: AcharehResponse, newItem: AcharehResponse): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: AcharehResponse, newItem: AcharehResponse): Boolean {
        return oldItem.id==newItem.id
    }

}
