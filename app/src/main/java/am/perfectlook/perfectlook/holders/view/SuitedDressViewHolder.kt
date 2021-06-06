package am.perfectlook.perfectlook.holders.view

import am.perfectlook.perfectlook.R
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class SuitedDressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var thumbnail: ImageView

    fun bind() {
        thumbnail = itemView.findViewById(R.id.suited_dress_thumbnail)
    }
}