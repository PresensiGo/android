# AccountApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1AccountsAccountIdDelete**](AccountApi.md#apiV1AccountsAccountIdDelete) | **DELETE** /api/v1/accounts/{account_id} |  |
| [**apiV1AccountsAccountIdPasswordPut**](AccountApi.md#apiV1AccountsAccountIdPasswordPut) | **PUT** /api/v1/accounts/{account_id}/password |  |
| [**apiV1AccountsAccountIdRolePut**](AccountApi.md#apiV1AccountsAccountIdRolePut) | **PUT** /api/v1/accounts/{account_id}/role |  |
| [**apiV1AccountsGet**](AccountApi.md#apiV1AccountsGet) | **GET** /api/v1/accounts |  |
| [**login**](AccountApi.md#login) | **POST** /api/v1/auth/login |  |
| [**logout**](AccountApi.md#logout) | **POST** /api/v1/auth/logout |  |
| [**refreshToken**](AccountApi.md#refreshToken) | **POST** /api/v1/auth/refresh-token |  |


<a id="apiV1AccountsAccountIdDelete"></a>
# **apiV1AccountsAccountIdDelete**
> ResponsesDeleteAccount apiV1AccountsAccountIdDelete(accountId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AccountApi()
val accountId : kotlin.Int = 56 // kotlin.Int | account id
try {
    val result : ResponsesDeleteAccount = apiInstance.apiV1AccountsAccountIdDelete(accountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#apiV1AccountsAccountIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#apiV1AccountsAccountIdDelete")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **accountId** | **kotlin.Int**| account id | |

### Return type

[**ResponsesDeleteAccount**](ResponsesDeleteAccount.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="apiV1AccountsAccountIdPasswordPut"></a>
# **apiV1AccountsAccountIdPasswordPut**
> ResponsesUpdateAccountPassword apiV1AccountsAccountIdPasswordPut(accountId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AccountApi()
val accountId : kotlin.Int = 56 // kotlin.Int | account id
val body : RequestsUpdateAccountPassword =  // RequestsUpdateAccountPassword | body
try {
    val result : ResponsesUpdateAccountPassword = apiInstance.apiV1AccountsAccountIdPasswordPut(accountId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#apiV1AccountsAccountIdPasswordPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#apiV1AccountsAccountIdPasswordPut")
    e.printStackTrace()
}
```

### Parameters
| **accountId** | **kotlin.Int**| account id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsUpdateAccountPassword**](RequestsUpdateAccountPassword.md)| body | |

### Return type

[**ResponsesUpdateAccountPassword**](ResponsesUpdateAccountPassword.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="apiV1AccountsAccountIdRolePut"></a>
# **apiV1AccountsAccountIdRolePut**
> ResponsesUpdateAccountRole apiV1AccountsAccountIdRolePut(accountId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AccountApi()
val accountId : kotlin.Int = 56 // kotlin.Int | account id
val body : RequestsUpdateAccountRole =  // RequestsUpdateAccountRole | body
try {
    val result : ResponsesUpdateAccountRole = apiInstance.apiV1AccountsAccountIdRolePut(accountId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#apiV1AccountsAccountIdRolePut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#apiV1AccountsAccountIdRolePut")
    e.printStackTrace()
}
```

### Parameters
| **accountId** | **kotlin.Int**| account id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsUpdateAccountRole**](RequestsUpdateAccountRole.md)| body | |

### Return type

[**ResponsesUpdateAccountRole**](ResponsesUpdateAccountRole.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="apiV1AccountsGet"></a>
# **apiV1AccountsGet**
> ResponsesGetAllUsers apiV1AccountsGet()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AccountApi()
try {
    val result : ResponsesGetAllUsers = apiInstance.apiV1AccountsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#apiV1AccountsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#apiV1AccountsGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ResponsesGetAllUsers**](ResponsesGetAllUsers.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="login"></a>
# **login**
> LoginRes login(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AccountApi()
val body : LoginReq =  // LoginReq | body
try {
    val result : LoginRes = apiInstance.login(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#login")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#login")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**LoginReq**](LoginReq.md)| body | |

### Return type

[**LoginRes**](LoginRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="logout"></a>
# **logout**
> ResponsesLogout logout(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AccountApi()
val body : LogoutReq =  // LogoutReq | Logout Request
try {
    val result : ResponsesLogout = apiInstance.logout(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#logout")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#logout")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**LogoutReq**](LogoutReq.md)| Logout Request | |

### Return type

[**ResponsesLogout**](ResponsesLogout.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="refreshToken"></a>
# **refreshToken**
> RefreshTokenRes refreshToken(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AccountApi()
val body : RefreshTokenReq =  // RefreshTokenReq | body
try {
    val result : RefreshTokenRes = apiInstance.refreshToken(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#refreshToken")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#refreshToken")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RefreshTokenReq**](RefreshTokenReq.md)| body | |

### Return type

[**RefreshTokenRes**](RefreshTokenRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

