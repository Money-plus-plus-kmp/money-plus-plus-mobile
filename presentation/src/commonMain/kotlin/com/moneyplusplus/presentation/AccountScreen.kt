package com.moneyplusplus.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.appBar.AccountInfoCard
import com.moneyplusplus.design_system.component.appBar.AppBar
import com.moneyplusplus.design_system.component.bottomNavigation.BottomNavigationBar
import com.moneyplusplus.design_system.component.bottomNavigation.BottomNavigationItem
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.generated.resources.Res
import com.moneyplusplus.design_system.generated.resources.ic_coins
import com.moneyplusplus.design_system.generated.resources.ic_customer_support
import com.moneyplusplus.design_system.generated.resources.ic_dashboard_circle_settings
import com.moneyplusplus.design_system.generated.resources.ic_fill_chart_bar_line
import com.moneyplusplus.design_system.generated.resources.ic_fill_file
import com.moneyplusplus.design_system.generated.resources.ic_fill_home
import com.moneyplusplus.design_system.generated.resources.ic_fill_user_circle
import com.moneyplusplus.design_system.generated.resources.ic_help_circle
import com.moneyplusplus.design_system.generated.resources.ic_money
import com.moneyplusplus.design_system.generated.resources.ic_out_chart_bar_line
import com.moneyplusplus.design_system.generated.resources.ic_out_file
import com.moneyplusplus.design_system.generated.resources.ic_out_home
import com.moneyplusplus.design_system.generated.resources.ic_out_user_circle
import com.moneyplusplus.design_system.generated.resources.ic_sun
import com.moneyplusplus.design_system.generated.resources.ic_translation
import com.moneyplusplus.design_system.theme.theme.Theme
import org.jetbrains.compose.resources.painterResource

@Composable
fun AccountScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colorScheme.surface.surface)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Theme.colorScheme.surface.surfaceLow)
                    .statusBarsPadding()
            ) {
                AppBar(
                    title = "Account",
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
                )
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 88.dp
                ),
                modifier = Modifier.fillMaxSize()
            ) {

                item {
                    AccountInfoCard(
                        name = "Hamsa Ali",
                        email = "HamsaAli2025@gmail.com",
                        initials = "HA",
                        onEditClick = {}
                    )
                }

                item {
                    SettingsRowCard(
                        title = "Manage categories",
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_dashboard_circle_settings),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = "App language",
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_translation),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = "App theme",
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_sun),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = "Currency",
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_coins),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = "Salary settings",
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_money),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = "Frequently asked question",
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_help_circle),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = "Help & Support",
                        showDivider = false,
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_customer_support),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }

                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "App version 1.0",
                            style = Theme.typography.body.medium,
                            color = Theme.colorScheme.body
                        )
                    }
                }
            }
        }


        BottomNavigationBar(
            selectedItemIndex = 3,
            onItemSelected = {},
            items = listOf(

                BottomNavigationItem(
                    selectedIcon = painterResource(Res.drawable.ic_fill_home),
                    notSelectedIcon = painterResource(Res.drawable.ic_out_home),
                    title = "Home"
                ),

                BottomNavigationItem(
                    selectedIcon = painterResource(Res.drawable.ic_fill_file),
                    notSelectedIcon = painterResource(Res.drawable.ic_out_file),
                    title = "Transaction"
                ),

                BottomNavigationItem(
                    selectedIcon = painterResource(Res.drawable.ic_fill_chart_bar_line),
                    notSelectedIcon = painterResource(Res.drawable.ic_out_chart_bar_line),
                    title = "Statistics"
                ),

                BottomNavigationItem(
                    selectedIcon = painterResource(Res.drawable.ic_fill_user_circle),
                    notSelectedIcon = painterResource(Res.drawable.ic_out_user_circle),
                    title = "Account"
                )
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }
}
