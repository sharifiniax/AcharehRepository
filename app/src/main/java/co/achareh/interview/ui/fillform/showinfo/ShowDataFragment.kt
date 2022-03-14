package co.achareh.interview.ui.fillform.showinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.achareh.interview.R
import co.achareh.interview.databinding.FragmentShowDataBinding
import co.achareh.interview.ui.fillform.FormViewModel
import co.achareh.interview.util.ChangeFragmentState
import co.achareh.interview.util.RequestState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect


class ShowDataFragment : Fragment() {
    lateinit var binding:FragmentShowDataBinding
    val viewModel:FormViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentShowDataBinding.inflate(
            inflater,container,false
        )
        binding.viewModel=viewModel
        lifecycleScope.launchWhenStarted {
            viewModel.rout.collect {

                when(it){

                    ChangeFragmentState.Empty -> {

                    }
                    ChangeFragmentState.Go -> {
                        findNavController().navigate(R.id.action_showDataFragment_to_informationFragment)
                        viewModel.rout.value=ChangeFragmentState.Empty
                    }



                }



            }





        }

        binding.recycler.layoutManager=LinearLayoutManager(context)
        val adapter=OrderAdapter()
        binding.recycler.adapter=adapter
        viewModel.listOrders.observe(viewLifecycleOwner){
            adapter.submitList(it)

        }
        lifecycleScope.launchWhenStarted {
            viewModel.listState.collect {

                when (it) {
                    RequestState.Empty -> {

                    }
                    RequestState.Loading -> {
                        binding.floatingActionButton.isEnabled = false
                        binding.progressBar2.visibility=View.VISIBLE
                        binding.textView5.visibility=View.VISIBLE
                        binding.progressBar2.isClickable=false

                    }
                    RequestState.Error -> {
                        Snackbar.make(
                            binding.root,
                            "خطای اینترنتی رخ داده",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        binding.floatingActionButton.isEnabled = true
                        binding.progressBar2.visibility=View.INVISIBLE
                        binding.textView5.visibility=View.INVISIBLE
                        binding.progressBar2.isClickable=false
                    }
                    RequestState.Success -> {
                        binding.floatingActionButton.isEnabled = true
                        binding.progressBar2.visibility=View.INVISIBLE
                        binding.textView5.visibility=View.INVISIBLE
                        binding.progressBar2.isClickable=false
                    }


                }





            }
        }

        return binding.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getList()
        super.onViewCreated(view, savedInstanceState)
    }

}