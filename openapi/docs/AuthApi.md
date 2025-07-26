# AuthApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**login**](AuthApi.md#login) | **POST** /api/v1/auth/login |  |
| [**logout**](AuthApi.md#logout) | **POST** /api/v1/auth/logout |  |
| [**refreshToken**](AuthApi.md#refreshToken) | **POST** /api/v1/auth/refresh-token |  |
| [**refreshTokenTTL**](AuthApi.md#refreshTokenTTL) | **POST** /api/v1/auth/refresh-token-ttl |  |
| [**register**](AuthApi.md#register) | **POST** /api/v1/auth/register |  |


<a id="login"></a>
# **login**
> ResponsesLogin login(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AuthApi()
val body : RequestsLogin =  // RequestsLogin | Login request
try {
    val result : ResponsesLogin = apiInstance.login(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#login")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#login")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsLogin**](RequestsLogin.md)| Login request | |

### Return type

[**ResponsesLogin**](ResponsesLogin.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="logout"></a>
# **logout**
> kotlin.Any logout(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AuthApi()
val body : RequestsLogout =  // RequestsLogout | Logout Request
try {
    val result : kotlin.Any = apiInstance.logout(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#logout")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#logout")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsLogout**](RequestsLogout.md)| Logout Request | |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="refreshToken"></a>
# **refreshToken**
> ResponsesRefreshToken refreshToken(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AuthApi()
val body : RequestsRefreshToken =  // RequestsRefreshToken | Refresh token req
try {
    val result : ResponsesRefreshToken = apiInstance.refreshToken(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#refreshToken")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#refreshToken")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsRefreshToken**](RequestsRefreshToken.md)| Refresh token req | |

### Return type

[**ResponsesRefreshToken**](ResponsesRefreshToken.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="refreshTokenTTL"></a>
# **refreshTokenTTL**
> kotlin.String refreshTokenTTL(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AuthApi()
val body : RefreshTokenTTLReq =  // RefreshTokenTTLReq | Refresh token req
try {
    val result : kotlin.String = apiInstance.refreshTokenTTL(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#refreshTokenTTL")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#refreshTokenTTL")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RefreshTokenTTLReq**](RefreshTokenTTLReq.md)| Refresh token req | |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="register"></a>
# **register**
> ResponsesRegister register(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AuthApi()
val body : RequestsRegister =  // RequestsRegister | Login request
try {
    val result : ResponsesRegister = apiInstance.register(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#register")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#register")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsRegister**](RequestsRegister.md)| Login request | |

### Return type

[**ResponsesRegister**](ResponsesRegister.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

