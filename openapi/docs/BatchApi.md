# BatchApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**getAllBatches**](BatchApi.md#getAllBatches) | **GET** /api/v1/batch |  |


<a id="getAllBatches"></a>
# **getAllBatches**
> ResponsesGetAllBatches getAllBatches()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = BatchApi()
try {
    val result : ResponsesGetAllBatches = apiInstance.getAllBatches()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BatchApi#getAllBatches")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BatchApi#getAllBatches")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ResponsesGetAllBatches**](ResponsesGetAllBatches.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

