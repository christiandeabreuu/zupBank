package com.zup.authenticator.core.extension

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

fun Fragment.toast(msg: String?) = Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
fun Activity.toast(msg: String?) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Fragment.snake(view: View, msg: String) = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()

fun Fragment.navTo(@IdRes dest: Int) = findNavController().navigate(dest)
fun Fragment.navTo(directions: NavDirections) = findNavController().navigate(directions)
fun Fragment.navTo(@IdRes dest: Int, navOptions: NavOptions) =
    findNavController().navigate(dest, null, navOptions)

fun Fragment.navBack() = findNavController().navigateUp()
