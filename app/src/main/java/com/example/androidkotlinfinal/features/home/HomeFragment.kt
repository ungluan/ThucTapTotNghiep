package com.example.androidkotlinfinal.features.home

import android.Manifest
import android.content.Context.SENSOR_SERVICE
import android.content.pm.PackageManager
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidkotlinfinal.databinding.FragmentHomeBinding
import com.example.androidkotlinfinal.navigation.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

/***
- Tránh dùng constraint layout vs item của recycler view
- Bởi vì: ConstraintLayout sẽ phải tính toán lại rất nhiều dựa trên các Constraint -> Ngốn CPU
 ***/
private const val ACTIVITY_RECOGNITION_REQUEST_CODE = 10

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var navigator: AppNavigator

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = context!!.getSystemService(SENSOR_SERVICE) as SensorManager
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        binding.viewModel = this.viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = AccelerationListAdapter()
        binding.recyclerViewRawData.adapter = adapter


        return binding.root
    }

    private fun checkPermission(){
        if (context!!.checkSelfPermission(Manifest.permission.ACTIVITY_RECOGNITION)
            != PackageManager.PERMISSION_GRANTED) {
            Timber.d("User is granted ACTIVITY_RECOGNITION")
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                    ACTIVITY_RECOGNITION_REQUEST_CODE
                )
            }
        }
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