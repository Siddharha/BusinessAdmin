package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.models.networkModels.LoginOutput
import `in`.creativelizard.businessadvisor.viewModels.LoginViewModel
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.nio.file.WatchEvent


class LoginFragment : Fragment() {

    lateinit var rootView: View
    lateinit var loginViewModel: LoginViewModel
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
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

    }

    private fun onActionPerform() {

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
            (it as FloatingActionButton).hide()

            loginViewModel.getLoginWithToken(rootView.etTokenId.text.toString()).observe(this,
                Observer {response ->
                    //Toast.makeText(activity!!,Gson().toJson(it),Toast.LENGTH_SHORT).show()
                    val loginData = Gson().fromJson(response,LoginOutput::class.java)
                    if(loginData.success == 1){
                        Handler().postDelayed({
                            Navigation.findNavController(rootView).navigate(R.id.action_loginFragment_to_formFragment)
                        },1000)
                    }else{
                        it.show()
                        Toast.makeText(activity!!,loginData.message,Toast.LENGTH_SHORT).show()
                    }
                })
           // callLogin()


        }
    }


}
