package com.test.presentation.enums

import androidx.fragment.app.Fragment
import com.test.presentation.R

enum class RouteEnum(private val routeResId: Int) {
    DETAIL_USER(R.string.detail_user_route);

    fun getRouteName(fragment: Fragment): String = fragment.getString(routeResId)
}