# MajorApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**createMajor**](MajorApi.md#createMajor) | **POST** /api/v1/majors |  |
| [**deleteMajor**](MajorApi.md#deleteMajor) | **DELETE** /api/v1/majors/{major_id} |  |
| [**getAllMajors**](MajorApi.md#getAllMajors) | **GET** /api/v1/majors |  |
| [**getAllMajorsByBatchId**](MajorApi.md#getAllMajorsByBatchId) | **GET** /api/v1/batches/{batch_id}/majors |  |
| [**updateMajor**](MajorApi.md#updateMajor) | **PUT** /api/v1/majors/{major_id} |  |


<a id="createMajor"></a>
# **createMajor**
> Major createMajor(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = MajorApi()
val body : CreateGeneralAttendanceReq =  // CreateGeneralAttendanceReq | body
try {
    val result : Major = apiInstance.createMajor(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MajorApi#createMajor")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MajorApi#createMajor")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**CreateGeneralAttendanceReq**](CreateGeneralAttendanceReq.md)| body | |

### Return type

[**Major**](Major.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="deleteMajor"></a>
# **deleteMajor**
> kotlin.String deleteMajor(majorId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = MajorApi()
val majorId : kotlin.Int = 56 // kotlin.Int | major id
try {
    val result : kotlin.String = apiInstance.deleteMajor(majorId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MajorApi#deleteMajor")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MajorApi#deleteMajor")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **majorId** | **kotlin.Int**| major id | |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllMajors"></a>
# **getAllMajors**
> kotlin.collections.List&lt;Major&gt; getAllMajors()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = MajorApi()
try {
    val result : kotlin.collections.List<Major> = apiInstance.getAllMajors()
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
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;Major&gt;**](Major.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllMajorsByBatchId"></a>
# **getAllMajorsByBatchId**
> ResponsesGetAllMajorsByBatchId getAllMajorsByBatchId(batchId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = MajorApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
try {
    val result : ResponsesGetAllMajorsByBatchId = apiInstance.getAllMajorsByBatchId(batchId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MajorApi#getAllMajorsByBatchId")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MajorApi#getAllMajorsByBatchId")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **batchId** | **kotlin.Int**| batch id | |

### Return type

[**ResponsesGetAllMajorsByBatchId**](ResponsesGetAllMajorsByBatchId.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="updateMajor"></a>
# **updateMajor**
> Major updateMajor(majorId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = MajorApi()
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val body : ApiInternalFeaturesMajorDtoRequestsUpdate =  // ApiInternalFeaturesMajorDtoRequestsUpdate | body
try {
    val result : Major = apiInstance.updateMajor(majorId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MajorApi#updateMajor")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MajorApi#updateMajor")
    e.printStackTrace()
}
```

### Parameters
| **majorId** | **kotlin.Int**| major id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**ApiInternalFeaturesMajorDtoRequestsUpdate**](ApiInternalFeaturesMajorDtoRequestsUpdate.md)| body | |

### Return type

[**Major**](Major.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

