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
> Batch createBatch(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = BatchApi()
val body : RequestsCreateBatch =  // RequestsCreateBatch | body
try {
    val result : Batch = apiInstance.createBatch(body)
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
| **body** | [**RequestsCreateBatch**](RequestsCreateBatch.md)| body | |

### Return type

[**Batch**](Batch.md)

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
> GetAllBatchesRes getAllBatches()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = BatchApi()
try {
    val result : GetAllBatchesRes = apiInstance.getAllBatches()
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

[**GetAllBatchesRes**](GetAllBatchesRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="updateBatch"></a>
# **updateBatch**
> Batch updateBatch(batchId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = BatchApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val body : RequestsUpdateBatch =  // RequestsUpdateBatch | body
try {
    val result : Batch = apiInstance.updateBatch(batchId, body)
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
| **body** | [**RequestsUpdateBatch**](RequestsUpdateBatch.md)| body | |

### Return type

[**Batch**](Batch.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

