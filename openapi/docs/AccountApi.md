# AccountApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1AccountsGet**](AccountApi.md#apiV1AccountsGet) | **GET** /api/v1/accounts |  |


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

