package com.moneyplusplus.data.repository

import com.moneyplusplus.domain.entity.Category
import com.moneyplusplus.domain.entity.Currency
import com.moneyplusplus.domain.entity.Transaction
import com.moneyplusplus.domain.entity.TransactionType
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
object FakeDataSource {


    private val EGP = Currency("EGP", "Egyptian Pound", "Egypt")
    private fun getTitlesForCategory(category: Category): List<String> {
        return when (category.name) {
            "Food" -> listOf(
                "Starbucks",
                "McDonald's",
                "Carrefour Grocery",
                "Dinner at Restaurant",
                "Coffee Break"
            )

            "Transport" -> listOf(
                "Uber Ride",
                "Gas Station",
                "Bus Ticket",
                "Car Maintenance",
                "Metro"
            )

            "Shopping" -> listOf(
                "Amazon Order",
                "Zara Clothing",
                "Ikea Furniture",
                "Noon Purchase",
                "Nike Shoes"
            )

            "Bills" -> listOf(
                "Vodafone Bill",
                "Electricity Bill",
                "Internet Subscription",
                "Netflix",
                "Spotify"
            )

            "Salary" -> listOf("Monthly Salary", "Yearly Bonus")
            "Freelance" -> listOf("Upwork Project", "Private Client Payment", "Consultation Fee")
            "Investment" -> listOf("Stock Dividends", "Gold Sale", "Crypto Profit")
            else -> listOf("Unknown Transaction")
        }
    }
    fun getFakeCategories(): List<Category> {
        return listOf(
            Category(id = Uuid.parse("550e8400-e29b-41d4-a716-446655440000"), name = "Food"),
            Category(id = Uuid.parse("6ba7b810-9dad-11d1-80b4-00c04fd430c8"), name = "Transport"),
            Category(id = Uuid.parse("6ba7b811-9dad-11d1-80b4-00c04fd430c8"), name = "Shopping"),
            Category(id = Uuid.parse("6ba7b812-9dad-11d1-80b4-00c04fd430c8"), name = "Salary"),
            Category(id = Uuid.parse("6ba7b813-9dad-11d1-80b4-00c04fd430c8"), name = "Investment"),
            Category(id = Uuid.parse("6ba7b814-9dad-11d1-80b4-00c04fd430c8"), name = "Education"),
            Category(id = Uuid.parse("6ba7b815-9dad-11d1-80b4-00c04fd430c8"), name = "Freelance"),
            Category(id = Uuid.parse("6ba7b816-9dad-11d1-80b4-00c04fd430c8"), name = "Bills")
        )
    }
    fun getFakeTransactions(count: Int = 50): List<Transaction> {
        val transactions = mutableListOf<Transaction>()
        val currentMoment = Clock.System.now()
        val timeZone = TimeZone.currentSystemDefault()
        val fakeCategories = getFakeCategories()

        repeat(count) {
            val isExpense = Random.nextInt(100) < 70
            val type = if (isExpense) TransactionType.EXPENSE else TransactionType.INCOME

            val category = if (isExpense) {
                fakeCategories.filter { it.name == "Food" || it.name == "Transport" || it.name == "Shopping" || it.name == "Bills" }
                    .random()
            } else {
                fakeCategories.filter { it.name == "Salary" || it.name == "Freelance" || it.name == "Investment" }
                    .random()
            }

            val title = getTitlesForCategory(category).random()

            val amount = if (isExpense) {
                Random.nextDouble(10.0, 500.0)
            } else {
                Random.nextDouble(1000.0, 5000.0)
            }
            val fakeDate = currentMoment.toLocalDateTime(timeZone)

            transactions.add(
                Transaction(
                    id = Uuid.random(),
                    title = title,
                    amount = amount,
                    currency = EGP,
                    date = fakeDate,
                    type = type,
                    category = category
                )
            )
        }

        return transactions.sortedByDescending { it.date }
    }
}