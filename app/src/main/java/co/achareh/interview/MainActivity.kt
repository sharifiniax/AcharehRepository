package co.achareh.interview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import co.achareh.interview.databinding.ActivityMainBinding
import co.achareh.interview.ui.fillform.FormViewModel
import dagger.hilt.android.AndroidEntryPoint



class MainActivity : AppCompatActivity() {

    lateinit var viewModel:FormViewModel
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel=ViewModelProvider(this).get(FormViewModel::class.java)
    }
}