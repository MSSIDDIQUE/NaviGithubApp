package com.baymax.demoapp.ui.activity.main_activity.ui

import androidx.lifecycle.ViewModel
import com.baymax.demoapp.ui.activity.main_activity.data.Repository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {
    suspend fun fetchPrs(owner: String, repo_name: String) = repo.fetchPrs(owner, repo_name)
}