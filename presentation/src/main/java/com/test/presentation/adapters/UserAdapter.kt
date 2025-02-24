package com.test.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.presentation.databinding.ItemUserBinding
import com.test.presentation.models.UserUI
import com.test.shared.base.BaseAdapter
import com.test.shared.base.BaseDiffUtil
import javax.inject.Inject

class UserAdapter @Inject constructor() : BaseAdapter<UserUI, ItemUserBinding>(
    diffCallback = BaseDiffUtil(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    private var onItemClicked: (UserUI) -> Unit = {}

    fun setOnItemEmployeeClicked(onItemClicked: (UserUI) -> Unit) {
        this.onItemClicked = onItemClicked
    }

    override fun bind(binding: ItemUserBinding, item: UserUI, position: Int) =
        with(binding) {
            // TODO init view
        }

    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ): ItemUserBinding {
        return ItemUserBinding.inflate(inflater, parent, attachToParent)
    }
}