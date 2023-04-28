package br.com.denisecastro.desafiorickandmorty.view.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.denisecastro.desafiorickandmorty.R
import br.com.denisecastro.desafiorickandmorty.databinding.FragmentListBinding
import br.com.denisecastro.desafiorickandmorty.model.Repository
import br.com.denisecastro.desafiorickandmorty.view.adapter.CharacterAdapter
import br.com.denisecastro.desafiorickandmorty.viewmodel.SharedViewModel
import br.com.denisecastro.desafiorickandmorty.viewmodel.SharedViewModelFactory

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels { SharedViewModelFactory(
        Repository()
    ) }
    private var adapter = CharacterAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedViewModel.getCharacters(1)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
        sharedViewModel.listCharacters.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                adapter.setCharacters(response.body()!!.results)
                txtApiError.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            } else {
                txtApiError.text = getString(R.string.text_error, response.code())
                txtApiError.visibility = View.VISIBLE
                recyclerView.visibility = View.INVISIBLE
            }
        }
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.adapter = adapter

            imgButtonFilter.setOnClickListener {
                findNavController().navigate(R.id.action_listFragment_to_filterFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}