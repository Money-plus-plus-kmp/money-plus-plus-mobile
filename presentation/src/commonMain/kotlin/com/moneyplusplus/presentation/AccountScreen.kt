package com.moneyplusplus.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneyplusplus.design_system.component.appBar.AccountInfoCard
import com.moneyplusplus.design_system.component.appBar.AppBar
import com.moneyplusplus.design_system.component.scaffold.Scaffold
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.account.AccountIntent
import com.moneyplusplus.presentation.account.AccountViewModel
import com.moneyplusplus.presentation.account.SettingsType
import com.moneyplusplus.presentation.account.component.SettingsRowCard
import money.presentation.generated.resources.Res
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountScreen(
    appVersion: String,
    viewModel: AccountViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(Res.string.account_title),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
            )
        },
        backgroundColor = Theme.colorScheme.surface.surface
    ) {
        val settingItems = listOf(
            AccountSettingItem(
                titleRes = Res.string.manage_categories,
                iconRes = Res.drawable.ic_dashboard_circle_settings,
                type = SettingsType.MANAGE_CATEGORIES
            ),
            AccountSettingItem(
                titleRes = Res.string.app_language,
                iconRes = Res.drawable.ic_translation,
                type = SettingsType.APP_LANGUAGE
            ),
            AccountSettingItem(
                titleRes = Res.string.app_theme,
                iconRes = Res.drawable.ic_sun,
                type = SettingsType.APP_THEME
            ),
            AccountSettingItem(
                titleRes = Res.string.currency,
                iconRes = Res.drawable.ic_coins,
                type = SettingsType.CURRENCY
            ),
            AccountSettingItem(
                titleRes = Res.string.salary_settings,
                iconRes = Res.drawable.ic_money,
                type = SettingsType.SALARY
            ),
            AccountSettingItem(
                titleRes = Res.string.faq,
                iconRes = Res.drawable.ic_help_circle,
                type = SettingsType.FAQ
            ),
            AccountSettingItem(
                titleRes = Res.string.help_support,
                iconRes = Res.drawable.ic_customer_support,
                type = SettingsType.HELP_SUPPORT
            )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Decorative background pattern at bottom
            Image(
                painter = painterResource(Res.drawable.ic_background_acc),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(width = 251.dp, height = 150.dp),
                alpha = 0.02f
            )

            LazyColumn(
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    top = 16.dp,
                    bottom = 80.dp
                ),
                modifier = Modifier.fillMaxSize()
            ) {

                item {
                    val profile = state.userProfile
                    val initials = profile?.name
                        ?.split(" ")
                        ?.filter { it.isNotBlank() }
                        ?.take(2)
                        ?.joinToString(" ") { it.first().uppercase() }
                        .orEmpty()

                    AccountInfoCard(
                        name = profile?.name ?: "",
                        email = profile?.email ?: "",
                        initials = initials,
                        modifier = Modifier.padding(bottom = 32.dp),
                        onEditClick = { viewModel.handleIntent(AccountIntent.EditProfile) }
                    )
                }

                itemsIndexed(settingItems) { index, settingItem ->
                    SettingsRowCard(
                        title = stringResource(settingItem.titleRes),
                        icon = painterResource(settingItem.iconRes),
                        showDivider = index != settingItems.lastIndex,
                        onClick = {
                            viewModel.handleIntent(
                                AccountIntent.NavigateToSettings(settingItem.type)
                            )
                        }
                    )
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 64.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(Res.string.app_version).replace("%s", appVersion),
                            style = Theme.typography.body.medium,
                            color = Theme.colorScheme.body
                        )
                    }
                }
            }
        }
    }
}

private data class AccountSettingItem(
    val titleRes: StringResource,
    val iconRes: DrawableResource,
    val type: SettingsType
)
