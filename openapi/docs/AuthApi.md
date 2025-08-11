# AuthApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**login**](AuthApi.md#login) | **POST** /api/v1/auth/login |  |
| [**logout**](AuthApi.md#logout) | **POST** /api/v1/auth/logout |  |
| [**refreshToken**](AuthApi.md#refreshToken) | **POST** /api/v1/auth/refresh-token |  |
| [**refreshTokenTTL**](AuthApi.md#refreshTokenTTL) | **POST** /api/v1/auth/refresh-token-ttl |  |


<a id="login"></a>
# **login**
> LoginRes login(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AuthApi()
val body : LoginReq =  // LoginReq | body
try {
    val result : LoginRes = apiInstance.login(body)
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
> kotlin.Any logout(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AuthApi()
val body : LogoutReq =  // LogoutReq | Logout Request
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
| **body** | [**LogoutReq**](LogoutReq.md)| Logout Request | |

### Return type

[**kotlin.Any**](kotlin.Any.md)

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

val apiInstance = AuthApi()
val body : RefreshTokenReq =  // RefreshTokenReq | Refresh token req
try {
    val result : RefreshTokenRes = apiInstance.refreshToken(body)
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
| **body** | [**RefreshTokenReq**](RefreshTokenReq.md)| Refresh token req | |

### Return type

[**RefreshTokenRes**](RefreshTokenRes.md)

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

