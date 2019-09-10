package `in`.creativelizard.businessadvisor.views.utils

import android.app.TimePickerDialog
import android.view.View
import android.widget.TextView
import java.util.*

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