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
import uz.itschool.secondprojectforbsb.databinding.FoodItemBinding

class FoodAdapter(
    context: Context,
    var foods: MutableList<Foods>
) :
    ArrayAdapter<Foods>(context, R.layout.food_item, foods) {

    override fun getCount(): Int {
        return foods.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var binding: FoodItemBinding
        if (convertView == null) {
            binding = FoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            binding = FoodItemBinding.bind(convertView)
        }
        val food = foods.get(position)
        binding.img.load(food.img) {
            placeholder(R.drawable.ic_launcher_background)
            error(androidx.appcompat.R.drawable.abc_btn_radio_material_anim)
        }

        binding.name.text = food.name
        binding.price.text = food.price.toString()
        return binding.root
    }
}