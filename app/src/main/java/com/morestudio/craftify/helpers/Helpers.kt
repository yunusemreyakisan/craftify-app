package com.morestudio.craftify.helpers

import android.text.TextUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Helpers {
    //Fragment loading
    fun loadFragment(fragmentManager: FragmentManager, fragment: Fragment, container: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(container, fragment)
        transaction.commit()
    }

    //Olusturma zamanını al
    fun olusturmaZamaniniGetir(): String {
        val sdf = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

    //isEmpty Control
    fun isFieldEmpty(vararg fields: String): Boolean {
        for (field in fields) {
            if (TextUtils.isEmpty(field)) {
                return true
            }
        }
        return false
    }



}