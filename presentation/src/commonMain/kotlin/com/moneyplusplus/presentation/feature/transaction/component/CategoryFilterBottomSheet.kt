package com.moneyplusplus.presentation.feature.transaction.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.moneyplusplus.design_system.component.button.PrimaryButton
import com.moneyplusplus.design_system.component.chip.Chip
import com.moneyplusplus.design_system.component.icon.Icon
import com.moneyplusplus.design_system.component.text.Text
import com.moneyplusplus.design_system.theme.theme.MoneyTheme
import com.moneyplusplus.design_system.theme.theme.Theme
import com.moneyplusplus.presentation.model.CategoryUiModel
import money.presentation.generated.resources.Res
import money.presentation.generated.resources.apply
import money.presentation.generated.resources.categories_filter
import money.presentation.generated.resources.clear
import money.presentation.generated.resources.close
import money.presentation.generated.resources.ic_cancel_circle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CategoryFilterBottomSheet(
    allCategories: List<CategoryUiModel>,
    selectedCategoryIds: List<String>,
    onApplyClick: (List<String>) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedIds by rememberSaveable(selectedCategoryIds) { mutableStateOf(selectedCategoryIds) }
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .navigationBarsPadding()
            .verticalScroll(scrollState),

        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.categories_filter),
                style = Theme.typography.title.small,
                color = Theme.colorScheme.title
            )
            Icon(
                painter = painterResource(Res.drawable.ic_cancel_circle),
                contentDescription = stringResource(Res.string.close),
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onDismiss() }
                    .padding(4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            allCategories.forEach { category ->
                val isSelected = selectedIds.contains(category.id)
                Chip(
                    text = category.name,
                    isSelected = isSelected,
                    onClick = {
                        selectedIds = if (isSelected) {
                            selectedIds - category.id
                        } else {
                            selectedIds + category.id
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PrimaryButton(
                onClick = {
                    selectedIds = emptyList()
                },
                modifier = Modifier.weight(1f),
                text = stringResource(Res.string.clear)
            )
            PrimaryButton(
                onClick = {
                    onApplyClick(selectedIds)
                },
                modifier = Modifier.weight(1f),
                text = stringResource(Res.string.apply)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryFilterBottomSheetPreview() {
    MoneyTheme {
        CategoryFilterBottomSheet(
            allCategories = listOf(
                CategoryUiModel(
                    id = "1",
                    name = "Food",
                ),
                CategoryUiModel(
                    id = "2",
                    name = "Transport",
                ),
                CategoryUiModel(
                    id = "3",
                    name = "Entertainment",
                ),
                CategoryUiModel(
                    id = "4",
                    name = "Health",
                ),
                CategoryUiModel(
                    id = "5",
                    name = "Education",
                ),
                CategoryUiModel(
                    id = "6",
                    name = "Shopping",
                ),
                CategoryUiModel(
                    id = "7",
                    name = "Bills",
                ),
                CategoryUiModel(
                    id = "8",
                    name = "Other",
                ),
            ),
            selectedCategoryIds = emptyList(),
            onApplyClick = {},
            onDismiss = {}
        )

    }

}