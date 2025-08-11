# BatchApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1BatchesBatchIdGet**](BatchApi.md#apiV1BatchesBatchIdGet) | **GET** /api/v1/batches/{batch_id} |  |
| [**createBatch**](BatchApi.md#createBatch) | **POST** /api/v1/batches |  |
| [**deleteBatch**](BatchApi.md#deleteBatch) | **DELETE** /api/v1/batches/{batch_id} |  |
| [**getAllBatches**](BatchApi.md#getAllBatches) | **GET** /api/v1/batches |  |
| [**updateBatch**](BatchApi.md#updateBatch) | **PUT** /api/v1/batches/{batch_id} |  |


<a id="apiV1BatchesBatchIdGet"></a>
# **apiV1BatchesBatchIdGet**
> ResponsesGetBatch apiV1BatchesBatchIdGet(batchId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = BatchApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
try {
    val result : ResponsesGetBatch = apiInstance.apiV1BatchesBatchIdGet(batchId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BatchApi#apiV1BatchesBatchIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BatchApi#apiV1BatchesBatchIdGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **batchId** | **kotlin.Int**| batch id | |

### Return type

[**ResponsesGetBatch**](ResponsesGetBatch.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="createBatch"></a>
# **createBatch**
> DomainsBatch createBatch(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = BatchApi()
val body : ApiInternalFeaturesBatchDtoRequestsCreate =  // ApiInternalFeaturesBatchDtoRequestsCreate | body
try {
    val result : DomainsBatch = apiInstance.createBatch(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BatchApi#createBatch")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BatchApi#createBatch")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**ApiInternalFeaturesBatchDtoRequestsCreate**](ApiInternalFeaturesBatchDtoRequestsCreate.md)| body | |

### Return type

[**DomainsBatch**](DomainsBatch.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="deleteBatch"></a>
# **deleteBatch**
> kotlin.String deleteBatch(batchId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = BatchApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
try {
    val result : kotlin.String = apiInstance.deleteBatch(batchId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BatchApi#deleteBatch")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BatchApi#deleteBatch")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **batchId** | **kotlin.Int**| batch id | |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

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

<a id="updateBatch"></a>
# **updateBatch**
> DomainsBatch updateBatch(batchId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = BatchApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val body : ApiInternalFeaturesBatchDtoRequestsUpdate =  // ApiInternalFeaturesBatchDtoRequestsUpdate | body
try {
    val result : DomainsBatch = apiInstance.updateBatch(batchId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BatchApi#updateBatch")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BatchApi#updateBatch")
    e.printStackTrace()
}
```

### Parameters
| **batchId** | **kotlin.Int**| batch id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**ApiInternalFeaturesBatchDtoRequestsUpdate**](ApiInternalFeaturesBatchDtoRequestsUpdate.md)| body | |

### Return type

[**DomainsBatch**](DomainsBatch.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

