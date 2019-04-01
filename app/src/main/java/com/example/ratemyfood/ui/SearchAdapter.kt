package com.example.ratemyfood.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ratemyfood.R
import com.example.ratemyfood.data.model.Business
import com.example.ratemyfood.data.model.BusinessCard
import com.squareup.picasso.Picasso
import java.util.ArrayList

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){
    private val businessCardList = ArrayList<BusinessCard>()
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = businessCardList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return businessCardList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(
            R.layout.item_business_card,
            parent, false)
        return SearchViewHolder(itemView)
    }

    fun addBussinessCard(businessCards : List<BusinessCard>){
        val initPosition = businessCardList.size
        businessCardList.addAll(businessCards)
        notifyItemRangeInserted(initPosition, businessCardList.size)
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageBusiness = itemView.findViewById<ImageView>(R.id.businessImage)
        var reviewBusiness = itemView.findViewById<TextView>(R.id.reviewsText)
        fun bind(businessCard: BusinessCard){
            Picasso
                .with(itemView.context)
                .load(businessCard.business.image_url)
                .into(imageBusiness)
            businessCard.reviewList?.forEach {
                reviewBusiness.text = it.text
            }
        }
    }
}