<p align="center"> <img src="https://user-images.githubusercontent.com/29150094/235265334-9a0f1278-9203-4299-b18f-b4aac7712fff.jpg" width="200" hight="200"/> </p>

<h1 align="center"> Desafio Rick and Morty </h1>

<p align="center"> Este projeto é um app em Kotlin que consome a api pública do Rick &amp; Morty.</p>

## Tecnologias utilizadas:

- `API REST com Retrofit`: para consumir os dados da api pública do Rick and Morty e receber em um recyclerview.
- `Okhttp`: para notificar problemas de conexão.
- `ViewBinding`: para vincular os elementos visuais às funcionalidades, substitui o findViewById.
- `Coroutines`: para programação assíncrona no Anbdroid.
- `SharedViewModel`: para compartilhar dados de um ViewModel entre os Fragments.

## Passo a passo:

### Lista de personagens:

<p> 1 - Utilizar o endpoint /character para montar uma lista de personagens.</p>

#### [Acesso a api do rick and morty](https://rickandmortyapi.com/)

```
object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}

```

#### [Endpoint /character](https://rickandmortyapi.com/documentation/#get-all-characters)

```
package br.com.denisecastro.desafiorickandmorty.api

import br.com.denisecastro.desafiorickandmorty.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {

    @GET("api/character")
    suspend fun getCharacters(@Query("page") page:Int): Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByStatusAndGender
                ( @Query("status") status: String,
                  @Query("gender") gender: String,
                  @Query("page")page: Int ):Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByStatus
                ( @Query("status") status: String,
                  @Query("page")page: Int ):Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByGender
                ( @Query("gender") gender: String,
                  @Query("page")page: Int ):Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByName
                (@Query("name") name: String,
                 @Query("page") page: Int):Response<CharacterList>
}

```
<p>A classe Response do Retrofit permite pegar erros de conexão. Também precisa configurar no recurso de strings a mensagem de erro.</p>

```xml
   <string name="text_error">%1$d - Characters not found!</string>
```

<p>Caso aconteça algum problema com a conexão, a mensagem será exibida assim:</p>

<p> <img src="https://user-images.githubusercontent.com/29150094/235273481-4df21184-86b5-454b-8e81-2850a30dd9f3.png" width="360" hight="640"/> </p>

<p> 2 - Cada item da lista deve exibir o nome e a foto dos personagens.</p>

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/colorPrimaryDark"
    tools:context=".view.list.ListFragment">

    <androidx.appcompat.widget.SearchView
        android:id="@+id/searchView"
        android:layout_width="379dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="16dp"
        android:background="@drawable/background_search"
        app:iconifiedByDefault="false"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:queryHint="@string/search_hint" />

    <TextView
        android:id="@+id/title_characters"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginTop="60dp"
        android:text="@string/title_characters"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintEnd_toStartOf="@+id/img_button_filter"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@+id/searchView" />

    <ImageView
        android:id="@+id/img_button_filter"
        android:layout_width="30dp"
        android:layout_height="30dp"
        android:layout_marginTop="60dp"
        android:layout_marginEnd="16dp"
        android:background="@android:color/transparent"
        android:src="@drawable/ic_filter"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toEndOf="@+id/recycler_view"
        app:layout_constraintTop_toTopOf="@+id/searchView" />

    <TextView
        android:id="@+id/title_action_reset"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="16dp"
        android:text="@string/title_reset"
        android:visibility="invisible"
        android:textSize="16sp"
        android:textStyle="bold"
        android:textColor="@color/text_blue_color"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/img_button_filter" />

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="36dp"
        android:layout_marginEnd="16dp"
        android:layout_marginBottom="16dp"
        tools:listitem="@layout/item_list"
        tools:layoutManager="androidx.recyclerview.widget.StaggeredGridLayoutManager"
        tools:orientation="vertical"
        tools:spanCount="2"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/title_characters" />

    <TextView
        android:id="@+id/txt_api_error"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        android:textStyle="bold"
        android:visibility="gone"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintHorizontal_bias="0.5"/>

</androidx.constraintlayout.widget.ConstraintLayout>

```

### Imagem que corresponde ao código acima:

<p> <img src="https://user-images.githubusercontent.com/29150094/235269849-6b2ab485-a29d-419c-8344-0563237bdd00.png" width="360" hight="640"/> </p>


### Fitro de personagens:

<p> Utilizar o endpoint /character para filtrar os personagens por parâmetro, como por ex.: name; status; gender. Nesta tela, a filtragem é por status ou gender, podendo escolher os dois parâmetros ou apenas 1 dos dois.</p>

<p> <img src="https://user-images.githubusercontent.com/29150094/235273599-8ce11383-40ac-4663-8961-792d4040aae6.png" width="360" hight="640"/> </p>

<p> Exemplo de busca utilizando os dois parâmetros:</p>

<p> <img src="https://user-images.githubusercontent.com/29150094/235273785-b835eef3-0361-4058-a860-c58eb16a688e.png" width="360" hight="640"/> </p>

<p> Resultado da busca por parâmetros: </p>

<p> <img src="https://user-images.githubusercontent.com/29150094/235273858-b7d094a6-d639-4977-97c8-a1d5dbb2a811.png" width="360" hight="640"/> </p>

<p> Busca por nome: </p>

<p> <img src="https://user-images.githubusercontent.com/29150094/235273967-8ace6491-0bff-4f3d-9065-a46944f8edeb.png" width="360" hight="640"/> </p>

<p> Resultado da busca por nome: </p>

<p> <img src="https://user-images.githubusercontent.com/29150094/235274051-ce80d7e8-91b9-47ce-a63e-166426f05a08.png" width="360" hight="640"/> </p>

### Detalhes do personagem:

<p> Ao clicar no personagem, deve ser possível navegar para a tela de detalhes.</p>

<p> <img src="https://user-images.githubusercontent.com/29150094/235274445-04620b15-0a01-44e1-a0f6-a30918e1a7e3.png" width="360" hight="640"/> </p>
