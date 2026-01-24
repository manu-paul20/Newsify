package com.manu.newsapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

class NavigationState(
    val startRoute: NavKey,
    topLevelRoute: MutableState<NavKey>,
    val backStack: Map<NavKey, NavBackStack<NavKey>>
) {
    var topLevelRoute by topLevelRoute

    val stackInUse: List<NavKey>
        get() = if (startRoute == topLevelRoute) {
            listOf(startRoute)
        } else {
            listOf(startRoute, topLevelRoute)
        }

}

@Composable
fun rememberNavigationState(
    startRoute: NavKey,
    topLevelRoutes: Set<NavKey>
): NavigationState {
    val topLevelRoute = rememberSerializable(
        startRoute,
        topLevelRoutes,
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Routes.HomeScreen::class, Routes.HomeScreen.serializer())
                    subclass(Routes.BookMarksScreen::class, Routes.BookMarksScreen.serializer())
                    subclass(Routes.OfflineNewsScreen::class, Routes.OfflineNewsScreen.serializer())
                    subclass(Routes.NewsDetailsScreen::class, Routes.NewsDetailsScreen.serializer())
                }
            }
        },
        serializer = MutableStateSerializer(PolymorphicSerializer(NavKey::class))
    ) {
        mutableStateOf(startRoute)
    }

    val backStacks = topLevelRoutes.associateWith {
        rememberNavBackStack(it)
    }

    return remember(startRoute, topLevelRoutes) {
        NavigationState(
            startRoute = startRoute,
            topLevelRoute = topLevelRoute,
            backStack = backStacks
        )
    }
}

@Composable
fun NavigationState.toEntries(
    entryProvider:(NavKey)-> NavEntry<NavKey>
): SnapshotStateList<NavEntry<NavKey>>{
    val decoratedEntries = backStack.mapValues {(_,stack)->
        val decorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
            rememberViewModelStoreNavEntryDecorator()
        )

        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = decorators,
            entryProvider = entryProvider
        )

    }
    return stackInUse
        .flatMap { decoratedEntries[it]?:emptyList() }
        .toMutableStateList()
}
