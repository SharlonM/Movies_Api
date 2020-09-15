
# **Movies_Api**
## **_Mobile Codding Challenge_**
### Stant Licenciamento de Software LTDA

#### Funcionalidades esperadas
- [x] Listar filmes em cartaz incluindo nome,poster,genero e data de lançamento
- [x] Implementar conceito infite loading
- [x] Selecionar filme e ver seus detalhes
- [ ] Buscar filmes por titulo ( opcional )
- [x] Salvar informações dos filmes em banco local ( opcional )

#### Dependencias usadas
```
androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version
androidx.room:room-runtime:$room_version
com.google.android.material:material:1.2.1
com.squareup.retrofit2:retrofit:2.9.0
com.squareup.retrofit2:converter-moshi:2.9.0
com.squareup.picasso:picasso:2.71828
```
### Observações
Programa criado usando o android studio 4.0.1 com a linguagem Java usando a biblioteca do Retrofit para consumir a Api do The Movie DB e o Picasso para ajudar com as fotos,
 os layouts foram construidos usando Material Design da google
 #### Aplicação simples usando o BottomNavigator para mudanças de telas sendo elas:
 - **Populares:** Lista os filmes mais procurados atualmente de 10 em 10 apenas solicitando novos quando o scroll chega no final.
 - **Em Cartaz:** Mesma funcionalidade da primeira porem mudando a listagem de filmes para os filmes que estão em cartaz no momento
 - **Destaques:** Ambas telas anteriores contem ação de clique em suas listas para abrir a tela de destaques contendo seus dados gerais e podendo ser adcionados como favoritos
 - **Favoritos:** Tela onde os filmes escolhidos para serem favoritos são listados (filmes adcionados como favoritos são salvos localmente para uma visualização mais 
 rapida e sem precisar de conexão )
 - **Series:** Tela bonus onde é listada as series mais populares atualmente tambem com funcionalidade de clique para detalhes
 
 ### Build
 Para realizar o download do App.Debug acesse o link abaixo ( lembrando que o target api é acima da 23, Android 6.0 )
 
 [DEBUG](https://drive.google.com/file/d/1lENAlBc1kVYu-SdZbodJyuEUcU02KxxI/view?usp=sharing)
 
 
 - Video demonstrando o funcionamento do app
 
 [VIDEO](https://drive.google.com/file/d/17pUTzkbVu3D4wcl-NEoMRhyckOwuSFAY/view?usp=sharing)
