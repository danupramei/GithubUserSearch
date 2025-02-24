package com.test.presentation.views.listuser

import android.os.Bundle
import android.view.View
import com.test.presentation.databinding.FragmentListUserGithubBinding
import com.test.shared.base.BaseFragment
import com.test.shared.extens.dpToPixels
import com.test.shared.extens.verticalLinearLayoutManager
import com.test.shared.utils.CustomMarginDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListUserGithubFragment :
    BaseFragment<FragmentListUserGithubBinding>(FragmentListUserGithubBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setColorStatusBar(com.test.shared.R.color.white)
        // TODO hit api
        initView()
        initAdapter()
        observe()
    }

    private fun initView() = with(binding) {
        swipeListEmployee.isRefreshing = true
        swipeListEmployee.setOnRefreshListener {
            // TODO hit api
        }
    }

    private fun observe() = with(binding) {
        // TODO observe response
    }

    private fun initAdapter() = with(binding.rcListEmployee) {
        // TODO init adapter
        isNestedScrollingEnabled = true
        val verticalLayout = verticalLinearLayoutManager()
        layoutManager = verticalLayout
        addItemDecoration(
            CustomMarginDecoration(
                topPadding = 15.dpToPixels(context).toInt(),
                bottomPadding = 20.dpToPixels(context).toInt()
            )
        )
    }
}