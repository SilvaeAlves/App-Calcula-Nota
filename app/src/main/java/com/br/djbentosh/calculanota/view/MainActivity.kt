package com.br.djbentosh.calculanota.view

import   android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.br.djbentosh.calculanota.R
import com.br.djbentosh.calculanota.model.AlunoModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.alunoCinco
import kotlinx.android.synthetic.main.activity_main.alunoDois
import kotlinx.android.synthetic.main.activity_main.alunoQuatro
import kotlinx.android.synthetic.main.activity_main.alunoTres
import kotlinx.android.synthetic.main.activity_main.alunoUm

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalc.setOnClickListener {
            val receberAluno = capturarDados()
            // só passa a proxima tela tem que ser igual a cinco
            if (receberAluno.size == 5)
                receberListaAlunos(receberAluno)
        }
    }

    private fun receberListaAlunos(listaDeAlunos: MutableList<AlunoModel>) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("dados", Gson().toJson(listaDeAlunos))
        startActivity(intent)
    }

    // captura as notas para efeito de cálculo
    private fun capturarDados(): MutableList<AlunoModel> {
        val aluno = mutableListOf<AlunoModel>()

        if (edNota1.text.toString() == "" && edNota2.text.toString() == ""  &&
            edNota3.text.toString() == "" && edNota4.text.toString() == "" &&
            edNota5.text.toString() == "" && edNota6.text.toString() == "" &&
            edNota7.text.toString() == "" && edNota8.text.toString() == "" &&
            edNota9.text.toString() == "" && edNota10.text.toString() == ""
        ) Toast.makeText(
            applicationContext,
            "Campos de Notas vazios por favor preenchê-los",
            Toast.LENGTH_LONG
        ).show()
        else {

            if (notaValorInvalido(
                    edNota1.text.toString(),
                    edNota2.text.toString(),
                    alunoUm.text.toString()
                )
            ) {
                val aluno1 = AlunoModel(
                    alunoUm.text.toString(),
                    edNota1.text.toString().toInt(),
                    edNota2.text.toString().toInt()
                )
                aluno.add(aluno1)
            } else Toast.makeText(
                applicationContext,
                "Valor nota Inválida do Aluno 1",
                Toast.LENGTH_LONG
            ).show()

            if (notaValorInvalido(
                    edNota3.text.toString(),
                    edNota4.text.toString(),
                    alunoDois.text.toString()
                )
            ) {
                val aluno2 = AlunoModel(
                    alunoDois.text.toString(),
                    edNota3.text.toString().toInt(),
                    edNota4.text.toString().toInt()
                )
                aluno.add(aluno2)
            } else Toast.makeText(
                applicationContext,
                "Valor nota Inválida do Aluno 2",
                Toast.LENGTH_LONG
            ).show()

            if (notaValorInvalido(
                    edNota5.text.toString(),
                    edNota6.text.toString(),
                    alunoTres.text.toString()
                )
            ) {
                val aluno3 = AlunoModel(
                    alunoTres.text.toString(),
                    edNota5.text.toString().toInt(),
                    edNota6.text.toString().toInt()
                )
                aluno.add(aluno3)
            } else Toast.makeText(
                applicationContext,
                "Valor nota Inválida do Aluno 3",
                Toast.LENGTH_LONG
            ).show()

            if (notaValorInvalido(
                    edNota7.text.toString(),
                    edNota8.text.toString(),
                    alunoQuatro.text.toString()
                )
            ) {
                val aluno4 = AlunoModel(
                    alunoQuatro.text.toString(),
                    edNota7.text.toString().toInt(),
                    edNota8.text.toString().toInt()
                )
                aluno.add(aluno4)
            } else Toast.makeText(
                applicationContext,
                "Valor nota Inválida do Aluno 4",
                Toast.LENGTH_LONG
            ).show()

            if (notaValorInvalido(
                    edNota9.text.toString(),
                    edNota10.text.toString(),
                    alunoCinco.text.toString()
                )
            ) {
                val aluno5 = AlunoModel(
                    alunoCinco.text.toString(),
                    edNota9.text.toString().toInt(),
                    edNota10.text.toString().toInt()
                )
                aluno.add(aluno5)
            } else Toast.makeText(
                applicationContext,
                "Valor nota Inválida do Aluno 5",
                Toast.LENGTH_LONG
            ).show()

            aluno.forEach { it.resultadoFinal = (calcularNota(nota1 = it.nota1, nota2 = it.nota2)) }
        }
        return aluno
    }

    // single expression function
    private fun calcularNota(nota1: Int, nota2: Int) = (nota1 + nota2) / 2

    //
    private fun notaValorInvalido(mediaNota1: String?, mediaNota2: String?, s: String): Boolean {
        // verifica se a nota não foi preenchida
        if (mediaNota1 != "" && mediaNota2 != "") {
        // verifica se a nota é entre 0 e 10
            if (mediaNota1?.toInt() !in 0..10 || mediaNota2?.toInt() !in 0..10) {
                return false
            }
        } else {
            return false
        }
        return true
    }
}



