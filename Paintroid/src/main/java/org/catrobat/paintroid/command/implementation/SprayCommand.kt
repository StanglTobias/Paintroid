package org.catrobat.paintroid.command.implementation

import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import org.catrobat.paintroid.command.Command
import org.catrobat.paintroid.contract.LayerContracts

class SprayCommand(
        private val sprayedPoints: FloatArray,
        private val paint: Paint
) : Command {

    override fun run(canvas: Canvas?, layerModel: LayerContracts.Model?) {
        canvas?.drawPoints(sprayedPoints, paint)
    }

    override fun freeResources() {
        //nothing to free
    }
}