package com.test.presentation.views.listuser

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.test.presentation.adapters.UserAdapter
import com.test.presentation.databinding.FragmentListUserGithubBinding
import com.test.presentation.enums.RouteEnum
import com.test.presentation.utils.launchAndCollectIn
import com.test.presentation.utils.onEmpty
import com.test.presentation.utils.onError
import com.test.presentation.utils.onSuccess
import com.test.presentation.views.constanta.Const
import com.test.shared.base.BaseFragment
import com.test.shared.extens.dpToPixels
import com.test.shared.extens.gone
import com.test.shared.extens.navigate
import com.test.shared.extens.showToastMessage
import com.test.shared.extens.verticalLinearLayoutManager
import com.test.shared.extens.visible
import com.test.shared.utils.CustomMarginDecoration
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListUserGithubFragment :
    BaseFragment<FragmentListUserGithubBinding>(FragmentListUserGithubBinding::inflate) {
    @Inject
    lateinit var adapterUser: UserAdapter

    private val viewModel by viewModels<ListUserGithubViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setColorStatusBar(com.test.shared.R.color.white)
        getUser()
        initView()
        initAdapter()
        observe()
    }

    private fun initView() = with(binding) {
        swipeListUser.isRefreshing = true
        swipeListUser.setOnRefreshListener {
            getUser()
        }
        etSearch.doAfterTextChanged {
            it?.let { text ->
                if (text.isNotBlank()) {
                    viewModel.search(etSearch.text.toString())
                } else {
                    // TODO
                }
            }
        }
    }

    private fun observe() = with(binding) {
        viewModel.searchWork.launchAndCollectIn(
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        ) { state ->
            state.onSuccess { list ->
                rcListUser.visible()
                adapterUser.apply {
                    submitList(list.orEmpty())
                    setOnItemEmployeeClicked { username ->
                        navigateToDetail(username)
                    }
                }
                swipeListUser.isRefreshing = false
            }.onError {
                showToastMessage(it.orEmpty())
                rcListUser.gone()
                swipeListUser.isRefreshing = false
            }.onEmpty {
                rcListUser.gone()
                swipeListUser.isRefreshing = false
            }
        }
    }

    private fun initAdapter() = with(binding.rcListUser) {
        adapter = adapterUser
        isNestedScrollingEnabled = true
        val verticalLayout = verticalLinearLayoutManager()
        layoutManager = verticalLayout
        addItemDecoration(
            CustomMarginDecoration(
                firstTopPadding = 16.dpToPixels(context).toInt(),
                lastBottomPadding = 20.dpToPixels(context).toInt(),
                horizontalPadding = 16.dpToPixels(context).toInt(),
                topPadding = 8.dpToPixels(context).toInt()
            )
        )
    }

    private fun getUser() = with(binding){
        if (etSearch.text.isNotBlank()) {
            viewModel.search(etSearch.text.toString())
        } else {
            viewModel
        }
    }

    private fun navigateToDetail(username: String) {
        navigate(
            route = RouteEnum.DETAIL_USER.getRouteName(this),
            extras = mapOf(Const.ARG_USERNAME to username)
        )
    }
}