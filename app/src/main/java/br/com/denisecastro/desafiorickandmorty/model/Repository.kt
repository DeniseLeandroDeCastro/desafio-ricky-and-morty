package br.com.denisecastro.desafiorickandmorty.model

import br.com.denisecastro.desafiorickandmorty.api.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getCharacters(page:Int): Response<CharacterList> {
        return RetrofitInstance.api.getCharacters(page)
    }

    suspend fun getCharactersByStatusAndGender(status: String, gender: String, page: Int):Response<CharacterList> {
        return RetrofitInstance.api.getCharactersByStatusAndGender(status, gender, page)
    }

    suspend fun getCharactersByStatus(status: String, page: Int):Response<CharacterList> {
        return RetrofitInstance.api.getCharactersByStatus(status, page)
    }

    suspend fun getCharactersByGender(gender: String, page: Int):Response<CharacterList> {
        return RetrofitInstance.api.getCharactersByGender(gender, page)
    }

    suspend fun getCharactersByName(name: String, page: Int): Response<CharacterList> {
        return RetrofitInstance.api.getCharactersByName(name, page)
    }
}