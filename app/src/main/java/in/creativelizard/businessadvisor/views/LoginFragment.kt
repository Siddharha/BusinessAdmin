package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.models.networkModels.LoginOutput
import `in`.creativelizard.businessadvisor.utils.Constant
import `in`.creativelizard.businessadvisor.utils.CustomScannerActivity
import `in`.creativelizard.businessadvisor.viewModels.LoginViewModel
import `in`.creativelizard.businessadvisor.views.utils.Pref
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.nio.file.WatchEvent


class LoginFragment : Fragment() {

    lateinit var rootView: View
    lateinit var loginViewModel: LoginViewModel
    lateinit var pref:Pref
    private var intentIntg: IntentIntegrator? = null
    lateinit var mFusedLocationClient:FusedLocationProviderClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_login, container, false)
        initialize()
        onActionPerform()
        return rootView
    }

    private fun initialize() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)
        pref = Pref(activity!!)
        intentIntg = IntentIntegrator(activity)
        rootView.etTokenId.setText(pref.getSession(Constant.USER_TOKEN))
        loginViewModel = /*ViewModelProviders.of(this).get(LoginViewModel::class.java)*/(context as MainActivity).loginViewModel

    }

    private fun onActionPerform() {

        loginViewModel.onLocationPermissionGrant().observe(this, Observer {
            if(it){
if(isLocationEnabled()){
    rootView.fabNext.performClick()
}else{
    val dialog = AlertDialog.Builder(activity!!)
    dialog.setMessage("Location Not Enabled! Please Enable location first as this app need your location to perform.")
        .create().show()
}
            }
        })

        rootView.etTokenId.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                rootView.tvLength.text = "${s?.length}/30"
            }

        })

        rootView.fabNext.setOnClickListener {

            if((context as MainActivity).checkPermissions()){

                mFusedLocationClient.lastLocation.addOnCompleteListener {
                    val location = it.result
                    if (location == null) {
                       // requestNewLocationData()
                    } else {
                        Toast.makeText(activity!!,"Lat: ${location.latitude}",Toast.LENGTH_SHORT).show()
                    }
                }

                (it as FloatingActionButton).hide()

                loginViewModel.getLoginWithToken(rootView.etTokenId.text.toString()).observe(this,
                    Observer {response ->

                        val loginData = Gson().fromJson(response,LoginOutput::class.java)
                        if(loginData.success == 1){
                            pref.setSession(Constant.USER_TOKEN,rootView.etTokenId.text.toString())
                            pref.setSession(Constant.USER_ID,loginData.user.id)
                            Handler().postDelayed({
                                Navigation.findNavController(rootView).navigate(R.id.action_loginFragment_to_formFragment)
                            },1000)
                        }else{
                            it.show()
                            Toast.makeText(activity!!,loginData.message,Toast.LENGTH_SHORT).show()
                        }
                    })

                loginViewModel.onQRDetected().observe(this, Observer {
                    rootView.etTokenId.setText(it)
                })
            }else{
                (context as MainActivity).requestPermissions()
            }



        }

        rootView.imgQR.setOnClickListener {
            startScan()
        }
    }


    fun startScan() {

        intentIntg?.setBeepEnabled(true)
        intentIntg?.setOrientationLocked(false)?.captureActivity = CustomScannerActivity::class.java
        intentIntg?.setBarcodeImageEnabled(true)
        intentIntg?.initiateScan()
        //FLAG_FOR_RESULT = 0
    }

    private fun isLocationEnabled():Boolean{
        val locationManager =  activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }


}
