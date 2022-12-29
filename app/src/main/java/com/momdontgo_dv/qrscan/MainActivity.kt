package com.momdontgo_dv.qrscan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager.OnActivityResultListener
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.momdontgo_dv.qrscan.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnScan.setOnClickListener{initScan()}
    }

    private fun initScan() {
        val integra = IntentIntegrator(this)
        integra.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integra.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (result != null){
            if (result.contents == null){
                val toasttext = resources.getString(R.string.toast)
                Toast.makeText(this, "$toasttext", Toast.LENGTH_SHORT).show()
            }else{

                binding.tvResult.setText(result.contents.toString())
            }
        }

        super.onActivityResult(requestCode, resultCode, data)

    }
}