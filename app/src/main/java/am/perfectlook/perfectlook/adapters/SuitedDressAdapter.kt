package am.perfectlook.perfectlook.adapters

import am.perfectlook.perfectlook.R
import am.perfectlook.perfectlook.holders.view.SuitedDressViewHolder
import am.perfectlook.perfectlook.models.SuitedDress
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class SuitedDressAdapter(private val data: List<SuitedDress>) : RecyclerView.Adapter<SuitedDressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuitedDressViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.suited_dress_list_item, parent, false)
        return SuitedDressViewHolder(root)
    }

    override fun onBindViewHolder(holder: SuitedDressViewHolder, position: Int) {
        holder.bind()

        val item = data[position]
        holder.thumbnail.setImageResource(item.thumbnail)
    }

    override fun getItemCount(): Int = data.size
}