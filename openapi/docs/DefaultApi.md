# DefaultApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1AccountsImportPost**](DefaultApi.md#apiV1AccountsImportPost) | **POST** /api/v1/accounts/import |  |


<a id="apiV1AccountsImportPost"></a>
# **apiV1AccountsImportPost**
> ResponsesImportAccounts apiV1AccountsImportPost(file)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = DefaultApi()
val file : io.ktor.client.request.forms.FormPart<io.ktor.client.request.forms.InputProvider> = BINARY_DATA_HERE // io.ktor.client.request.forms.FormPart<io.ktor.client.request.forms.InputProvider> | file
try {
    val result : ResponsesImportAccounts = apiInstance.apiV1AccountsImportPost(file)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#apiV1AccountsImportPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#apiV1AccountsImportPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **file** | **io.ktor.client.request.forms.FormPart&lt;io.ktor.client.request.forms.InputProvider&gt;**| file | |

### Return type

[**ResponsesImportAccounts**](ResponsesImportAccounts.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*

