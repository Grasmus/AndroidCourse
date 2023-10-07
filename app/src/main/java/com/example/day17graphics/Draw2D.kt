package com.example.day17graphics

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class Draw2D(context: Context?) : View(context) {

    private val paint: Paint = Paint()
    private val rect: Rect = Rect()
    val res: Resources = this.resources
    private val bitmap: Bitmap = BitmapFactory.decodeResource(res, R.drawable.cat)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.apply {
            style = Paint.Style.FILL
            color = Color.WHITE
        }
        canvas.drawPaint(paint)

        paint.apply {
            isAntiAlias = true
            color = Color.YELLOW
        }
        canvas.drawCircle(width - 150F, 150F, 300F, paint)

        paint.color = Color.GREEN
        canvas.drawRect(20F, height - 100F, width - 20F, height - 50F, paint)

        paint.apply {
            color = Color.BLUE
            style = Paint.Style.FILL
            isAntiAlias = true
            textSize = 64F
        }
        canvas.drawText("Cats only lawn", 30F, height - 150F, paint)

        paint.apply {
            color = Color.GRAY
            style = Paint.Style.FILL
            textSize = 48F
        }

        val strToRotate = "Sun beam"

        canvas.save()
        canvas.rotate(-45F, height / 4F + rect.exactCenterX(), width / 3F + rect.exactCenterY())
        canvas.drawText(strToRotate, 800F, 650F, paint)

        canvas.restore()

       canvas.drawBitmap(
            bitmap, (width - bitmap.width).toFloat(), (height - bitmap.height
                    - 10).toFloat(), paint
        )
    }
}
