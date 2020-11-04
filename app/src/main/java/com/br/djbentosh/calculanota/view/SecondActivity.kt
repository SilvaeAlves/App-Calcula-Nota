package com.br.djbentosh.calculanota.view

import android.os.Bundle
import android.util.Log
import com.br.djbentosh.calculanota.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_second.*
import com.br.djbentosh.calculanota.model.AlunoModel as AlunoModel

class SecondActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btnVoltar.setOnClickListener {
            finish()
        }
        // pegar os dados
        val itens = intent.extras?.getString("dados")

        // converte de gson para lista alunos (evitar consumir memória)
        val itemType = object : TypeToken<MutableList<AlunoModel>>() {}.type
        val aluno = Gson().fromJson<MutableList<AlunoModel>>(itens, itemType)
        receberNotas(aluno)
        receberStatus(aluno)
    }

    // informa o valor da nota média
    private fun receberNotas(notas: MutableList<AlunoModel>) {
        tNotaFinal1.text = notas[0].resultadoFinal.toString()
        tNotaFinal2.text = notas[1].resultadoFinal.toString()
        tNotaFinal3.text = notas[2].resultadoFinal.toString()
        tNotaFinal4.text = notas[3].resultadoFinal.toString()
        tNotaFinal5.text = notas[4].resultadoFinal.toString()
    }

    private fun avaliarNotas(mediaNota: Int) = if (mediaNota >= 7) "Aprovado" else "Reprovado"

    // informa se o aluno foi reprovado ou aprovado
    private fun receberStatus(notas: MutableList<AlunoModel>) {
        resultAluno1.text = avaliarNotas(notas[0].resultadoFinal)
        resultAluno2.text = avaliarNotas(notas[1].resultadoFinal)
        resultAluno3.text = avaliarNotas(notas[2].resultadoFinal)
        resultAluno4.text = avaliarNotas(notas[3].resultadoFinal)
        resultAluno5.text = avaliarNotas(notas[4].resultadoFinal)
    }
}