package com.moneyplusplus.data.repository

import com.moneyplusplus.domain.entity.MonthlyOverview
import com.moneyplusplus.domain.repository.StatisticsRepository
import kotlinx.datetime.LocalDate

class StatisticsRepositoryImpl : StatisticsRepository {
    override suspend fun getMonthlyOverview(month: LocalDate): MonthlyOverview {
        // Mock data for now
        return MonthlyOverview(
            income = 1_500_000.0,
            expenses = 850_000.0,
            currency = "IQD",
            maxValue = 2_000_000.0,
            scaleLabels = listOf(
                "0", "250K", "500K", "750K",
                "1M", "1.25M", "1.5M", "1.75M", "2M",
            ),
        )
    }
}
