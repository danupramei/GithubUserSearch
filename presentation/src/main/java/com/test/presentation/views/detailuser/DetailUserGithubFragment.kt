package com.test.presentation.views.detailuser

import android.os.Bundle
import android.view.View
import com.test.presentation.databinding.FragmentDetailUserGithubBinding
import com.test.shared.base.BaseFragment
import com.test.shared.extens.clickWithDebounce
import com.test.shared.extens.onBackPressed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserGithubFragment :
    BaseFragment<FragmentDetailUserGithubBinding>(FragmentDetailUserGithubBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setColorStatusBar(com.test.shared.R.color.blue)
        initView()
    }

    private fun initView() = with(binding) {
        ivBtnBack.clickWithDebounce { onBackPressed() }
        // TODO init view
    }
}