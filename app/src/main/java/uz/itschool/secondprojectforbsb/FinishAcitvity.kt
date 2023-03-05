package uz.itschool.secondprojectforbsb

import android.content.SharedPreferences
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.itschool.secondprojectforbsb.databinding.ActivityFinishAcitvityBinding

class FinishAcitvity : AppCompatActivity() {
    private lateinit var binding: ActivityFinishAcitvityBinding
    private lateinit var getPreferences: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor
    private var listOrder = mutableListOf<Foods>()
    private var total = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishAcitvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = object : TypeToken<List<Foods>>() {}.type
        val gson = Gson()

        getPreferences = getSharedPreferences("ordered", MODE_PRIVATE)
        edit = getPreferences.edit()
        val str = getPreferences.getString("order", "")

        if (str == "") {
            listOrder = mutableListOf()
        } else {
            listOrder = gson.fromJson(str, type)
        }

        for (i in listOrder) {
            total += i.price
        }
        binding.totalSum.text = "$$total"
        binding.back.setOnClickListener {
            finish()
        }

        listOrder.clear()
        total = 0
        val s = gson.toJson(listOrder)
        edit.putString("order", s).apply()
    }
}