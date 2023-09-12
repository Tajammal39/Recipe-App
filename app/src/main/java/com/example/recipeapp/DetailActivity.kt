package com.example.recipeapp

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.util.Util
import com.example.recipeapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    var imgCrop = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
        binding.tittle2.text = intent.getStringExtra("title")
//        binding.ingData.text = intent.getStringExtra("ing")
        binding.stepData.text = intent.getStringExtra("des")
        var ing =
            intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }
                ?.toTypedArray()

        binding.time.text = ing?.get(0)

        for (i in 1 until ing!!.size) {
            binding.ingData.text = """    ${binding.ingData.text} 🟢 ${ing[i]}
                
               """.trimIndent()
        }
        binding.Steps.background = null
        binding.Steps.setTextColor(getColor(R.color.black))
        binding.Steps.setOnClickListener {
            binding.Steps.setBackgroundResource(R.drawable.btn_ing)
            binding.Ingredients.background = null
            binding.Steps.setTextColor(getColor(R.color.white))
            binding.Ingredients.setTextColor(getColor(R.color.black))
            binding.ingScrollView.visibility = View.GONE
            binding.stepScrollView.visibility = View.VISIBLE
        }


        binding.Ingredients.setOnClickListener {
            binding.Ingredients.setBackgroundResource(R.drawable.btn_ing)
            binding.stepData.background = null
            binding.Ingredients.setTextColor(getColor(R.color.white))
            binding.Steps.setTextColor(getColor(R.color.black))
            binding.ingScrollView.visibility = View.VISIBLE
            binding.stepScrollView.visibility = View.GONE
        }

        binding.fullScreen.setOnClickListener {
            if (imgCrop) {
                binding.itemImage.scaleType = ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScreen.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                binding.shade.visibility = View.GONE
                imgCrop = false
            } else {
                binding.itemImage.scaleType = ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScreen.setColorFilter(null)
                binding.shade.visibility = View.GONE
                imgCrop = true
            }
        }

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}