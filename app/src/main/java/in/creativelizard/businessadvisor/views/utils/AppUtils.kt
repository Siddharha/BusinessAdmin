package `in`.creativelizard.businessadvisor.views.utils

import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.viewModels.BottomSheetViewModel
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.dialog_profile_chooser.*
import java.util.*
import kotlin.collections.HashMap

class AppUtils {
    companion object{


        fun getTime(it:View) {
            val timeDialog = TimePickerDialog(it.context,
                TimePickerDialog.OnTimeSetListener { v, hourOfDay, minute ->
                    (it as TextView).text = "$hourOfDay : $minute"
                }, Calendar.HOUR_OF_DAY, Calendar.MINUTE, false
            )
            timeDialog.show()
        }

        fun profileTypeChooserPopup(context:Context,bottomsheetModel:BottomSheetViewModel){
            val dialog = Dialog(context)
            dialog.setTitle("Choose Upload type")
            dialog.setContentView(R.layout.dialog_profile_chooser)

            dialog.btnShowcaseImg.setOnClickListener {
                val mapData = HashMap<String,Int>()
                mapData.set("type",0)
                bottomsheetModel.liveBottomSheetData.value = mapData
            }
            dialog.show()
        }

    }

}