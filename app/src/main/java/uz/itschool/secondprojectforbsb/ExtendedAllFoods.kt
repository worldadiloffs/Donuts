package uz.itschool.secondprojectforbsb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.itschool.secondprojectforbsb.databinding.ActivityExtendedAllFoodsBinding

class ExtendedAllFoods : AppCompatActivity() {
    private lateinit var binding: ActivityExtendedAllFoodsBinding
    private var list = mutableListOf<Foods>()
    private var listComment = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExtendedAllFoodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = object : TypeToken<List<String>>() {}.type
        val gson = Gson()
        val getPreferences = getSharedPreferences("comment_list", MODE_PRIVATE)
        val str = getPreferences.getString("comment", "")
        if (str == "") {
        } else {
            listComment = gson.fromJson(str, type)
        }
        val tag = intent.getIntExtra("tag", 1)
        if (tag == 1) {
            loadSalads()
        }
        if (tag == 2) {
            loadDrinks()
        }
        if (tag == 3) {
            loadPastas()
        }

        var adapter = FoodAdapter(this, list)
        binding.main.adapter = adapter

        binding.main.setOnItemClickListener { _, _, i, _ ->
            val food = list.get(i)
            val intent = Intent(this, MoreInfo::class.java)
            intent.putExtra("food", food)
            startActivity(intent)
        }
        binding.search.addTextChangedListener {
            val filter = mutableListOf<Foods>()
            if (it != null) {
                var fav = list
                for (c in fav) {
                    if (c.name.lowercase().contains(it.toString().lowercase())) {
                        filter.add(c)
                    }
                }
                adapter = FoodAdapter(this, filter)
                binding.main.adapter = adapter
            }
        }
    }

    private fun loadSalads() {
        list.add(
            Foods(
                "https://cdn77-s3.lazycatkitchen.com/wp-content/uploads/2017/04/spring-salad-top-down-800x1200.jpg",
                "Spring Salad",
                310,
                510,
                "Taskent, Yunusobod",
                3.6,
                80,
                listComment
            )
        )
        list.add(
            Foods(
                "https://cdn.loveandlemons.com/wp-content/uploads/2019/07/salad.jpg",
                "Greek Salad",
                410,
                610,
                "Taskent, Yunusobod",
                2.0,
                90,
                listComment
            )
        )
        list.add(
            Foods(
                "https://simple-veganista.com/wp-content/uploads/2020/03/best-house-salad-recipe_5.jpg",
                "House Salad",
                420,
                720,
                "Taskent, Yunusobod",
                1.8,
                55,
                listComment
            )
        )
        list.add(
            Foods(
                "https://i0.wp.com/www.onceuponachef.com/images/2019/07/Big-Italian-Salad.jpg?resize=1200%2C1553&ssl=1",
                "Big Italian Salad",
                510,
                820,
                "Taskent, Yunusobod",
                0.3,
                120,
                listComment
            )
        )
        list.add(
            Foods(
                "https://www.cookingclassy.com/wp-content/uploads/2019/11/best-salad-7.jpg",
                "Salad de Maison",
                420,
                610,
                "Taskent, Yunusobod",
                5.6,
                100,
                listComment
            )
        )
        list.add(
            Foods(
                "https://static01.nyt.com/images/2022/07/15/dining/MC-Chopped-Salad-15SALADREX/merlin_209652387_1b5eee4c-9da5-443c-90e0-db20ee51a246-articleLarge.jpg",
                "Chopped Salad",
                310,
                620,
                "Taskent, Yunusobod",
                3.6,
                75,
                listComment
            )
        )
        list.add(
            Foods(
                "https://feelgoodfoodie.net/wp-content/uploads/2018/03/Chicken-Shawarma-Salad-06.jpg",
                "Shawarma Salad",
                720,
                910,
                "Taskent, Yunusobod",
                4.6,
                150,
                listComment
            )
        )
        list.add(
            Foods(
                "https://topteenrecipes.com/wp-content/uploads/2021/11/Untitled-design-1-2.jpg",
                "Lettuce Salad",
                450,
                320,
                "Taskent, Yunusobod",
                3.2,
                99,
                listComment
            )
        )
    }

    private fun loadDrinks() {
        list.add(
            Foods(
                "https://previews.123rf.com/images/baibaz/baibaz1702/baibaz170200011/71184992-glass-of-red-cherry-soda-drink-isolated-on-white-background-from-top-view.jpg",
                "Red Cherry Soda",
                500,
                120,
                "Taskent, Yunusobod",
                3.6,
                60,
                listComment
            )
        )
        list.add(
            Foods(
                "https://media.istockphoto.com/id/854504034/photo/glass-of-lemon-ice-tea.jpg?s=612x612&w=0&k=20&c=H6oyeNf6Axir_ERCqmkS0rqB3eit_r2CwITQyA7wTVE=",
                "Lemon Ice Tea",
                500,
                100,
                "Taskent, Yunusobod",
                3.6,
                70,
                listComment
            )
        )
        list.add(
            Foods(
                "https://previews.123rf.com/images/baibaz/baibaz1702/baibaz170200002/71184993-glass-of-orange-soda-drink-isolated-on-white-background-from-top-view.jpg",
                "Orange Soda",
                500,
                120,
                "Tashkent, Yunusobod",
                1.2,
                100,
                listComment
            )
        )
        list.add(
            Foods(
                "https://thetoastykitchen.com/wp-content/uploads/2018/12/the-grinch-cocktail-toasty-kitchen-3.jpg",
                "Grinch Cocktail",
                500,
                90,
                "Tashkent, Yunusobod",
                2.6,
                120,
                listComment
            )
        )
        list.add(
            Foods(
                "https://media.istockphoto.com/id/1000078962/photo/glass-of-cola-drink.jpg?s=612x612&w=0&k=20&c=vbkDJfq2Frgkrypvwa_UccpDc8wVTkjWoIHgHDrXLWY=",
                "Coke",
                500,
                250,
                "Tashkent, Yunusobod",
                5.6,
                60,
                listComment
            )
        )
        list.add(
            Foods(
                "https://www.stephaniesdish.com/wp-content/uploads/2021/05/GettyImages-1000079034.jpg",
                "Bootleger",
                500,
                150,
                "Tashkent, Yunusobod",
                2.9,
                90,
                listComment
            )
        )
    }

    private fun loadPastas() {
        list.add(
            Foods(
                "https://danielsplate.com/wp-content/uploads/2022/04/simple-tahini-pasta-4-800x800.jpg",
                "Simple Tahini Pasta",
                330,
                485,
                "Taskent, Yunusobod",
                3.6,
                110,
                listComment
            )
        )

        list.add(
            Foods(
                "https://assets.bonappetit.com/photos/61099235416061002aabbb86/3:2/w_1686,h_1124,c_limit/Pantry-0921-Mackerel%20Pantry%20Pasta.jpg",
                "Mackerel Pantry Pasta",
                330,
                495,
                "Taskent, Yunusobod",
                3.6,
                120,
                listComment
            )
        )
        list.add(
            Foods(
                "https://s3.envato.com/files/326943639/AJ6A4393_2.jpg",
                "Penne pasta",
                290,
                510,
                "Tashkent, Yunusobod",
                3.8,
                100,
                listComment
            )
        )
        list.add(
            Foods(
                "https://www.thespruceeats.com/thmb/zxPNPh_-ie2p98gymYoBmjLPH8Y=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Pastasardehoriz-bf435b7f948a45fbafd7780f0e791ad1.jpg",
                "Pasta con le Sarde",
                310,
                510,
                "Tashkent, Yunusobod",
                3.8,
                160,
                listComment
            )
        )
        list.add(
            Foods(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPHC3NHfSiCwBLStglgHTPsvCRx9fTc1hFOw&usqp=CAU",
                "Creamy Vegan",
                320,
                600,
                "Tashkent, Yunusobod",
                4.8,
                150,
                listComment
            )
        )
        list.add(
            Foods(
                "https://thumbs.dreamstime.com/b/tomato-spaghetti-gray-bowl-isolated-white-background-sauce-pasta-classic-italian-cuisine-dish-popular-food-top-view-186210092.jpg",
                "Tomato Spaghetti",
                450,
                710,
                "Tashkent, Yunusobod",
                1.2,
                200,
                listComment
            )
        )
    }
}