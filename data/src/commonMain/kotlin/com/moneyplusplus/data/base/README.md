# Data Layer Base Package

A simple base package for the data layer using Kotlin's built-in `Result` type for consistent error handling across all repositories.

## Files

### SafeCall.kt
Wraps suspending operations with automatic error handling:
```kotlin
suspend fun <T> safeCall(block: suspend () -> T): Result<T>
```

### DataExtensions.kt
Extension function for mapping Result values:
```kotlin
inline fun <T, R> Result<T>.mapResult(transform: (T) -> R): Result<R>
```

## Usage in Repositories

### Example 1: AuthRepository Implementation
```kotlin
class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {
    
    override suspend fun createAccount(
        email: String, 
        name: String, 
        password: String
    ): Result<User> = safeCall {
        authService.createAccount(email, name, password)
            .toDomain()
    }
    
    override suspend fun login(
        email: String, 
        password: String
    ): Result<User> = safeCall {
        authService.login(email, password)
            .toDomain()
    }
    
    override suspend fun signInWithGoogle(
        idToken: String
    ): Result<User> = safeCall {
        authService.signInWithGoogle(idToken)
            .toDomain()
    }
    
    override suspend fun resetPassword(
        email: String
    ): Result<Unit> = safeCall {
        authService.resetPassword(email)
    }
    
    override suspend fun logout(): Result<Unit> = safeCall {
        authService.logout()
    }
}
```

### Example 2: AccountRepository Implementation
```kotlin
class AccountRepositoryImpl(
    private val accountService: AccountService,
    private val localDataSource: AccountLocalDataSource
) : AccountRepository {
    
    override suspend fun saveAccount(
        account: Account
    ): Result<Unit> = safeCall {
        val accountDto = account.toDto()
        accountService.saveAccount(accountDto)
        localDataSource.cacheAccount(accountDto)
    }
    
    override suspend fun getAccount(
        userId: String
    ): Result<Account> = safeCall {
        accountService.getAccount(userId)
    }.mapResult { accountDto ->
        accountDto.toDomain()
    }
}
```

### Example 3: CategoryRepository Implementation
```kotlin
class CategoryRepositoryImpl(
    private val categoryService: CategoryService
) : CategoryRepository {
    
    override suspend fun getCategories(): Result<List<Category>> = safeCall {
        categoryService.getCategories()
    }.mapResult { categoriesDto ->
        categoriesDto.map { it.toDomain() }
    }
    
    override suspend fun createCategory(
        name: String,
        type: CategoryType
    ): Result<Category> = safeCall {
        categoryService.createCategory(name, type)
            .toDomain()
    }
}
```