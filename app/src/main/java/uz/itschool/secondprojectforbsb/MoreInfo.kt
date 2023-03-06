package uz.itschool.secondprojectforbsb

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.itschool.secondprojectforbsb.databinding.ActivityMoreInfoBinding

class MoreInfo : AppCompatActivity() {
    private lateinit var binding: ActivityMoreInfoBinding
    private var listComment = mutableListOf<String>()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = object : TypeToken<List<String>>() {}.type
        val gson = Gson()

        sharedPreferences = getSharedPreferences("comment_list", MODE_PRIVATE)
        edit = sharedPreferences.edit()

        val str = sharedPreferences.getString("comment", "")

        if (str == "") {
            listComment = mutableListOf()
        } else {
            listComment = gson.fromJson(str, type)
        }

        val adapter = CommentAdapter(this, listComment)
        binding.main.adapter = adapter

        val foods = intent.getSerializableExtra("food") as Foods
        binding.img.load(foods.img) {
            placeholder(R.drawable.ic_launcher_background)
            error(androidx.appcompat.R.drawable.abc_btn_radio_material_anim)
        }
        binding.name.text = foods.name
        binding.grams.text = foods.weight.toString()
        binding.calories.text = foods.calories.toString()
        binding.location.text = foods.location
        binding.diraction.text = foods.direction.toString()
        binding.price.text = foods.price.toString()

        binding.addToCart.setOnClickListener {
            val intent = Intent(this, Orders::class.java)
            intent.putExtra("food", foods)
            startActivity(intent)
        }
        binding.back.setOnClickListener {
            finish()
        }
        binding.publish.setOnClickListener {
            if (binding.coment.text.isNotEmpty()) {
                listComment.add(binding.coment.text.toString())

                val adapter = CommentAdapter(this, listComment)
                binding.main.adapter = adapter

                val s = gson.toJson(listComment)
                edit.putString("comment", s).apply()

            }
            binding.coment.text.clear()
        }

    }
}