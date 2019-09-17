package `in`.creativelizard.businessadvisor.views.utils

import android.content.Context
import android.content.SharedPreferences

class Pref (_thisContext: Context) {
    //private Activity _activity;
    private val PREF_FILE = "in.creativelizard.businessadvisor"
    private val spref = _thisContext.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
    private val _editorSpref = spref.edit()
    fun getSharedPreferencesInstance(): SharedPreferences {
        return spref!!
    }

    fun getSharedPreferencesEditorInstance(): SharedPreferences.Editor {
        return _editorSpref!!
    }



    fun clearPref() {
        _editorSpref?.clear()
        _editorSpref?.commit()
    }

    fun getSession(key: String): String {
        return spref?.getString(key, "")!!
    }

    fun getIntegerSession(key: String): Int {
        return spref?.getInt(key, Integer.MIN_VALUE)!!
    }

    fun getBooleanSession(key: String): Boolean {
        return spref?.getBoolean(key, false)!!
    }

    fun setSession(key: String?, value: String?) {
        if (key != null && value != null) {
            _editorSpref?.putString(key, value)
            _editorSpref?.commit()
        }
    }

    fun setSession(key: String?, value: Int) {
        if (key != null) {
            _editorSpref?.putInt(key, value)
            _editorSpref?.commit()
        }
    }

    fun setSession(key: String?, value: Boolean) {
        if (key != null) {
            _editorSpref?.putBoolean(key, value)
            _editorSpref?.commit()
        }
    }


}