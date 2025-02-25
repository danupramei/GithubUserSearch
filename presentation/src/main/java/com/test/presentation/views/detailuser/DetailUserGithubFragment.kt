package com.test.presentation.views.detailuser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.test.presentation.databinding.FragmentDetailUserGithubBinding
import com.test.presentation.models.UserUI
import com.test.presentation.utils.launchAndCollectIn
import com.test.presentation.utils.onError
import com.test.presentation.utils.onLoading
import com.test.presentation.utils.onSuccess
import com.test.presentation.views.constanta.Const
import com.test.shared.base.BaseFragment
import com.test.shared.customview.LoadingDialog
import com.test.shared.extens.clickWithDebounce
import com.test.shared.extens.getDataFromPreviousFragment
import com.test.shared.extens.loadNetworkImage
import com.test.shared.extens.onBackPressed
import com.test.shared.extens.showToastMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserGithubFragment :
    BaseFragment<FragmentDetailUserGithubBinding>(FragmentDetailUserGithubBinding::inflate) {
    private val username: String by lazy {
        getDataFromPreviousFragment<String>(Const.ARG_USERNAME).orEmpty()
    }

    private val viewModel by viewModels<DetailUserGithubViewModel>()
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog.getInstance(context) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setColorStatusBar(com.test.shared.R.color.blue)
        initView()
        observe()
    }

    private fun observe() {
        viewModel.detailUser.launchAndCollectIn(
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        ) { state ->
            state.onSuccess { data ->
                data?.let {
                    setView(it)
                }
                loadingDialog.dismiss()
            }.onError {
                showToastMessage(it.orEmpty())
                loadingDialog.dismiss()
            }.onLoading {
                loadingDialog.show()
            }
        }
    }

    private fun initView() = with(binding) {
        viewModel.getDetailUser(username)
        ivBtnBack.clickWithDebounce { onBackPressed() }
    }

    private fun setView(data: UserUI) = with(binding) {
        ivImgDetailUser.loadNetworkImage(data.avatarUrl)
        tvDetailName.text = data.name
        tvDetailUsername.text = data.login
        tvDetailBio.text = data.bio
        tvFollowers.text = data.followers
        tvFollowing.text = data.following
        tvRepository.text = data.publicRepos
    }
}