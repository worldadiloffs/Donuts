package uz.itschool.secondprojectforbsb

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.BaseExpandableListAdapter
import android.widget.Button
import android.widget.TextView
import uz.itschool.secondprojectforbsb.databinding.ChildLayoutBinding
import uz.itschool.secondprojectforbsb.databinding.ParentItemBinding

class TypeFoodAdapter(
    var context: Context,
    var types: MutableMap<String, MutableList<Category>>,
    var type: String
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return 1
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return types.get(type)!!.size
    }

    override fun getGroup(groupPosition: Int): String {
        return type
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return types.get(type)!!.get(childPosition).toString()
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        position: Int, p1: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View {

        val binding: ParentItemBinding
        if (convertView == null) {
            binding =
                ParentItemBinding.inflate(LayoutInflater.from(parent!!.context), parent, false)
        } else {
            binding = ParentItemBinding.bind(convertView)
        }
        return binding.root
    }

    override fun getChildView(
        parentPostion: Int,
        childPosition: Int,
        p2: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding: ChildLayoutBinding
        if (convertView == null) {
            binding =
                ChildLayoutBinding.inflate(LayoutInflater.from(parent!!.context), parent, false)
        } else {
            binding = ChildLayoutBinding.bind(convertView)
        }

        val category = types.get(type)!!.get(childPosition)
        binding.img.setImageResource(category.img)
        binding.name.text = category.name
        return binding.root
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

}