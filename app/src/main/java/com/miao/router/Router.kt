package com.miao.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import java.util.*

@Target(
    AnnotationTarget.TYPE,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Route(val path: String)

object Router {
    private val routeMap: MutableMap<String, Class<out Activity?>> = HashMap()

    fun init(context: Context) {
        try {
            val packageName = context.packageName
            val packageInfo =
                context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            val activities = packageInfo.activities
            for (activityInfo in activities) {
                val className = activityInfo.name
                val clazz = Class.forName(className)
                addRoute(clazz)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun addRoute(activityClass: Class<*>) {
        val routeAnnotation = activityClass.getAnnotation(Route::class.java)
        if (routeAnnotation != null) {
            val path = routeAnnotation.path
            val convertedClass = activityClass as? Class<out Activity?>
            if (convertedClass != null) {
                routeMap[path] = convertedClass
            }
        }
    }

    fun navigate(context: Context, path: String) {
        val activityClass = routeMap[path]
        if (activityClass != null) {
            val intent = Intent(context, activityClass)
            context.startActivity(intent)
        }
    }
}
