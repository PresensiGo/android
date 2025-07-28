# StudentApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**getAllStudents**](StudentApi.md#getAllStudents) | **GET** /api/v1/students |  |
| [**getAllStudentsByClassroomId**](StudentApi.md#getAllStudentsByClassroomId) | **GET** /api/v1/students/classrooms/{classroom_id} |  |


<a id="getAllStudents"></a>
# **getAllStudents**
> GetAllStudentsRes getAllStudents(keyword)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = StudentApi()
val keyword : kotlin.String = keyword_example // kotlin.String | Keyword
try {
    val result : GetAllStudentsRes = apiInstance.getAllStudents(keyword)
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
| **keyword** | **kotlin.String**| Keyword | |

### Return type

[**GetAllStudentsRes**](GetAllStudentsRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllStudentsByClassroomId"></a>
# **getAllStudentsByClassroomId**
> GetAllStudentsByClassroomIdRes getAllStudentsByClassroomId(classroomId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = StudentApi()
val classroomId : kotlin.Int = 56 // kotlin.Int | Classroom Id
try {
    val result : GetAllStudentsByClassroomIdRes = apiInstance.getAllStudentsByClassroomId(classroomId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling StudentApi#getAllStudentsByClassroomId")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling StudentApi#getAllStudentsByClassroomId")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **classroomId** | **kotlin.Int**| Classroom Id | |

### Return type

[**GetAllStudentsByClassroomIdRes**](GetAllStudentsByClassroomIdRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

