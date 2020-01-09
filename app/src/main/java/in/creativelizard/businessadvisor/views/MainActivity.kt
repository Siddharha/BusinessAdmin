package `in`.creativelizard.businessadvisor.views

import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.models.networkModels.BusinessProfileInput
import `in`.creativelizard.businessadvisor.viewModels.LoginViewModel
import `in`.creativelizard.businessadvisor.viewModels.SplashViewModel
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    lateinit var businessProfileinp:BusinessProfileInput
    lateinit var loginViewModel: LoginViewModel
    private var PERMISSION_ID = 100

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
     //   Log.e("response",result.contents)
        if(result.contents!=null){
            loginViewModel.setQRForResult(result.contents)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    fun checkPermissions():Boolean{
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    fun requestPermissions(){
        ActivityCompat.requestPermissions(
            this,
             arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Granted. Start getting the location information

                loginViewModel.setLocationPermissionResult(true)
            }
        }
    }
}
