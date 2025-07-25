# AttendanceApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**createAttendance**](AttendanceApi.md#createAttendance) | **POST** /api/v1/attendances/ |  |
| [**getAllAttendances**](AttendanceApi.md#getAllAttendances) | **GET** /api/v1/attendances/class/{class_id} |  |


<a id="createAttendance"></a>
# **createAttendance**
> kotlin.String createAttendance(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
val body : CreateAttendanceReq =  // CreateAttendanceReq | Body
try {
    val result : kotlin.String = apiInstance.createAttendance(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#createAttendance")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#createAttendance")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**CreateAttendanceReq**](CreateAttendanceReq.md)| Body | |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllAttendances"></a>
# **getAllAttendances**
> ResponsesGetAllAttendances getAllAttendances(classId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
val classId : kotlin.Int = 56 // kotlin.Int | Classroom ID
try {
    val result : ResponsesGetAllAttendances = apiInstance.getAllAttendances(classId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#getAllAttendances")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#getAllAttendances")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **classId** | **kotlin.Int**| Classroom ID | |

### Return type

[**ResponsesGetAllAttendances**](ResponsesGetAllAttendances.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

