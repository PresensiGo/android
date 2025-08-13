# AccountApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1AccountsGet**](AccountApi.md#apiV1AccountsGet) | **GET** /api/v1/accounts |  |
| [**login**](AccountApi.md#login) | **POST** /api/v1/auth/login |  |
| [**logout**](AccountApi.md#logout) | **POST** /api/v1/auth/logout |  |
| [**refreshToken**](AccountApi.md#refreshToken) | **POST** /api/v1/auth/refresh-token |  |


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

