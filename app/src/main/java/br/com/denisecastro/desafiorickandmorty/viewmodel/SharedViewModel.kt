package br.com.denisecastro.desafiorickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.denisecastro.desafiorickandmorty.model.CharacterList
import br.com.denisecastro.desafiorickandmorty.model.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel(val repository: Repository): ViewModel() {

    var listCharacters = MutableLiveData<Response<CharacterList>>()

    fun getCharacters(page:Int) {
        viewModelScope.launch {
            val characters = repository.getCharacters(page)
            listCharacters.value = characters
        }
    }
}