package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment() {

    lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_login, container, false)
        onActionPerform()
        return rootView
    }

    private fun onActionPerform() {
        rootView.fabNext.setOnClickListener {
            (it as FloatingActionButton).hide()

            Handler().postDelayed({
                Navigation.findNavController(rootView).navigate(R.id.action_loginFragment_to_formFragment)
            },1000)

        }
    }


}
