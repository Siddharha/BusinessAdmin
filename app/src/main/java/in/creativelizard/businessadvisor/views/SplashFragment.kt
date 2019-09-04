package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.viewModels.SplashViewModel
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
        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    private fun onActionPerform() {

        Handler().postDelayed({
            splashViewModel.isSplashLaded.value = true
        },1000)

        splashViewModel.getSplashLoadStatus().observe(this, Observer {
            if(it){
                try {
                    Navigation.findNavController(rootView).navigate(R.id.action_splashFragment_to_loginFragment)
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        })

    }


}
