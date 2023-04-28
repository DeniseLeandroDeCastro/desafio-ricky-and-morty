package br.com.denisecastro.desafiorickandmorty.view.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.com.denisecastro.desafiorickandmorty.R
import br.com.denisecastro.desafiorickandmorty.databinding.FragmentFilterBinding
import br.com.denisecastro.desafiorickandmorty.extensions.getTextChipChecked
import br.com.denisecastro.desafiorickandmorty.extensions.setChipChecked
import br.com.denisecastro.desafiorickandmorty.model.Repository
import br.com.denisecastro.desafiorickandmorty.viewmodel.SharedViewModel
import br.com.denisecastro.desafiorickandmorty.viewmodel.SharedViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels { SharedViewModelFactory (Repository()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
        sharedViewModel.filterValue.observe(viewLifecycleOwner) { item ->
                chipgroupStatus.setChipChecked(item[0])
                chipgroupGender.setChipChecked(item[1])
        }

            clearChipCheck.setOnClickListener { chipgroupGender.clearCheck() }

            btnApplyFilter.setOnClickListener {
                if (
                    chipgroupStatus.getTextChipChecked().isNotEmpty() &&
                    chipgroupGender.getTextChipChecked().isNotEmpty()) {
                    sharedViewModel.getByStatusAndGender(
                        chipgroupStatus.getTextChipChecked(),
                        chipgroupGender.getTextChipChecked(), 1)
                } else {
                    if (chipgroupStatus.getTextChipChecked().isNotEmpty()) {
                        sharedViewModel.getByStatus(chipgroupStatus.getTextChipChecked(), 1)
                    } else {
                        sharedViewModel.getByGender(chipgroupGender.getTextChipChecked(), 1)
                    }
                }
               sharedViewModel.filterValue.value = arrayOf(chipgroupStatus.checkedChipId, chipgroupGender.checkedChipId)
               findNavController().popBackStack(R.id.listFragment, false)
           }
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}