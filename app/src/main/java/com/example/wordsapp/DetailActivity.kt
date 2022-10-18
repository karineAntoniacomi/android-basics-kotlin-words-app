/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    // objeto complementar para organizar constantes e torná-las acessíveis fora da DetailActivity
    // palavra-chave companion: significa que o objeto está associado à classe DetailActivity
    companion object {
        const val LETTER = "letter"
        // Cria intent implícita
        //mesmo URL base é usado para todas as pesquisas = constante
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recupera um objeto de ligação que permite fzer referência a views por id
        // Retrieve a binding object that allows you to refer to views by id name
        // Names are converted from snake case to camel case.
        // For example, a View with the id word_one is referenced as binding.wordOne
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the LETTER from the Intent extras
        // intent.extras.getString returns String? (String or null)
        // so toString() guarantees that the value will be a String
        val letterId = intent?.extras?.getString(LETTER).toString()
        // a propriedade intent é de qualquer atividade, e mantém uma
        // referência à intent usada para iniciar a atividade.
        // a propriedade "extras" é do tipo Bundle e fornece uma maneira
        // de acessar todos os extras transmitidos à intent.
        // O Ponto de interrogação nas propriedades intent e extras é porque
        // elas são anuláveis (podem ter um valor ou não / Null).
        // Se a intent for null, o app não tentará acessar a propriedade "extras".
        // Se a extras for nula, o código não tentará chamar getString().

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(letterId, this)

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix) + " " + letterId
    }
}