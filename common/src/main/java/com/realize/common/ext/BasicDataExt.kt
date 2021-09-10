package com.realize.common.ext

import android.content.res.Resources
import com.realize.common.CommonApplication
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by SongWenjun
 * 2021/7/10
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...基本数据类型扩展
 */

val Float.dp: Float
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )

val Double.format2: Float
    get() {
        val df = DecimalFormat("0.00")
        df.setRoundingMode(RoundingMode.HALF_UP)
        return df.format(this).toFloat()
    }

val Int.dp: Int
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

val Int.toDp: Int
    get() {
        val scale = CommonApplication.context.resources.displayMetrics.density ?: 1F
        return (this.toFloat() / scale + 0.5f).toInt()
    }

val Float.sp: Float
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics
    )

val Int.sp: Int
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()