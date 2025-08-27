# DefaultApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1AccountsImportPost**](DefaultApi.md#apiV1AccountsImportPost) | **POST** /api/v1/accounts/import |  |
| [**apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdDelete**](DefaultApi.md#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdDelete) | **DELETE** /api/v1/batches/{batch_id}/majors/{major_id}/classrooms/{classroom_id}/students/{student_id} |  |
| [**apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdPut**](DefaultApi.md#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdPut) | **PUT** /api/v1/batches/{batch_id}/majors/{major_id}/classrooms/{classroom_id}/students/{student_id} |  |
| [**apiV1ExcelImportDataPost**](DefaultApi.md#apiV1ExcelImportDataPost) | **POST** /api/v1/excel/import-data |  |


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

<a id="apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdDelete"></a>
# **apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdDelete**
> ResponsesDeleteStudent apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdDelete(batchId, majorId, classroomId, studentId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = DefaultApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val classroomId : kotlin.Int = 56 // kotlin.Int | classroom id
val studentId : kotlin.Int = 56 // kotlin.Int | student id
try {
    val result : ResponsesDeleteStudent = apiInstance.apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdDelete(batchId, majorId, classroomId, studentId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdDelete")
    e.printStackTrace()
}
```

### Parameters
| **batchId** | **kotlin.Int**| batch id | |
| **majorId** | **kotlin.Int**| major id | |
| **classroomId** | **kotlin.Int**| classroom id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **studentId** | **kotlin.Int**| student id | |

### Return type

[**ResponsesDeleteStudent**](ResponsesDeleteStudent.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdPut"></a>
# **apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdPut**
> ResponsesUpdateStudent apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdPut(batchId, majorId, classroomId, studentId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = DefaultApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val classroomId : kotlin.Int = 56 // kotlin.Int | classroom id
val studentId : kotlin.Int = 56 // kotlin.Int | student id
val body : RequestsUpdateStudent =  // RequestsUpdateStudent | body
try {
    val result : ResponsesUpdateStudent = apiInstance.apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdPut(batchId, majorId, classroomId, studentId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentsStudentIdPut")
    e.printStackTrace()
}
```

### Parameters
| **batchId** | **kotlin.Int**| batch id | |
| **majorId** | **kotlin.Int**| major id | |
| **classroomId** | **kotlin.Int**| classroom id | |
| **studentId** | **kotlin.Int**| student id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsUpdateStudent**](RequestsUpdateStudent.md)| body | |

### Return type

[**ResponsesUpdateStudent**](ResponsesUpdateStudent.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="apiV1ExcelImportDataPost"></a>
# **apiV1ExcelImportDataPost**
> ResponsesImportData apiV1ExcelImportDataPost(file)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = DefaultApi()
val file : io.ktor.client.request.forms.FormPart<io.ktor.client.request.forms.InputProvider> = BINARY_DATA_HERE // io.ktor.client.request.forms.FormPart<io.ktor.client.request.forms.InputProvider> | file
try {
    val result : ResponsesImportData = apiInstance.apiV1ExcelImportDataPost(file)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#apiV1ExcelImportDataPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#apiV1ExcelImportDataPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **file** | **io.ktor.client.request.forms.FormPart&lt;io.ktor.client.request.forms.InputProvider&gt;**| file | |

### Return type

[**ResponsesImportData**](ResponsesImportData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*

