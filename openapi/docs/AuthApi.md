# AuthApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1AuthLoginPost**](AuthApi.md#apiV1AuthLoginPost) | **POST** /api/v1/auth/login |  |
| [**apiV1AuthRegisterPost**](AuthApi.md#apiV1AuthRegisterPost) | **POST** /api/v1/auth/register |  |


<a id="apiV1AuthLoginPost"></a>
# **apiV1AuthLoginPost**
> AuthLoginResponse apiV1AuthLoginPost(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AuthApi()
val body : AuthLoginRequest =  // AuthLoginRequest | Login request
try {
    val result : AuthLoginResponse = apiInstance.apiV1AuthLoginPost(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#apiV1AuthLoginPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#apiV1AuthLoginPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**AuthLoginRequest**](AuthLoginRequest.md)| Login request | |

### Return type

[**AuthLoginResponse**](AuthLoginResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="apiV1AuthRegisterPost"></a>
# **apiV1AuthRegisterPost**
> AuthRegisterResponse apiV1AuthRegisterPost(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AuthApi()
val body : AuthRegisterRequest =  // AuthRegisterRequest | Login request
try {
    val result : AuthRegisterResponse = apiInstance.apiV1AuthRegisterPost(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#apiV1AuthRegisterPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#apiV1AuthRegisterPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**AuthRegisterRequest**](AuthRegisterRequest.md)| Login request | |

### Return type

[**AuthRegisterResponse**](AuthRegisterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

