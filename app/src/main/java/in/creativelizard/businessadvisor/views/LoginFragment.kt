package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


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

    }


}
