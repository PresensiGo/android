# ClassroomApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdPut**](ClassroomApi.md#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdPut) | **PUT** /api/v1/batches/{batch_id}/majors/{major_id}/classrooms/{classroom_id} |  |
| [**apiV1BatchesBatchIdMajorsMajorIdClassroomsPost**](ClassroomApi.md#apiV1BatchesBatchIdMajorsMajorIdClassroomsPost) | **POST** /api/v1/batches/{batch_id}/majors/{major_id}/classrooms |  |
| [**getAllClassroomWithMajors**](ClassroomApi.md#getAllClassroomWithMajors) | **GET** /api/v1/classrooms/batches/{batch_id} |  |
| [**getAllClassrooms**](ClassroomApi.md#getAllClassrooms) | **GET** /api/v1/classrooms |  |
| [**getAllClassroomsByMajorId**](ClassroomApi.md#getAllClassroomsByMajorId) | **GET** /api/v1/batches/{batch_id}/majors/{major_id}/classrooms |  |


<a id="apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdPut"></a>
# **apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdPut**
> ResponsesUpdateClassroom apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdPut(batchId, majorId, majorId2, classroomId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = ClassroomApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val majorId2 : kotlin.Int = 56 // kotlin.Int | major id
val classroomId : kotlin.Int = 56 // kotlin.Int | classroom id
val body : RequestsUpdateClassroom =  // RequestsUpdateClassroom | body
try {
    val result : ResponsesUpdateClassroom = apiInstance.apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdPut(batchId, majorId, majorId2, classroomId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ClassroomApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ClassroomApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdPut")
    e.printStackTrace()
}
```

### Parameters
| **batchId** | **kotlin.Int**| batch id | |
| **majorId** | **kotlin.Int**| major id | |
| **majorId2** | **kotlin.Int**| major id | |
| **classroomId** | **kotlin.Int**| classroom id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsUpdateClassroom**](RequestsUpdateClassroom.md)| body | |

### Return type

[**ResponsesUpdateClassroom**](ResponsesUpdateClassroom.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="apiV1BatchesBatchIdMajorsMajorIdClassroomsPost"></a>
# **apiV1BatchesBatchIdMajorsMajorIdClassroomsPost**
> ResponsesCreateClassroom apiV1BatchesBatchIdMajorsMajorIdClassroomsPost(batchId, majorId, majorId2, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = ClassroomApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val majorId2 : kotlin.Int = 56 // kotlin.Int | major id
val body : RequestsCreateClassroom =  // RequestsCreateClassroom | body
try {
    val result : ResponsesCreateClassroom = apiInstance.apiV1BatchesBatchIdMajorsMajorIdClassroomsPost(batchId, majorId, majorId2, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ClassroomApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ClassroomApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsPost")
    e.printStackTrace()
}
```

### Parameters
| **batchId** | **kotlin.Int**| batch id | |
| **majorId** | **kotlin.Int**| major id | |
| **majorId2** | **kotlin.Int**| major id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsCreateClassroom**](RequestsCreateClassroom.md)| body | |

### Return type

[**ResponsesCreateClassroom**](ResponsesCreateClassroom.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

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

<a id="getAllClassrooms"></a>
# **getAllClassrooms**
> ResponsesGetAll getAllClassrooms()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = ClassroomApi()
try {
    val result : ResponsesGetAll = apiInstance.getAllClassrooms()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ClassroomApi#getAllClassrooms")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ClassroomApi#getAllClassrooms")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ResponsesGetAll**](ResponsesGetAll.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllClassroomsByMajorId"></a>
# **getAllClassroomsByMajorId**
> ResponsesGetAllClassroomsByMajorId getAllClassroomsByMajorId(batchId, majorId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = ClassroomApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
try {
    val result : ResponsesGetAllClassroomsByMajorId = apiInstance.getAllClassroomsByMajorId(batchId, majorId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ClassroomApi#getAllClassroomsByMajorId")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ClassroomApi#getAllClassroomsByMajorId")
    e.printStackTrace()
}
```

### Parameters
| **batchId** | **kotlin.Int**| batch id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **majorId** | **kotlin.Int**| major id | |

### Return type

[**ResponsesGetAllClassroomsByMajorId**](ResponsesGetAllClassroomsByMajorId.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

