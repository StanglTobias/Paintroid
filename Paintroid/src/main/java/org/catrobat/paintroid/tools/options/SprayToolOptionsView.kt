package org.catrobat.paintroid.tools.options

interface SprayToolOptionsView {

    fun setCallback(callback: Callback?)

    fun setRadius(radius: Int)

    interface Callback {

        fun radiusChanged(radius: Int)
    }
}