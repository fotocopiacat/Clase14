package com.example.android.clase14

import android.content.Context
import android.hardware.Sensor
import android.hardware.Sensor.TYPE_PROXIMITY
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.getSystemService
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    var sensorManager : SensorManager? = null
    var sensorLuz: Sensor? = null
    var sensorProximity: Sensor? = null



    //la clase main debe extender a SENSOREVENTLISTENER.  Se debe sobreescribir lso metodos sugeridos
    //onaccuracychanged y onsensorchanged.
    //se borran los TODO
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT)
        {
           lblLuz.text=event.values[0].toString()

         }
        if (event?.sensor?.type == Sensor.TYPE_PROXIMITY) {
            lblProximidad.text = event.values[0].toString()
        }
    }




//onresume y onpause se sobrescriben para uno determinar q pasa con la app al resumrise y/o pausarse
    override fun onResume() {
        sensorManager?.registerListener(this, sensorLuz,SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager?.registerListener(this, sensorProximity,SensorManager.SENSOR_DELAY_NORMAL)
    super.onResume()
    }

    override fun onPause() {
        sensorManager?.unregisterListener (this)
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //listando todos los sensores
        /* val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensores = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (i in 0 .. sensores.size-1) {
            lblLuz.append(sensores.get(i).name + "\n")*/

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorManager!!.getSensorList(Sensor.TYPE_LIGHT) != null) {
            sensorLuz = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)

        } else
            Toast.makeText(this, "sensor de luz no disponible", Toast.LENGTH_SHORT).show()

        if (sensorManager!!.getSensorList(Sensor.TYPE_PROXIMITY) != null) {
            sensorProximity = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        } else
            Toast.makeText(this, "sensor de proximidad no disponible", Toast.LENGTH_SHORT).show()

        }
    }