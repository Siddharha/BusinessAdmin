package `in`.creativelizard.businessadvisor.adapters

import `in`.creativelizard.businessadvisor.R
import `in`.creativelizard.businessadvisor.models.networkModels.CategoryArray
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.view.LayoutInflater
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import kotlinx.android.synthetic.main.category_cell.view.*


class CategorySpinnerAdapter(context: Context,
                             @LayoutRes private val layoutResource: Int,
                             private val values: List<CategoryArray>) : ArrayAdapter<CategoryArray>(context, layoutResource, values) {

    override fun getItem(position: Int): CategoryArray = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //val view = createViewFromResource(convertView, parent, layoutResource)
        val view = convertView ?: LayoutInflater.from(context).inflate(layoutResource, parent, false)
        return bindData(getItem(position), view)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        //val view = createViewFromResource(convertView, parent, R.layout.category_cell)
        val view = convertView ?: LayoutInflater.from(context).inflate(layoutResource, parent, false)
        return bindData(getItem(position), view)
    }

    private fun bindData(value: CategoryArray, view: View): View {
        view.tvTitle.text = value.name
        return view
    }
}