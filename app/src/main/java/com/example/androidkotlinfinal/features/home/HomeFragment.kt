package com.example.androidkotlinfinal.features.home

import android.Manifest
import android.content.Context.SENSOR_SERVICE
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.androidkotlinfinal.database.entities.AccelerationData
import com.example.androidkotlinfinal.databinding.FragmentHomeBinding
import com.example.androidkotlinfinal.navigation.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.sqrt

/***
- Tránh dùng constraint layout vs item của recycler view
- Bởi vì: ConstraintLayout sẽ phải tính toán lại rất nhiều dựa trên các Constraint -> Ngốn CPU
 ***/
private const val ACTIVITY_RECOGNITION_REQUEST_CODE = 10

@AndroidEntryPoint
class HomeFragment : Fragment(), SensorEventListener {

    @Inject
    lateinit var navigator: AppNavigator

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: AccelerationListAdapter
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometerSensor: Sensor
    private var preThreshold: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = context!!.getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        checkPermission()
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        adapter = AccelerationListAdapter()
//        binding.recyclerViewRawData.adapter = adapter
//
//        viewModel.accelerations.observe(viewLifecycleOwner, Observer {
//            adapter.submitList(it)
//        })
//        binding.register.setOnClickListener {
//            sensorManager.registerListener(
//                this,
//                accelerometerSensor,
//                SensorManager.SENSOR_DELAY_NORMAL
//            )
//        }
//        binding.unregister.setOnClickListener {
//            sensorManager.unregisterListener(this)
//        }
//
//        binding.delete.setOnClickListener{
//            viewModel.deleteAllAcceleration()
//        }

        return binding.root
    }
    private fun handleRawData(x: Float, y: Float, z: Float){
        val threshold = sqrt(x*x + y*y + z*z)
        val denta = threshold - preThreshold
        preThreshold = threshold
        if(denta > 10){
            // Running
        }else if(denta > 6){
            // Walking
        }else {
            // denta lần 1 lớn hơn 20
            // trong
        }
    }
    private fun checkPermission() {
        if (context!!.checkSelfPermission(Manifest.permission.ACTIVITY_RECOGNITION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Timber.d("User is granted ACTIVITY_RECOGNITION")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                    ACTIVITY_RECOGNITION_REQUEST_CODE
                )
            }
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        Timber.d("Sensor change")
        val x: Float = (p0!!.values[0])
        val y: Float = (p0.values[1])
        val z: Float = (p0.values[2])
        Timber.d(
            "\n" +
                    "x: " + x + "\n" +
                    "y: " + y + "\n" +
                    "z: " + z + "\n" +
                    "Threshold: " + sqrt(x*x+y*y+z*z) + "\n"
        )
        viewModel.insertAccelerationData(
            AccelerationData(x, y, z)
        )
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        println("")
    }


//    private fun saveData() {
//        val sharedPreferences: SharedPreferences =
//            context!!.getSharedPreferences("store", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putFloat("STEP_COUNTER", previousTotalSteps)
//        editor.apply()
//    }
//
//    private fun loadData() {
//        val sharedPreferences: SharedPreferences =
//            context!!.getSharedPreferences("store", Context.MODE_PRIVATE)
//        val savedNumber = sharedPreferences.getFloat("STEP_COUNTER", 0f)
//        Log.d("HOME_PAGE", savedNumber.toString());
//    }

}