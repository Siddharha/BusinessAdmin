package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.models.CreateBusinessInput
import `in`.creativelizard.businessadvisor.models.GetBusinessInput
import `in`.creativelizard.businessadvisor.utils.Constant
import `in`.creativelizard.businessadvisor.viewModels.FromPageViewModel
import `in`.creativelizard.businessadvisor.views.utils.Pref
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_business.view.*
import kotlinx.android.synthetic.main.fragment_form.view.*
import kotlinx.android.synthetic.main.fragment_personal.view.*


class FormFragment : Fragment() {

    lateinit var rootView:View
    lateinit var personalFragment: PersonalFragment
    lateinit var businessFragment: BusinessFragment
    lateinit var fromPageViewModel:FromPageViewModel
    lateinit var toolsFragment: ToolsFragment
    lateinit var mToolbar:Toolbar
    lateinit var _pref:Pref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_form, container, false)
        initialize()
        loadData()
        onActionPerform()
        return rootView
    }

    private fun loadData() {
        Log.e("response",_pref.getSession(Constant.USER_ID))
        val businessInp = GetBusinessInput(_pref.getSession(Constant.USER_ID).toInt())
        fromPageViewModel.getBusiness(businessInp).observe(this, Observer {
            if(it.success == 1){
                val itm = (context as MainActivity).businessProfileinp
                itm.addresses = it.business_card.addresses
                itm.title = it.business_card.title
                itm.number = it.business_card.number
                itm.email = it.business_card.email
                itm.web_address = it.business_card.web_address
                itm.open_time = it.business_card.open_time

                personalFragment.rootView.etPhone.setText(itm.number)
                personalFragment.rootView.etEmail.setText(itm.email)
                personalFragment.rootView.etWebsite.setText(itm.web_address)
            }
        })
    }

    private fun onActionPerform() {
        rootView.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mnuPersonal ->  {
                    // mnuNavController.navigate(R.id.allFeedsFragment)
                    try{
                    loadFragment(personalFragment,"Personal Details")
                    true}catch (e:Exception){return@setOnNavigationItemSelectedListener false}}
                R.id.mnuBusness ->  {
                    //   mnuNavController.navigate(R.id.courcesFragment)
                    try{
                    loadFragment(businessFragment,"Business Details")
                    true}catch (e:Exception){return@setOnNavigationItemSelectedListener false}}
                R.id.mnuTools ->  {
                    try{
                    loadFragment(toolsFragment,"Tools")
                        true}catch (e:Exception){return@setOnNavigationItemSelectedListener false}}
                else -> false
            }
        }
    }

    private fun initialize() {
        setHasOptionsMenu(true)
        _pref = Pref(activity!!)
        mToolbar = rootView.toolbar as Toolbar
        personalFragment = PersonalFragment()
        businessFragment = BusinessFragment()
        toolsFragment = ToolsFragment()
        //loadDefault fragment
        mToolbar.title = "Personal Details"
        (context as MainActivity).setSupportActionBar(mToolbar)
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(rootView.flContainer.id, personalFragment)

        transaction.commit()
        fromPageViewModel = ViewModelProviders.of(this).get(FromPageViewModel::class.java)

    }

    private fun loadFragment(fragment: Fragment, title:String){
        try {
            mToolbar.title = title
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
            transaction?.addToBackStack(null)
            transaction?.replace(rootView.flContainer.id, fragment)
            //transaction.addToBackStack(null)

            transaction?.commit()
        }catch (e:Exception){
            e.printStackTrace()
        }

        try {
            val itm = (context as MainActivity).businessProfileinp
            personalFragment.rootView.etPhone.setText(itm.number)
            personalFragment.rootView.etEmail.setText(itm.email)
            personalFragment.rootView.etWebsite.setText(itm.web_address)

            businessFragment.rootView.etTitle.setText(itm.title)
            businessFragment.rootView.tvOpenTime.text = itm.open_time
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mnuDone -> callCreateBusinessAPI()
        }
        return false
    }

    private fun callCreateBusinessAPI() {
        //createBusinessProfile
        val itm = (context as MainActivity).businessProfileinp
        val inp = CreateBusinessInput(itm.addresses,itm.close_time,itm.description,itm.email,itm.lat_location,
            itm.lon_location,itm.number,itm.open_time,itm.title,itm.type,itm.uder_id,itm.web_address)
        fromPageViewModel.createBusiness(inp).observe(this, Observer {
            if(it.success == 1){
                Toast.makeText(activity!!,it.message,Toast.LENGTH_SHORT).show()
            }
        })
       // Toast.makeText(activity!!,"data: ${Gson().toJson((context as MainActivity).businessProfileinp)}",Toast.LENGTH_SHORT).show()

    }


}

