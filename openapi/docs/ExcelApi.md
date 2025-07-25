# ExcelApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**importData**](ExcelApi.md#importData) | **POST** /api/v1/excel/import |  |


<a id="importData"></a>
# **importData**
> kotlin.String importData()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = ExcelApi()
try {
    val result : kotlin.String = apiInstance.importData()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ExcelApi#importData")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ExcelApi#importData")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

