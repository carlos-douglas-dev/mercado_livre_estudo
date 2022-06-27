package com.example.mercadolivreaula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var autenticacao: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inicializaComponentesInterface()
    }

    fun inicializaComponentesInterface() {
        editEmail = findViewById(R.id.editEmailLogin)
        editSenha = findViewById(R.id.editSenhaLogin)
        autenticacao = Firebase.auth
    }

    fun validarCampos(): Boolean {
        return editEmail.text.isNotEmpty() && editSenha.text.isNotEmpty()
//        Toast(
//            this,
//            "Preencha seu e-mail",
//            Toast.LENGTH_LONG
//        ).show()
    }

    fun entrar(view: View) {
        val email: String = editEmail.text.toString()
        val senha: String = editSenha.text.toString()

        if (validarCampos()) {
            // Login do usuário
            autenticacao.signInWithEmailAndPassword(
                email, senha
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Sucesso ao fazer login de usuário",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                    startActivity(
                        Intent(
                            this,
                            PrincipalActivity::class.java
                        )
                    )
                } else Toast.makeText(this, "Erro ao logar usuário", Toast.LENGTH_SHORT).show()
            }
        } else {

        }
    }
}