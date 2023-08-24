package com.dnegu.pokemonapichallenge.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnegu.pokemonapichallenge.databinding.RowPokemonEvolutionaryBinding
import com.dnegu.pokemonapichallenge.databinding.RowPokemonSkillsBinding
import com.dnegu.pokemonapichallenge.home.data.model.response.Ability
import java.util.*

class PokemonEvolutionaryAdapter(private var historyList: List<String>, private var buttonClickListener: OnButtonClickListener? = null) :
    RecyclerView.Adapter<PokemonEvolutionaryAdapter.PokemonEvolutionaryHolder>() {

    fun setList(_historyList: List<String>){
        historyList = _historyList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonEvolutionaryHolder {
        val itemBinding = RowPokemonEvolutionaryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonEvolutionaryHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PokemonEvolutionaryHolder, position: Int) {
        val paymentBean: String = historyList[position]
        holder.bind(paymentBean,buttonClickListener)
    }

    override fun getItemCount(): Int = historyList.size

    interface OnButtonClickListener {
        fun onButtonClick(data: String)
    }


    class PokemonEvolutionaryHolder(private val itemBinding: RowPokemonEvolutionaryBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(name: String, buttonClickListener: OnButtonClickListener? = null) = with(itemBinding){
            tvPokemonEvolutionary.text =name
            clRow.setOnClickListener {
                buttonClickListener?.onButtonClick(name)
            }
        }
    }
}