package br.com.multalpha.aplicativos.v1.appinstagram.util

import android.app.Activity
import br.com.multalpha.aplicativos.v1.appinstagram.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by João Bosco on 02/02/2022.
 * e-mail - Support: ti.junior@gmail.com
 */

object Files {

    private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

    fun generateFile(activity: Activity): File {
        val mediaDir = activity.externalMediaDirs.firstOrNull()?.let { file ->
            File(file, activity.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }

        val outputDir = if (mediaDir != null && mediaDir.exists())
            mediaDir else activity.filesDir

        return File(
            outputDir,
            SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis()) + ".jpg"
        )
    }

}