# ResetApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**reset**](ResetApi.md#reset) | **GET** /api/v1/reset |  |


<a id="reset"></a>
# **reset**
> kotlin.String reset()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = ResetApi()
try {
    val result : kotlin.String = apiInstance.reset()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ResetApi#reset")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ResetApi#reset")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

