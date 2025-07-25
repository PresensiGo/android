# StudentApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**getAllStudents**](StudentApi.md#getAllStudents) | **GET** /api/v1/students/classrooms/{classroom_id} |  |


<a id="getAllStudents"></a>
# **getAllStudents**
> ResponsesGetAllStudents getAllStudents(classroomId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = StudentApi()
val classroomId : kotlin.Int = 56 // kotlin.Int | Classroom ID
try {
    val result : ResponsesGetAllStudents = apiInstance.getAllStudents(classroomId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling StudentApi#getAllStudents")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling StudentApi#getAllStudents")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **classroomId** | **kotlin.Int**| Classroom ID | |

### Return type

[**ResponsesGetAllStudents**](ResponsesGetAllStudents.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

