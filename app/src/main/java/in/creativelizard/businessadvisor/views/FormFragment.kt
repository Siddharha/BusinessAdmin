package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.fragment_form.view.*


class FormFragment : Fragment() {

    lateinit var rootView:View
    lateinit var personalFragment: PersonalFragment
    lateinit var businessFragment: BusinessFragment
    lateinit var toolsFragment: ToolsFragment
    lateinit var mToolbar:Toolbar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_form, container, false)
        initialize()
        onActionPerform()
        return rootView
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
        Toast.makeText(activity!!,"Not Implemented!",Toast.LENGTH_SHORT).show()
    }


}

