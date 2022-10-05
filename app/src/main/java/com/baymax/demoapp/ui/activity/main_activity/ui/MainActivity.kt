package com.baymax.demoapp.ui.activity.main_activity.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.baymax.demoapp.R
import com.baymax.demoapp.data.Result
import com.baymax.demoapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var prListAdapter: PrListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
        binding.apply {
            search.setOnClickListener {
                val owner = ownerNameText.text.toString().trim()
                val repo = repoNameText.text.toString().trim()
                loadPullRequest(owner,repo)
            }
        }
        loadPullRequest("jspruance", "musician-app")
    }

    private fun loadPullRequest(owner:String,repo:String){
        binding.progress.visibility = View.VISIBLE
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                viewModel.fetchPrs(owner, repo)
            }

            when(result){
                is Result.Failure -> {
                    Snackbar.make(
                        binding.root,
                        "Something went wrong!, please check user name and repository name",
                        Snackbar.LENGTH_LONG
                    ).show()
                    binding.progress.visibility = View.GONE
                }
                is Result.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    result.data?.let { list->
                        if(list.size==0){
                            Snackbar.make(
                                binding.root,
                                "No pull request available for the repo!",
                                Snackbar.LENGTH_LONG
                            ).show()
                            binding.headerTitle.visibility = View.GONE
                        }
                        prListAdapter = PrListAdapter(list)
                        linearLayoutManager = LinearLayoutManager(this@MainActivity)
                        binding.apply {
                            recyclerView.apply {
                                layoutManager = linearLayoutManager
                                adapter = prListAdapter
                            }
                            progress.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }
}