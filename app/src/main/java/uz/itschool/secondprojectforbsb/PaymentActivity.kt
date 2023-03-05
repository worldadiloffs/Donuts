package uz.itschool.secondprojectforbsb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.itschool.secondprojectforbsb.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            finish()
        }
        if (binding.cardNum.text != null && binding.cardPass.text != null) {
            binding.next.isEnabled = true
        }
        binding.next.setOnClickListener {
            val intent = Intent(this, FinishAcitvity::class.java)
            startActivity(intent)
            finish()
        }
    }
}