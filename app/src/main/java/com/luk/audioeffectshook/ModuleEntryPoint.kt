package com.luk.audioeffectshook

import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage


class ModuleEntryPoint : IXposedHookLoadPackage {
    private fun ByteArray.toHexArray(): String =
        joinToString(", ") { eachByte -> "0x%02x".format(eachByte) }

    private fun ShortArray.toHexArray(): String =
        joinToString(", ") { eachByte -> "0x%02x".format(eachByte) }

    fun logParams(msg: String, param: XC_MethodHook.MethodHookParam) {
        Log.e(TAG, msg)

        param.args.forEachIndexed { k, v ->
            when (v) {
                is ByteArray -> {
                    Log.e(TAG, "    key=$k, v=[${v.toHexArray()}]")
                }
                is ShortArray -> {
                    Log.e(TAG, "    key=$k, v=[${v.toHexArray()}]")
                }
                else -> {
                    Log.e(TAG, "    key=$k, v=$v")
                }
            }
        }
    }

    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        XposedHelpers.findAndHookMethod(
            "android.media.audiofx.AudioEffect",
            lpparam!!.classLoader,
            "setParameter",
            ByteArray::class.java,
            ByteArray::class.java,
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    logParams("setParameter(byte[] param, byte[] value)", param)
                }
            })

        XposedHelpers.findAndHookMethod(
            "android.media.audiofx.AudioEffect",
            lpparam.classLoader,
            "setParameter",
            Int::class.java,
            Int::class.java,
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    logParams("setParameter(int param, int value)", param)
                }
            })

        XposedHelpers.findAndHookMethod(
            "android.media.audiofx.AudioEffect",
            lpparam.classLoader,
            "setParameter",
            Int::class.java,
            Short::class.java,
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    logParams("setParameter(int param, short value)", param)
                }
            })

        XposedHelpers.findAndHookMethod(
            "android.media.audiofx.AudioEffect",
            lpparam.classLoader,
            "setParameter",
            Int::class.java,
            ByteArray::class.java,
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    logParams("setParameter(int param, byte[] value)", param)
                }
            })

        XposedHelpers.findAndHookMethod(
            "android.media.audiofx.AudioEffect",
            lpparam.classLoader,
            "setParameter",
            IntArray::class.java,
            IntArray::class.java,
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    logParams("setParameter(int[] param, int[] value)", param)
                }
            })

        XposedHelpers.findAndHookMethod(
            "android.media.audiofx.AudioEffect",
            lpparam.classLoader,
            "setParameter",
            IntArray::class.java,
            ShortArray::class.java,
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    logParams("setParameter(int[] param, short[] value)", param)
                }
            })

        XposedHelpers.findAndHookMethod(
            "android.media.audiofx.AudioEffect",
            lpparam.classLoader,
            "setParameter",
            IntArray::class.java,
            ByteArray::class.java,
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    logParams("setParameter(int[] param, byte[] value)", param)
                }
            })
    }

    companion object {
        const val TAG = "AudioEffectsHook"
    }
}
