package br.com.denisecastro.desafiorickandmorty.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationData(
    var name: String
):Parcelable
