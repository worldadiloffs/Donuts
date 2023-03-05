package uz.itschool.secondprojectforbsb

import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.itschool.secondprojectforbsb.databinding.ActivityOrdersBinding

class Orders : AppCompatActivity() {
    private lateinit var binding: ActivityOrdersBinding
    private var listOrder = mutableListOf<Foods>()
    private var oldList = mutableListOf<Foods>()
    private var total = 0
    private lateinit var getPreferences: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
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
        if (intent.getSerializableExtra("food") != null) {
            val foods = intent.getSerializableExtra("food") as Foods
            oldList.add(foods)
        }

        listOrder.addAll(oldList)


        var adapter = FoodAdapter(this, listOrder)
        binding.main.adapter = adapter

        for (i in listOrder) {
            total += i.price
        }
        binding.total.text = total.toString()
        binding.next.isEnabled = false
        if (total > 0) {
            binding.next.isEnabled = true
            binding.next.setOnClickListener {
                val intent = Intent(this, PaymentActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.back.setOnClickListener {
            finish()
        }
        binding.clear.setOnClickListener {
            listOrder.clear()
            total = 0
            binding.total.text = total.toString()
            adapter = FoodAdapter(this, listOrder)
            binding.main.adapter = adapter
            val s = gson.toJson(listOrder)
            edit.putString("order", s).apply()
        }
        val s = gson.toJson(listOrder)
        edit.putString("order", s).apply()
    }
}