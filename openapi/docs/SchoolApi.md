# SchoolApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1SchoolsProfilePut**](SchoolApi.md#apiV1SchoolsProfilePut) | **PUT** /api/v1/schools/profile |  |
| [**getSchool**](SchoolApi.md#getSchool) | **GET** /api/v1/schools/profile |  |


<a id="apiV1SchoolsProfilePut"></a>
# **apiV1SchoolsProfilePut**
> ResponsesUpdateSchool apiV1SchoolsProfilePut(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = SchoolApi()
val body : RequestsUpdateSchool =  // RequestsUpdateSchool | body
try {
    val result : ResponsesUpdateSchool = apiInstance.apiV1SchoolsProfilePut(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SchoolApi#apiV1SchoolsProfilePut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SchoolApi#apiV1SchoolsProfilePut")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsUpdateSchool**](RequestsUpdateSchool.md)| body | |

### Return type

[**ResponsesUpdateSchool**](ResponsesUpdateSchool.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getSchool"></a>
# **getSchool**
> GetSchoolRes getSchool()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = SchoolApi()
try {
    val result : GetSchoolRes = apiInstance.getSchool()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SchoolApi#getSchool")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SchoolApi#getSchool")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GetSchoolRes**](GetSchoolRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

