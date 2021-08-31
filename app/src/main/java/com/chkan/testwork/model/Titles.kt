package com.chkan.testwork.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class Titles (
    val id: Int = 0,
    val title: String = ""
) : Parcelable