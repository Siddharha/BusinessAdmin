package `in`.creativelizard.businessadvisor.views

import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.views.utils.Pref
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    lateinit var pref:Pref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        initialize()
        onactionPerform()
    }

    private fun initialize() {
        pref = Pref(this)
    }

    private fun onactionPerform() {
        swLogout.setOnCheckedChangeListener { btn, isChecked ->
            if(isChecked){
                val dialog = AlertDialog.Builder(this,R.style.Theme_MaterialComponents_Light_Dialog_Alert)
                dialog.setMessage("Logout From the app?")
                    .setPositiveButton("yes"){d,_->
                        pref.clearPref()

                        d.dismiss()
                        finish()
                    }
                    .setNegativeButton("Dismiss"){d,_->
                        d.dismiss()
                    }
                    .create().show()
            }
        }
    }
}
