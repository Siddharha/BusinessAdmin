package `in`.creativelizard.businessadvisor.views

import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.models.networkModels.BusinessProfileInput
import `in`.creativelizard.businessadvisor.viewModels.LoginViewModel
import `in`.creativelizard.businessadvisor.viewModels.SplashViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    lateinit var businessProfileinp:BusinessProfileInput
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {

        businessProfileinp = BusinessProfileInput()
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        Log.e("response",result.contents)
        loginViewModel.setQRForResult(result.contents)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
