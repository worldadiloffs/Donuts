package uz.itschool.secondprojectforbsb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.itschool.secondprojectforbsb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var types: MutableMap<String, MutableList<Category>>
    private var listRec = mutableListOf<Foods>()
    private var comments = mutableMapOf<String, MutableList<String>>()
    private val type = "type"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val type1 = object : TypeToken<MutableMap<String, MutableList<String>>>() {}.type
        val gson = Gson()
        val getPreferences = getSharedPreferences("comment_list", MODE_PRIVATE)
        val str = getPreferences.getString("comment", "")
        if (str == "") {
        } else {
            comments = gson.fromJson(str, type1)
        }
        loadRec()
        loadData()
        val adapter = TypeFoodAdapter(this, types, type)
        binding.expandable.setAdapter(adapter)
        binding.expandable.setOnChildClickListener { _, _, _, i2, _ ->
            val category = types.get(type)!!.get(i2)
            if (category.tag == 1) {
                val intent = Intent(this, ExtendedAllFoods::class.java)
                intent.putExtra("tag", 1)
                startActivity(intent)
            }
            if (category.tag == 2) {
                val intent = Intent(this, ExtendedAllFoods::class.java)
                intent.putExtra("tag", 2)
                startActivity(intent)
            }
            if (category.tag == 3) {
                val intent = Intent(this, ExtendedAllFoods::class.java)
                intent.putExtra("tag", 3)
                startActivity(intent)
            }
            true
        }
        var adapter2 = FoodAdapter(this, listRec)
        binding.main.adapter = adapter2

        binding.main.setOnItemClickListener { _, _, i, _ ->
            val food = listRec.get(i)
            val intent = Intent(this, MoreInfo::class.java)
            intent.putExtra("food", food)
            startActivity(intent)
            finish()
        }
        binding.search.addTextChangedListener {
            val filter = mutableListOf<Foods>()
            if (it != null) {
                var fav = listRec
                for (c in fav) {
                    if (c.name.lowercase().contains(it.toString().lowercase())) {
                        filter.add(c)
                    }
                }
                adapter2 = FoodAdapter(this, filter)
                binding.main.adapter = adapter2
            }
        }

        binding.cart.setOnClickListener {
            val intent = Intent(this, Orders::class.java)
            startActivity(intent)
        }

    }

    private fun loadData() {
        types = mutableMapOf()

        val choose = mutableListOf<Category>()
        choose.add(Category(R.drawable.salads, 1, "Salads"))
        choose.add(Category(R.drawable.drinks, 2, "Drinks"))
        choose.add(Category(R.drawable.pasta, 3, "Pastas"))

        types.put(type, choose)
    }

    private fun loadRec() {
        listRec.add(
            Foods(
                "https://danielsplate.com/wp-content/uploads/2022/04/simple-tahini-pasta-4-800x800.jpg",
                "Simple Tahini Pasta",
                330,
                485,
                "Taskent, Yunusobod",
                3.6,
                110,
                comments.get("Simple Tahini Pasta")
            )
        )
        listRec.add(
            Foods(
                "https://cdn77-s3.lazycatkitchen.com/wp-content/uploads/2017/04/spring-salad-top-down-800x1200.jpg",
                "Spring Salad",
                310,
                510,
                "Taskent, Yunusobod",
                3.6,
                80,
                comments.get("Spring Salad")
            )
        )
        listRec.add(
            Foods(
                "https://previews.123rf.com/images/baibaz/baibaz1702/baibaz170200011/71184992-glass-of-red-cherry-soda-drink-isolated-on-white-background-from-top-view.jpg",
                "Red Cherry Soda",
                500,
                120,
                "Taskent, Yunusobod",
                3.6,
                60,
                comments.get("Red Cherry Soda")
            )
        )
        listRec.add(
            Foods(
                "https://media.istockphoto.com/id/854504034/photo/glass-of-lemon-ice-tea.jpg?s=612x612&w=0&k=20&c=H6oyeNf6Axir_ERCqmkS0rqB3eit_r2CwITQyA7wTVE=",
                "Lemon Ice Tea",
                500,
                100,
                "Taskent, Yunusobod",
                3.6,
                70,
                comments.get("Lemon Ice Tea")
            )
        )
        listRec.add(
            Foods(
                "https://assets.bonappetit.com/photos/61099235416061002aabbb86/3:2/w_1686,h_1124,c_limit/Pantry-0921-Mackerel%20Pantry%20Pasta.jpg",
                "Mackerel Pantry Pasta",
                330,
                495,
                "Taskent, Yunusobod",
                3.6,
                120,
                comments.get("Mackerel Pantry Pasta")
            )
        )
    }
}