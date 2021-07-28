package com.example.tatvasoft_task

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tatvasoft_task.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel3
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var itemsAdapter: ItemsAdapter
    var listItems = ArrayList<NumberModel>()
    var count: String = ""
    var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = MainViewModel3()
        activityMainBinding.viewmodel = mainViewModel
        activityMainBinding.lifecycleOwner
        setAdapter(1)
        clickListener()
    }

    private fun clickListener() {
        activityMainBinding.btSubmit.setOnClickListener {
            count = activityMainBinding.edNumber.toString()
            if (!count.isEmpty()) {
                number = Integer.parseInt(count);
                var itemCount = Math.sqrt(number.toDouble());
                listItems = ArrayList()
                for (i in 0..number) {
                    var numberModel: NumberModel = NumberModel()
                    listItems.add(numberModel)
                }
                setAdapter(itemCount.roundToInt())
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            for (i in 0..listItems.size) {
                var numberModel: NumberModel = NumberModel()
                numberModel.text = "0"
                listItems.set(i, numberModel)
            }
            setAdapter(number)
        }, 200)
    }

    private fun setAdapter(gridCount: Int) {
        itemsAdapter = ItemsAdapter(this, listItems)
        linearLayoutManager = GridLayoutManager(this, gridCount)
        activityMainBinding.rvItems.layoutManager = linearLayoutManager
        activityMainBinding.rvItems.adapter = itemsAdapter
        itemsAdapter.notifyDataSetChanged()
    }
}