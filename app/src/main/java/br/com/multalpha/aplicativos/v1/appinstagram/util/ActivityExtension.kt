package br.com.multalpha.aplicativos.v1.appinstagram.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by João Bosco on 11/11/2021.
 */
fun Activity.hideKeyboard() {
    val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    var view: View? = currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.animationEnd(callback: () -> Unit) : AnimatorListenerAdapter {
    return object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            callback.invoke()
        }
    }
}

fun AppCompatActivity.replaceFragment(@IdRes id: Int, fragment: Fragment) {
    if (supportFragmentManager.findFragmentById(id) == null) {
        supportFragmentManager.beginTransaction().apply {
            add(id, fragment, fragment.javaClass.simpleName)
            commit()
        }
    } else {
        supportFragmentManager.beginTransaction().apply {
            replace(id, fragment, fragment.javaClass.simpleName)
            addToBackStack(null)
            commit()
        }
    }
    hideKeyboard()
}

fun Activity.validError(error: String): String {
    var message = "";

    if (error.contains("There is no user record corresponding to this identifier")) {
        message = "E-mail não cadastrado!";
    } else if (error.contains("The email address is badly formatted")) {
        message = "Formato de e-mail inválido!";
    } else if (error.contains("The password is invalid or the user does not have a password")) {
        message = "Senha inválida, tente novamente.";
    } else if (error.contains("The email address is already in use by another account")) {
        message = "Este e-mail já está em uso.";
    } else if (error.contains("Password should be at least 6 characters")) {
        message = "Insira uma senha com no mínimo 6 caracteres.";
    }

    return message;
}