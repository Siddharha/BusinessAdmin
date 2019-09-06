package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.views.utils.AppUtils
import `in`.creativelizard.businessadvisor.views.utils.ToolsFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                    loadFragment(personalFragment,"Personal Details")
                    true}
                R.id.mnuBusness ->  {
                    //   mnuNavController.navigate(R.id.courcesFragment)
                    loadFragment(businessFragment,"Business Details")
                    true}
                R.id.mnuTools ->  {
                    loadFragment(toolsFragment,"Tools")
                    true}
                else -> false
            }
        }
    }

    private fun initialize() {
        mToolbar = rootView.toolbar as Toolbar
        personalFragment = PersonalFragment()
        businessFragment = BusinessFragment()
        toolsFragment = ToolsFragment()
        loadFragment(personalFragment,"Personal Details")
    }

    private fun loadFragment(fragment: Fragment, title:String){
        mToolbar.title = title
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(rootView.flContainer.id, fragment)
        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
        transaction.addToBackStack(null)

        transaction.commit()
    }


}

