package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.utils.Constant
import `in`.creativelizard.businessadvisor.viewModels.SplashViewModel
import `in`.creativelizard.businessadvisor.views.utils.Pref
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation

class SplashFragment : Fragment() {

    lateinit var rootView:View
    lateinit var splashViewModel: SplashViewModel
    lateinit var pref:Pref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_splash, container, false)
        initialize()
        onActionPerform()
        return rootView
    }

    private fun initialize() {
        pref = Pref(activity!!)
        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    private fun onActionPerform() {

        Handler().postDelayed({
            splashViewModel.isSplashLaded.value = true
        },5000)

        splashViewModel.getSplashLoadStatus().observe(this, Observer {
            if(it){
                try {
                    if(pref.getSession(Constant.USER_TOKEN).isEmpty()){
                        Navigation.findNavController(rootView).navigate(R.id.action_splashFragment_to_loginFragment)
                    }else{
                        Navigation.findNavController(rootView).navigate(R.id.action_splashFragment_to_formFragment)
                    }

                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        })

    }


}
