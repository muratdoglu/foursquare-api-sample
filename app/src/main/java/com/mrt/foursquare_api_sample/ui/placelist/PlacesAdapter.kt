package com.mrt.foursquare_api_sample.ui.placelist


import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mrt.foursquare_api_sample.R
import com.mrt.foursquare_api_sample.data.PlacesBody
import java.util.ArrayList

class PlacesAdapter(
    var placeList: ArrayList<PlacesBody>, private val onItemClick: (info: PlacesBody) -> Unit
) :
    RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_places, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: PlacesAdapter.ViewHolder, position: Int) {
        holder.bindItems(placeList[position], onItemClick)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return placeList.size
    }

    fun updateN(newUserList: ArrayList<PlacesBody>) {
        placeList = newUserList
        notifyDataSetChanged()

    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(
            place: PlacesBody,
            onItemClick: (info: PlacesBody) -> Unit
        ) {
            itemView.findViewById<TextView>(R.id.tvName).text = place.name
            itemView.findViewById<TextView>(R.id.tvDistance).text = place.distance.toString()
            if(place.categories?.size?:0>0){
                var category=place.categories?.get(0)
                itemView.findViewById<TextView>(R.id.tvCategory).text = category?.name
                Glide.with(itemView.context)
                    .load(category?.icon?.prefix?:""+category?.icon?.suffix?:"")
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .dontAnimate()
                    .into(itemView.findViewById<ImageView>(R.id.ivIcon))
            }


        }
    }

}