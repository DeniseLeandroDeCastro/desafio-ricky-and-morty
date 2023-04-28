package br.com.denisecastro.desafiorickandmorty.extensions

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.getTextChipChecked(): String {
    val selectedId: Int = this.checkedChipId
    return if (selectedId > -1) findViewById<Chip>(selectedId).text.toString() else ""
}

fun ChipGroup.setChipChecked(selectedId: Int) {
    if (selectedId > 0) this.findViewById<Chip>(selectedId).isChecked = true
}