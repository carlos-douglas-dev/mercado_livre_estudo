package com.example.mercadolivreaula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

class CadastroActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var autenticacao: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        inicializaComponentesInterface()
    }

    fun criarConta(view: View) {
        if (validarCampos()) {
            // Cadastro do usuário
            cadastrarUsuario()
        } else {

        }
    }

    fun cadastrarUsuario() {
        val email: String = editEmail.text.toString()
        val senha: String = editSenha.text.toString()

        autenticacao.createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Sucesso ao cadastrar usuário", Toast.LENGTH_SHORT).show()
                finish()
                startActivity(
                    Intent(
                        this,
                        PrincipalActivity::class.java
                    )
                )
            } else Toast.makeText(this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show()
        }
    }

    fun validarCampos(): Boolean {
        return editEmail.text.isNotEmpty() && editSenha.text.isNotEmpty()
//        Toast(
//            this,
//            "Preencha seu e-mail",
//            Toast.LENGTH_LONG
//        ).show()
    }

    fun inicializaComponentesInterface() {
        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)
        autenticacao = Firebase.auth
    }
}