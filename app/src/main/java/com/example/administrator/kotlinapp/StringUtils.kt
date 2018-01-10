package com.example.administrator.kotlinapp

/**
 * Created by Administrator on 2017/12/15.
 */
class StringUtils {
    //伴生对象，由于kotlin没有静态方法，就出现了伴生对象。这样我们就可以在其他地方调用了（ps:StringUtils.isEmpty("qly")）
    companion object {
        fun isEmpty(str: String): Boolean {
            return "" == str
        }
    }
}

