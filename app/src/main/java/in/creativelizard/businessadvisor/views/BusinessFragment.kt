package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.views.utils.AppUtils
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_business.view.*


class BusinessFragment : Fragment() {

    lateinit var rootView:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_business, container, false)
        onActionPerform()
        return rootView
    }

    @SuppressLint("SetTextI18n")
    private fun onActionPerform() {
        rootView.tvOpenTime.setOnClickListener {
            AppUtils.getTime(it)
        }
        rootView.tvCloseTime.setOnClickListener {
            AppUtils.getTime(it)
        }
    }


}
