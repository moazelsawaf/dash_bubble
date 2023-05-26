package dev.moaz.dash_bubble.src

import android.content.Context
import android.content.res.Resources
import android.util.Log
//import android.content.Intent
import io.flutter.plugin.common.MethodCall

/** Helpers is the class that contains the helper methods for the plugin */
class Helpers {

    companion object {
//        /** Bring the app to the foreground.
//         * @param context The context of the application.
//         */
//        fun bringAppToForeground(context: Context) {
//            val intent = Intent(context, MainActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.action = Intent.ACTION_MAIN
//            intent.addCategory(Intent.CATEGORY_LAUNCHER)
//            context.startActivity(intent)
//        }

        /**
         * Get the application name from the context.
         * @param context The context of the application.
         * @return The application name.
         */
        fun getApplicationName(context: Context): String {
            val appInfo = context.applicationInfo
            return context.packageManager.getApplicationLabel(appInfo).toString()
        }

        /**
         * Get the drawable id from the name of the drawable.
         * @param context The context of the application.
         * @param name The name of the drawable.
         * @param default The default drawable id to return if the drawable is not found.
         * @return The drawable id.
         */
        fun getDrawableId(context: Context, name: String?, default: Int): Int {
            if (name == null) return default

            val identifier = context.resources.getIdentifier(name, "drawable", context.packageName)

            return if (identifier == 0) {
                Log.w(Constants.ERROR_TAG, "Could not find resource with name $name")
                default
            } else {
                identifier
            }
        }

        /** Convert pixels (px) to density-independent pixels (dp).
         * @param px The pixel value to convert.
         * @return The converted dp value.
         */
        fun pxToDp(px: Double): Double {
            return px / Resources.getSystem().displayMetrics.density
        }
    }
}