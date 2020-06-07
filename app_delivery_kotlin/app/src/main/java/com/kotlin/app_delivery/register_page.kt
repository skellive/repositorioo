package com.kotlin.app_delivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register_page.*


class register_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        setup()
    }

    private fun setup() {
        val usuario = editTextTextPersonName4.text.toString()
        val password = editTextTextPassword2.text.toString()
        val confPass = editTextTextPassword3.text.toString()
        title = "Autenticacion"
        button_continuar_registro.setOnClickListener {
            if (editTextTextPersonName4.text.isNotEmpty() && editTextTextPassword2.text.isNotEmpty() && editTextTextPassword3.text.isNotEmpty()) {
                if (password.equals(confPass)) {
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(usuario,
                            password).addOnCompleteListener {
                            if (it.isSuccessful){
                                    showLogin(it.result?.user?.email ?:"", ProviderType.BASIC)

                            }else{
                                showAlertAutentic()
                            }
                        }


                } else {
                    showAlertContNoCoinciden()
                }

            } else {
                showAlertCamposVacios()
            }
        }
    }


    private fun showLogin(email: String, provider: ProviderType){
        val MainIntent = Intent(this, AuthActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(MainIntent)


    }


    private fun showAlertCamposVacios(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Campos vacios. Por favor llene los campos correspondientes")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun showAlertContNoCoinciden(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Las contraseñas no coinciden")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun showAlertAutentic(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al registrar o auntenticar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }


}