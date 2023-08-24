package com.dnegu.pokemonapichallenge.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnegu.pokemonapichallenge.databinding.RowPokemonSkillsBinding
import com.dnegu.pokemonapichallenge.home.data.model.response.Ability
import java.util.*

class PokemonSkillAdapter(private var historyList: List<Ability>, private var buttonClickListener: OnButtonClickListener? = null) :
    RecyclerView.Adapter<PokemonSkillAdapter.PokemonSkillHolder>() {

    fun setList(_historyList: List<Ability>){
        historyList = _historyList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonSkillHolder {
        val itemBinding = RowPokemonSkillsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonSkillHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PokemonSkillHolder, position: Int) {
        val paymentBean: Ability = historyList[position]
        holder.bind(paymentBean,buttonClickListener)
    }

    override fun getItemCount(): Int = historyList.size

    interface OnButtonClickListener {
        fun onButtonClick(data: Ability)
    }


    class PokemonSkillHolder(private val itemBinding: RowPokemonSkillsBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(ability: Ability, buttonClickListener: OnButtonClickListener? = null) = with(itemBinding){
            tvPokemonSkill.text = ability.ability.name
            clRow.setOnClickListener {
                buttonClickListener?.onButtonClick(ability)
            }
        }
    }
}