# MajorApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**getAllMajors**](MajorApi.md#getAllMajors) | **GET** /api/v1/majors/batch/{batch_id} |  |


<a id="getAllMajors"></a>
# **getAllMajors**
> ResponsesGetAllMajors getAllMajors(batchId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = MajorApi()
val batchId : kotlin.Int = 56 // kotlin.Int | Batch Id
try {
    val result : ResponsesGetAllMajors = apiInstance.getAllMajors(batchId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MajorApi#getAllMajors")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MajorApi#getAllMajors")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **batchId** | **kotlin.Int**| Batch Id | |

### Return type

[**ResponsesGetAllMajors**](ResponsesGetAllMajors.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

