package com.zup.authenticator.core.extension

fun <A, B, R> Pair<A, B>.spread(f: (A, B) -> R) = f(first, second)
