package com.example.android.clase14

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensores = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (i in 0 .. sensores.size-1) {
            lblSensor.append(sensores.get(i).name + "\n")
        }
    }
}
