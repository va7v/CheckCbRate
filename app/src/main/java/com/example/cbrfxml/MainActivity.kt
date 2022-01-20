package com.example.cbrfxml

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.example.cbrfxml.databinding.ActivityMainBinding
import com.example.cbrfxml.UsdAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val usdViewModel by viewModels<UsdViewModel>()
    private val itemAdapter = UsdAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        // Список прогнозных значений
        usdViewModel.items.observe(this, Observer {
            itemAdapter.addItems(it)
        })

        // Задание даты для получения курса ЦБ и сравнения с прогнозом
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            usdViewModel.compareCbrUsd(year, month, dayOfMonth)
            Toast.makeText(this@MainActivity, "Сравнение с курсом ЦБ...", Toast.LENGTH_SHORT).show()
        }

        // Результат сравнения прогноза с курсом ЦБ
        usdViewModel.reqRes.observe(this, Observer {
            Toast.makeText(this@MainActivity, it , Toast.LENGTH_LONG).show()
        })
    }
}