package com.example.menurestaurante3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        auth = FirebaseAuth.getInstance()

        val etCorreo = findViewById<EditText>(R.id.etCorreoRegistro)
        val etPassword = findViewById<EditText>(R.id.etPasswordRegistro)
        val etConfirmar = findViewById<EditText>(R.id.etConfirmarPassword)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnVolver = findViewById<Button>(R.id.btnVolverLogin)

        btnRegistrar.setOnClickListener {

            val correo = etCorreo.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmar = etConfirmar.text.toString().trim()

            if (correo.isEmpty() || password.isEmpty() || confirmar.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmar) {
                Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        Toast.makeText(
                            this,
                            "Usuario registrado correctamente.",
                            Toast.LENGTH_LONG
                        ).show()

                        startActivity(
                            Intent(this, LoginActivity::class.java)
                        )

                        finish()

                    } else {

                        Toast.makeText(
                            this,
                            it.exception?.message,
                            Toast.LENGTH_LONG
                        ).show()

                    }

                }

        }

        btnVolver.setOnClickListener {

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()

        }

    }
}