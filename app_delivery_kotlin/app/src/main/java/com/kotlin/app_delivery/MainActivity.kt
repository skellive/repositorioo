package com.kotlin.app_delivery

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register_page.*




class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()


    }
    private fun setup(){

        txt_registro.setOnClickListener {
            val registerIntent = Intent( this, register_page:: class.java)
            startActivity(registerIntent)
        }

        button2_login.setOnClickListener {
            if (editTextTextPersonName.text.isNotEmpty() && editTextTextPassword.text.isNotEmpty()) {

                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(editTextTextPersonName.text.toString(),
                            editTextTextPassword.text.toString()).addOnCompleteListener {
                            if (it.isSuccessful){
                                showLogin(it.result?.user?.email ?:"", ProviderType.BASIC)

                            }else{
                                showAlertAutentic()
                            }
                        }




            } else {
                showAlertCamposVacios()
            }
        }
    }


    private fun showLogin(email: String, provider: ProviderType){
        val AuthIntent = Intent(this, AuthActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(AuthIntent)


    }


    private fun showAlertCamposVacios(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Campos vacios. Por favor llene los campos correspondientes")
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




