package `in`.creativelizard.businessadvisor.views


import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.viewModels.BottomSheetViewModel
import `in`.creativelizard.businessadvisor.views.utils.AppUtils
import android.animation.Animator
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_personal.view.*


class PersonalFragment : Fragment() {
    lateinit var rootView:View
    lateinit var bottomSheetViewModel: BottomSheetViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_personal, container, false)
        initialize()
        onActionPerform()
        return rootView
    }

    private fun onActionPerform() {
        rootView.fabProfileImg.setOnClickListener {
            AppUtils.profileTypeChooserPopup(activity!!,bottomSheetViewModel)
        }
        rootView.etPhone.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(!hasFocus && (v as EditText).length() >0){
                    (context as MainActivity).businessProfileinp.number = v.text.toString()
                }

            }

        rootView.etEmail.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(!hasFocus && (v as EditText).length() >0){
                    (context as MainActivity).businessProfileinp.email = v.text.toString()
                }

            }

        rootView.etWebsite.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if(!hasFocus && (v as EditText).length() >0){
                    (context as MainActivity).businessProfileinp.web_address = v.text.toString()
                }

            }
    }

    private fun initialize() {
        bottomSheetViewModel = ViewModelProviders.of(this).get(BottomSheetViewModel::class.java)
        rootView.imgLogo.scaleX = 0f
        rootView.imgLogo.scaleY = 0f
        rootView.imgBanner.translationY = - resources.displayMetrics.heightPixels.toFloat()/3

        rootView.imgBanner.animate().translationY(0f).setListener(object :Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                Glide.with(activity!!)
                    .load("https://images.pexels.com/photos/145939/pexels-photo-145939.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
                    .apply(RequestOptions.circleCropTransform())
                    .listener(object :RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Toast.makeText(activity!!,"Unable to load!",Toast.LENGTH_SHORT).show()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            rootView.imgLogo.animate().scaleX(1f).scaleY(1f).start()
                            return false

                        }

                    })
                    .into(rootView.imgLogo)
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })

    }


}
