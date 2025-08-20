# MajorApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**createMajor**](MajorApi.md#createMajor) | **POST** /api/v1/batches/{batch_id}/majors |  |
| [**deleteMajor**](MajorApi.md#deleteMajor) | **DELETE** /api/v1/batches/{batch_id}/majors/{major_id} |  |
| [**getAllMajors**](MajorApi.md#getAllMajors) | **GET** /api/v1/majors |  |
| [**getAllMajorsByBatchId**](MajorApi.md#getAllMajorsByBatchId) | **GET** /api/v1/batches/{batch_id}/majors |  |
| [**getMajor**](MajorApi.md#getMajor) | **GET** /api/v1/batches/{batch_id}/majors/{major_id} |  |
| [**updateMajor**](MajorApi.md#updateMajor) | **PUT** /api/v1/batches/{batch_id}/majors/{major_id} |  |


<a id="createMajor"></a>
# **createMajor**
> Major createMajor(batchId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = MajorApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val body : RequestsCreateMajor =  // RequestsCreateMajor | body
try {
    val result : Major = apiInstance.createMajor(batchId, body)
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
| **batchId** | **kotlin.Int**| batch id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsCreateMajor**](RequestsCreateMajor.md)| body | |

### Return type

[**Major**](Major.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="deleteMajor"></a>
# **deleteMajor**
> kotlin.String deleteMajor(batchId, majorId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = MajorApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
try {
    val result : kotlin.String = apiInstance.deleteMajor(batchId, majorId)
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
| **batchId** | **kotlin.Int**| batch id | |
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
> GetAllMajorsByBatchIdRes getAllMajorsByBatchId(batchId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = MajorApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
try {
    val result : GetAllMajorsByBatchIdRes = apiInstance.getAllMajorsByBatchId(batchId)
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

[**GetAllMajorsByBatchIdRes**](GetAllMajorsByBatchIdRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getMajor"></a>
# **getMajor**
> GetMajorRes getMajor(batchId, majorId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = MajorApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
try {
    val result : GetMajorRes = apiInstance.getMajor(batchId, majorId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MajorApi#getMajor")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MajorApi#getMajor")
    e.printStackTrace()
}
```

### Parameters
| **batchId** | **kotlin.Int**| batch id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **majorId** | **kotlin.Int**| major id | |

### Return type

[**GetMajorRes**](GetMajorRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="updateMajor"></a>
# **updateMajor**
> Major updateMajor(batchId, majorId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = MajorApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val body : RequestsUpdateMajor =  // RequestsUpdateMajor | body
try {
    val result : Major = apiInstance.updateMajor(batchId, majorId, body)
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
| **batchId** | **kotlin.Int**| batch id | |
| **majorId** | **kotlin.Int**| major id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsUpdateMajor**](RequestsUpdateMajor.md)| body | |

### Return type

[**Major**](Major.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

