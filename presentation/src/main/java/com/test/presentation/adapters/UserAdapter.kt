package com.test.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.presentation.databinding.ItemUserBinding
import com.test.presentation.models.UserItemUI
import com.test.shared.base.BaseAdapter
import com.test.shared.base.BaseDiffUtil
import com.test.shared.extens.clickWithDebounce
import com.test.shared.extens.loadNetworkImage
import javax.inject.Inject

class UserAdapter @Inject constructor() : BaseAdapter<UserItemUI, ItemUserBinding>(
    diffCallback = BaseDiffUtil(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    private var onItemClicked: (String) -> Unit = {}

    fun setOnItemUserClicked(onItemClicked: (String) -> Unit) {
        this.onItemClicked = onItemClicked
    }

    override fun bind(binding: ItemUserBinding, item: UserItemUI, position: Int) =
        with(binding) {
            ivImgUser.loadNetworkImage(item.avatarUrl)
            tvItemNameUser.text = item.username
            tvItemLink.text = item.linkGithub
            cvCardUser.clickWithDebounce {
                onItemClicked.invoke(item.username)
            }
        }

    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ): ItemUserBinding {
        return ItemUserBinding.inflate(inflater, parent, attachToParent)
    }
}