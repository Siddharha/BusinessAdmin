package `in`.creativelizard.businessadvisor.views

import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.models.networkModels.BusinessProfileInput
import `in`.creativelizard.businessadvisor.viewModels.SplashViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var businessProfileinp:BusinessProfileInput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {

        businessProfileinp = BusinessProfileInput()
    }
}
