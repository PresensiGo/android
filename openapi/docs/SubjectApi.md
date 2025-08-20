# SubjectApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**createSubject**](SubjectApi.md#createSubject) | **POST** /api/v1/subjects |  |
| [**deleteSubject**](SubjectApi.md#deleteSubject) | **DELETE** /api/v1/subjects/{subject_id} |  |
| [**getAllSubjects**](SubjectApi.md#getAllSubjects) | **GET** /api/v1/subjects |  |
| [**getSubject**](SubjectApi.md#getSubject) | **GET** /api/v1/subjects/{subject_id} |  |
| [**updateSubject**](SubjectApi.md#updateSubject) | **PUT** /api/v1/subjects/{subject_id} |  |


<a id="createSubject"></a>
# **createSubject**
> ResponsesCreateSubject createSubject(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = SubjectApi()
val body : RequestsCreateSubject =  // RequestsCreateSubject | body
try {
    val result : ResponsesCreateSubject = apiInstance.createSubject(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SubjectApi#createSubject")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SubjectApi#createSubject")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsCreateSubject**](RequestsCreateSubject.md)| body | |

### Return type

[**ResponsesCreateSubject**](ResponsesCreateSubject.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="deleteSubject"></a>
# **deleteSubject**
> ResponsesDeleteSubject deleteSubject(subjectId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = SubjectApi()
val subjectId : kotlin.Int = 56 // kotlin.Int | subject id
try {
    val result : ResponsesDeleteSubject = apiInstance.deleteSubject(subjectId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SubjectApi#deleteSubject")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SubjectApi#deleteSubject")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **subjectId** | **kotlin.Int**| subject id | |

### Return type

[**ResponsesDeleteSubject**](ResponsesDeleteSubject.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllSubjects"></a>
# **getAllSubjects**
> ResponsesGetAllSubjects getAllSubjects()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = SubjectApi()
try {
    val result : ResponsesGetAllSubjects = apiInstance.getAllSubjects()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SubjectApi#getAllSubjects")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SubjectApi#getAllSubjects")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ResponsesGetAllSubjects**](ResponsesGetAllSubjects.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getSubject"></a>
# **getSubject**
> GetSubjectRes getSubject(subjectId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = SubjectApi()
val subjectId : kotlin.Int = 56 // kotlin.Int | subject id
try {
    val result : GetSubjectRes = apiInstance.getSubject(subjectId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SubjectApi#getSubject")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SubjectApi#getSubject")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **subjectId** | **kotlin.Int**| subject id | |

### Return type

[**GetSubjectRes**](GetSubjectRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="updateSubject"></a>
# **updateSubject**
> ResponsesUpdateSubject updateSubject(subjectId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = SubjectApi()
val subjectId : kotlin.Int = 56 // kotlin.Int | subject id
val body : RequestsUpdateSubject =  // RequestsUpdateSubject | body
try {
    val result : ResponsesUpdateSubject = apiInstance.updateSubject(subjectId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SubjectApi#updateSubject")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SubjectApi#updateSubject")
    e.printStackTrace()
}
```

### Parameters
| **subjectId** | **kotlin.Int**| subject id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsUpdateSubject**](RequestsUpdateSubject.md)| body | |

### Return type

[**ResponsesUpdateSubject**](ResponsesUpdateSubject.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

