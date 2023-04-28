package br.com.denisecastro.desafiorickandmorty.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.denisecastro.desafiorickandmorty.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = args.character
        binding.apply {
            txtId.text = character.id.toString()
            txtStatus.text = character.status
            txtGender.text = character.gender
            txtName.text = character.name
            txtSpecie.text = character.species
            txtNEpisodes.text = character.episode.size.toString()
            txtOrigin.text = character.origin.name
            txtLocation.text = character.location.name
            Picasso.get().load(character.image).into(imgCharacterDetail)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}