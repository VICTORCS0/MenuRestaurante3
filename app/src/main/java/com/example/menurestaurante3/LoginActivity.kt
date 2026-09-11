package com.example.menurestaurante3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegistro = findViewById<Button>(R.id.btnIrRegistro)

        btnLogin.setOnClickListener {

            val correo = etCorreo.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (correo.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                    this,
                    "Completa todos los campos.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener

            }

            auth.signInWithEmailAndPassword(correo, password)
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        startActivity(
                            Intent(this, MainActivity::class.java)
                        )

                        finish()

                    } else {

                        Toast.makeText(
                            this,
                            "Correo o contraseña incorrectos.",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                }

        }

        btnRegistro.setOnClickListener {

            startActivity(
                Intent(this, RegistroActivity::class.java)
            )

        }

    }
}