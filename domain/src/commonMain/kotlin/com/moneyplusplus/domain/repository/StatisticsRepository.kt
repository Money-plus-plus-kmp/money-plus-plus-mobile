package com.moneyplusplus.domain.repository

import com.moneyplusplus.domain.entity.MonthlyOverview
import kotlinx.datetime.LocalDate

interface StatisticsRepository {
    suspend fun getMonthlyOverview(month: LocalDate): MonthlyOverview?
}
