# AuthApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**login**](AuthApi.md#login) | **POST** /api/v1/auth/login |  |
| [**register**](AuthApi.md#register) | **POST** /api/v1/auth/register |  |


<a id="login"></a>
# **login**
> ResponsesLoginResponse login(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AuthApi()
val body : RequestsLoginRequest =  // RequestsLoginRequest | Login request
try {
    val result : ResponsesLoginResponse = apiInstance.login(body)
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
| **body** | [**RequestsLoginRequest**](RequestsLoginRequest.md)| Login request | |

### Return type

[**ResponsesLoginResponse**](ResponsesLoginResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="register"></a>
# **register**
> ResponsesRegisterResponse register(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AuthApi()
val body : RequestsRegisterRequest =  // RequestsRegisterRequest | Login request
try {
    val result : ResponsesRegisterResponse = apiInstance.register(body)
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
| **body** | [**RequestsRegisterRequest**](RequestsRegisterRequest.md)| Login request | |

### Return type

[**ResponsesRegisterResponse**](ResponsesRegisterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

