package com.hhvvg.anytext.wrapper

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.hhvvg.anytext.ui.TextEditingDialog

const val IGNORE_HOOK = "IGNORE_HOOK"

class TextViewOnClickWrapper(
    private val originClickListener: View.OnClickListener?,
    private val textView: TextView
) : View.OnTouchListener {

    private val gestureDetector = GestureDetector(textView.context, object : GestureDetector.SimpleOnGestureListener() {

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            // 双击时打开修改弹窗
            TextEditingDialog(textView.context, textView, originClickListener).show()
            return true
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            // 单击时不拦截，继续走原来的点击逻辑
            return false
        }
    })

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        // 返回 false 表示事件继续向下分发，原点击事件保留
        return false
    }
}