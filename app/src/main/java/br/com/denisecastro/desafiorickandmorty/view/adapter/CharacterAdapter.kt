package br.com.denisecastro.desafiorickandmorty.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.denisecastro.desafiorickandmorty.R
import br.com.denisecastro.desafiorickandmorty.model.Character
import br.com.denisecastro.desafiorickandmorty.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var listCharacters = emptyList<Character>()

    class CharacterViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.txtIdCharacter.text = character.id.toString()
            binding.txtNameCharacter.text = character.name
            binding.txtStatusCharacter.text = character.status
            Picasso.get().load(character.image).into(binding.imgCharacter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(listCharacters[position])
        holder.itemView.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    fun setCharacters(characters: List<Character>) {
        listCharacters = characters
        notifyDataSetChanged()
    }
}