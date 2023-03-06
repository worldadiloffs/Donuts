package uz.itschool.secondprojectforbsb

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import uz.itschool.secondprojectforbsb.databinding.ActivityMainBinding
import uz.itschool.secondprojectforbsb.databinding.CommentItemBinding
import uz.itschool.secondprojectforbsb.databinding.FoodItemBinding

class CommentAdapter(
    context: Context,
    var comment: MutableList<String>
) :
    ArrayAdapter<String>(context, R.layout.food_item, comment) {

    override fun getCount(): Int {
        return comment.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var binding: CommentItemBinding
        if (convertView == null) {
            binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            binding = CommentItemBinding.bind(convertView)
        }
        val com = comment.get(position)
        binding.name.text = com
        return binding.root
    }
}