package com.kecc.domain.models

import com.kecc.domain.interfaces.DisplayableItemItf

data class CourseModel(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
): DisplayableItemItf
