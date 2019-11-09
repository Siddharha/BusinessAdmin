package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.adapters.CategorySpinnerAdapter
import `in`.creativelizard.businessadvisor.models.networkModels.CategoryArray
import `in`.creativelizard.businessadvisor.viewModels.BusinessPageViewModel
import `in`.creativelizard.businessadvisor.views.utils.AppUtils
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_business.view.*


class BusinessFragment : Fragment() {

    lateinit var rootView:View
    lateinit var businessTypeListAdapter:CategorySpinnerAdapter
    lateinit var businessPageViewModel: BusinessPageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_business, container, false)
        initialize()
        onActionPerform()
        return rootView
    }

    private fun initialize() {
        businessPageViewModel = ViewModelProviders.of(this).get(BusinessPageViewModel::class.java)
        businessTypeListAdapter = CategorySpinnerAdapter(activity!!,R.layout.category_cell,businessPageViewModel.getBusinessTypeList().value!!)
        rootView.spinner.adapter = businessTypeListAdapter
        rootView.clBusiness.translationX =  resources.displayMetrics.widthPixels.toFloat()

        Handler().postDelayed({
            rootView.clBusiness.animate().translationX(1f).duration = 500
        },100)

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
