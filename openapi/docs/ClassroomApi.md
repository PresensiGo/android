# ClassroomApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**getAllClassroomWithMajors**](ClassroomApi.md#getAllClassroomWithMajors) | **GET** /api/v1/classrooms/batches/{batch_id} |  |


<a id="getAllClassroomWithMajors"></a>
# **getAllClassroomWithMajors**
> ResponsesGetAllClassroomWithMajors getAllClassroomWithMajors(batchId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = ClassroomApi()
val batchId : kotlin.Int = 56 // kotlin.Int | Batch Id
try {
    val result : ResponsesGetAllClassroomWithMajors = apiInstance.getAllClassroomWithMajors(batchId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ClassroomApi#getAllClassroomWithMajors")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ClassroomApi#getAllClassroomWithMajors")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **batchId** | **kotlin.Int**| Batch Id | |

### Return type

[**ResponsesGetAllClassroomWithMajors**](ResponsesGetAllClassroomWithMajors.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

