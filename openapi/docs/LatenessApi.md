# LatenessApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**createLateness**](LatenessApi.md#createLateness) | **POST** /api/v1/latenesses |  |
| [**createLatenessDetail**](LatenessApi.md#createLatenessDetail) | **POST** /api/v1/latenesses/{lateness_id} |  |
| [**getAllLatenesses**](LatenessApi.md#getAllLatenesses) | **GET** /api/v1/latenesses |  |


<a id="createLateness"></a>
# **createLateness**
> kotlin.String createLateness(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = LatenessApi()
val body : CreateLatenessReq =  // CreateLatenessReq | Payload
try {
    val result : kotlin.String = apiInstance.createLateness(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling LatenessApi#createLateness")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling LatenessApi#createLateness")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**CreateLatenessReq**](CreateLatenessReq.md)| Payload | |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="createLatenessDetail"></a>
# **createLatenessDetail**
> kotlin.String createLatenessDetail(latenessId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = LatenessApi()
val latenessId : kotlin.Int = 56 // kotlin.Int | Payload
val body : CreateLatenessDetailReq =  // CreateLatenessDetailReq | Payload
try {
    val result : kotlin.String = apiInstance.createLatenessDetail(latenessId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling LatenessApi#createLatenessDetail")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling LatenessApi#createLatenessDetail")
    e.printStackTrace()
}
```

### Parameters
| **latenessId** | **kotlin.Int**| Payload | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**CreateLatenessDetailReq**](CreateLatenessDetailReq.md)| Payload | |

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllLatenesses"></a>
# **getAllLatenesses**
> GetAllLatenessesRes getAllLatenesses()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = LatenessApi()
try {
    val result : GetAllLatenessesRes = apiInstance.getAllLatenesses()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling LatenessApi#getAllLatenesses")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling LatenessApi#getAllLatenesses")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GetAllLatenessesRes**](GetAllLatenessesRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

