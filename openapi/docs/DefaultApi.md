# DefaultApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1TeachersImportPost**](DefaultApi.md#apiV1TeachersImportPost) | **POST** /api/v1/teachers/import |  |


<a id="apiV1TeachersImportPost"></a>
# **apiV1TeachersImportPost**
> ResponsesImportTeacher apiV1TeachersImportPost(file)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = DefaultApi()
val file : io.ktor.client.request.forms.FormPart<io.ktor.client.request.forms.InputProvider> = BINARY_DATA_HERE // io.ktor.client.request.forms.FormPart<io.ktor.client.request.forms.InputProvider> | file
try {
    val result : ResponsesImportTeacher = apiInstance.apiV1TeachersImportPost(file)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#apiV1TeachersImportPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#apiV1TeachersImportPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **file** | **io.ktor.client.request.forms.FormPart&lt;io.ktor.client.request.forms.InputProvider&gt;**| file | |

### Return type

[**ResponsesImportTeacher**](ResponsesImportTeacher.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*

