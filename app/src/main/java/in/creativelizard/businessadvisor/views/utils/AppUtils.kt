package `in`.creativelizard.businessadvisor.views.utils

import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*
import kotlin.collections.ArrayList

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

    }

}