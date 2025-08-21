# SchoolApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**getSchool**](SchoolApi.md#getSchool) | **GET** /api/v1/schools/profile |  |


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

