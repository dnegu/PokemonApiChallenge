package com.dnegu.pokemonapichallenge.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnegu.pokemonapichallenge.databinding.RowPokemonListBinding
import com.dnegu.pokemonapichallenge.home.data.model.response.PokemonList
import java.util.*

class PokemonListAdapter(private var historyList: List<PokemonList>, private var buttonClickListener: OnButtonClickListener? = null) :
    RecyclerView.Adapter<PokemonListAdapter.BookCoursesHistoryHolder>() {

    fun setList(_historyList: List<PokemonList>){
        historyList = _historyList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCoursesHistoryHolder {
        val itemBinding = RowPokemonListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookCoursesHistoryHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BookCoursesHistoryHolder, position: Int) {
        val paymentBean: PokemonList = historyList[position]
        holder.bind(paymentBean,buttonClickListener)
    }

    override fun getItemCount(): Int = historyList.size

    interface OnButtonClickListener {
        fun onButtonClick(data: PokemonList)
    }


    class BookCoursesHistoryHolder(private val itemBinding: RowPokemonListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(pokemonList: PokemonList, buttonClickListener: OnButtonClickListener? = null) = with(itemBinding){
            tvNamePokemon.text = pokemonList.name
            clRow.setOnClickListener {
                buttonClickListener?.onButtonClick(pokemonList)
            }
        }
    }
}