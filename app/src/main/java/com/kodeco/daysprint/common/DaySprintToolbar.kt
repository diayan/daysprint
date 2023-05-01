package com.kodeco.daysprint.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.kodeco.daysprint.ui.theme.colorPrimary
import com.kodeco.daysprint.R.string as AppText


@Composable
fun ActionToolbar(
    @StringRes title: Int,
    @DrawableRes endActionIcon: Int,
    modifier: Modifier,
    endAction: () -> Unit,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {}
) {
    if (canNavigateBack) {
        TopAppBar(
            title = {
                Text(
                stringResource(title),
                color = Color.White
            ) },
            backgroundColor = colorPrimary,
            actions = {
                Box(modifier) {
                    IconButton(onClick = endAction) {
                        Icon(
                            painter = painterResource(endActionIcon),
                            contentDescription = "Action",
                            tint = Color.White
                        )
                    }
                }
            },
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(AppText.back_button),
                        tint = Color.White
                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = {
                Text(
                    stringResource(title),
                    color = Color.White
                )},
            backgroundColor = colorPrimary,
            actions = {
                Box(modifier) {
                    IconButton(onClick = endAction) {
                        Icon(
                            painter = painterResource(endActionIcon),
                            contentDescription = "Action",
                            tint = Color.White
                        )
                    }
                }
            }
        )
    }
}

@Composable
private fun toolbarColor(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (darkTheme) MaterialTheme.colors.secondary else MaterialTheme.colors.primaryVariant
}