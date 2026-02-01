package com.moneyplusplus.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneyplusplus.design_system.component.appBar.AccountInfoCard
import com.moneyplusplus.design_system.component.appBar.AppBar
import com.moneyplusplus.design_system.component.appBar.SettingsRowCard
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.account.AccountIntent
import com.moneyplusplus.presentation.account.AccountViewModel
import com.moneyplusplus.presentation.account.SettingsType
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.*

@Composable
fun AccountScreen(
    viewModel: AccountViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(Res.string.account_title),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            )
        },
        backgroundColor = Theme.colorScheme.surface.surface
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_background_acc),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp)
                    .size(width = 251.3.dp, height = 150.dp),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(Theme.colorScheme.primary.primary)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
                modifier = Modifier.fillMaxSize()
            ) {

                item {
                    val profile = state.userProfile
                    AccountInfoCard(
                        name = profile?.name ?: "",
                        email = profile?.email ?: "",
                        initials = profile?.initials ?: "",
                        onEditClick = { viewModel.handleIntent(AccountIntent.EditProfile) }
                    )
                }

                item {
                    SettingsRowCard(
                        title = stringResource(Res.string.manage_categories),
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_dashboard_circle_settings),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            viewModel.handleIntent(AccountIntent.NavigateToSettings(SettingsType.MANAGE_CATEGORIES))
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = stringResource(Res.string.app_language),
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_translation),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            viewModel.handleIntent(AccountIntent.NavigateToSettings(SettingsType.APP_LANGUAGE))
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = stringResource(Res.string.app_theme),
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_sun),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            viewModel.handleIntent(AccountIntent.NavigateToSettings(SettingsType.APP_THEME))
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = stringResource(Res.string.currency),
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_coins),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            viewModel.handleIntent(AccountIntent.NavigateToSettings(SettingsType.CURRENCY))
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = stringResource(Res.string.salary_settings),
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_money),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            viewModel.handleIntent(AccountIntent.NavigateToSettings(SettingsType.SALARY))
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = stringResource(Res.string.faq),
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_help_circle),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            viewModel.handleIntent(AccountIntent.NavigateToSettings(SettingsType.FAQ))
                        }
                    )
                }
                item {
                    SettingsRowCard(
                        title = stringResource(Res.string.help_support),
                        showDivider = false,
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_customer_support),
                                contentDescription = null,
                                tint = Theme.colorScheme.body,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            viewModel.handleIntent(AccountIntent.NavigateToSettings(SettingsType.HELP_SUPPORT))
                        }
                    )
                }


            }
        }
    }
}
