package com.example.proba.helper

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import android.preference.PreferenceManager
import android.util.Log
import com.example.proba.R
import java.util.*

object LanguageHelper {

    const val PREF_LANGUAGE_CODE = "PREF_LANGUAGE_CODE"
    const val LOCALE_DEFAULT = "hr"

    @JvmStatic
    fun setPreferredLanguage(context: Context, newLocale : String) =
            PreferenceManager.getDefaultSharedPreferences(context)
                    .edit().putString(PREF_LANGUAGE_CODE, newLocale).apply()

    @JvmStatic
    fun getPreferredLanguage(context: Context) =
            PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(PREF_LANGUAGE_CODE, LOCALE_DEFAULT) ?: LOCALE_DEFAULT

    @JvmStatic
    @JvmOverloads
    fun wrapLanguage(context: Context) : Context{
//        val localeCode = getPreferredLanguage(context)
//        Log.d("CURRENT_LANGUAGE", localeCode)
//        val supportedCodeList = context.resources.getStringArray(R.array.supported_languages)

//        if (localeCode != LOCALE_DEFAULT && supportedCodeList.contains(localeCode)) {
//            val locale = createLocale(localeCode)
//            val configuration = Configuration(context.resources.configuration)
//            configuration.setLocale(locale)
//            Locale.setDefault(locale)
//            context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
//        }

        val locale = createLocale(getPreferredLanguage(context))
//        val res = context.resources
//        val dm = res.displayMetrics
        val conf = Configuration(context.resources.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            conf.setLocale(locale)
            Locale.setDefault(locale)
//            val localList = LocaleList(locale)
//            LocaleList.setDefault(localList)
//            conf.setLocales(localList)
        }
        else {
            conf.locale = locale
            Locale.setDefault(locale)

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
            context.createConfigurationContext(conf)
        else
            context.resources.updateConfiguration(conf,context.resources.displayMetrics)

        //        locale = Locale(localeName)
//        val res = resources
//        val dm = res.displayMetrics
//        val conf = res.configuration
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//            val localList = LocaleList(locale)
//            LocaleList.setDefault(localList)
//            conf.setLocales(localList)
//        }
//        else
//            conf.locale = locale
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
//            context?.createConfigurationContext(conf)
//        else
//            res.updateConfiguration(conf,dm)
        return context
    }


    fun createLocale(languageCode: String): Locale {
        Log.d("DANIJEL","createLOcale is "+languageCode)
//        val parts = languageCode.split("-")
//        return Locale(parts[0]).takeIf { parts.size == 1 } ?: Locale(parts[0], parts[1])
        return Locale(languageCode)
    }
}