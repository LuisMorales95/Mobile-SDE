package com.jlmp.mobilesdecodeexercise.core.rawfile

import android.content.Context
import com.google.gson.Gson
import com.jlmp.mobilesdecodeexercise.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RawFileParser @Inject constructor(
    @ApplicationContext  val context: Context
): RawShipmentParser, RawDriverParser {

    override fun getRawShipment(): RawShipment {
        val rawJson = context.resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        return Gson().fromJson(rawJson, RawShipment::class.java)
    }

    override fun getRawDriver(): RawDriver {
        val rawJson = context.resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        return Gson().fromJson(rawJson, RawDriver::class.java)
    }
}