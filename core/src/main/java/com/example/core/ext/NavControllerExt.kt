package com.example.core

import androidx.navigation.NavController
import androidx.navigation.NavGraph

fun NavController.navigateToNestedGraphWithStartDestination(graphId: Int, destinationId: Int) {
    graph.findNode(graphId)?.let {
        val originalDestination = (it as NavGraph).startDestination
        it.startDestination = destinationId
        navigate(graphId)
        it.startDestination = originalDestination
    }
}