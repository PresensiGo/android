# AttendanceApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesGet**](AttendanceApi.md#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesGet) | **GET** /api/v1/batches/{batch_id}/majors/{major_id}/classrooms/{classroom_id}/subject-attendances |  |
| [**apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesPost**](AttendanceApi.md#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesPost) | **POST** /api/v1/batches/{batch_id}/majors/{major_id}/classrooms/{classroom_id}/subject-attendances |  |
| [**apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesRecordsStudentPost**](AttendanceApi.md#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesRecordsStudentPost) | **POST** /api/v1/batches/{batch_id}/majors/{major_id}/classrooms/{classroom_id}/subject-attendances/records/student |  |
| [**apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesSubjectAttendanceIdGet**](AttendanceApi.md#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesSubjectAttendanceIdGet) | **GET** /api/v1/batches/{batch_id}/majors/{major_id}/classrooms/{classroom_id}/subject-attendances/{subject_attendance_id} |  |
| [**apiV1GeneralAttendancesRecordsStudentPost**](AttendanceApi.md#apiV1GeneralAttendancesRecordsStudentPost) | **POST** /api/v1/general-attendances/records/student |  |
| [**createGeneralAttendance**](AttendanceApi.md#createGeneralAttendance) | **POST** /api/v1/general_attendances |  |
| [**deleteGeneralAttendance**](AttendanceApi.md#deleteGeneralAttendance) | **DELETE** /api/v1/general_attendances/{general_attendance_id} |  |
| [**getAllGeneralAttendances**](AttendanceApi.md#getAllGeneralAttendances) | **GET** /api/v1/general_attendances |  |
| [**getGeneralAttendance**](AttendanceApi.md#getGeneralAttendance) | **GET** /api/v1/general_attendances/{general_attendance_id} |  |
| [**updateGeneralAttendance**](AttendanceApi.md#updateGeneralAttendance) | **PUT** /api/v1/general_attendances/{general_attendance_id} |  |


<a id="apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesGet"></a>
# **apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesGet**
> ResponsesGetAllSubjectAttendances apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesGet(batchId, majorId, classroomId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val classroomId : kotlin.Int = 56 // kotlin.Int | classroom id
try {
    val result : ResponsesGetAllSubjectAttendances = apiInstance.apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesGet(batchId, majorId, classroomId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesGet")
    e.printStackTrace()
}
```

### Parameters
| **batchId** | **kotlin.Int**| batch id | |
| **majorId** | **kotlin.Int**| major id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **classroomId** | **kotlin.Int**| classroom id | |

### Return type

[**ResponsesGetAllSubjectAttendances**](ResponsesGetAllSubjectAttendances.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesPost"></a>
# **apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesPost**
> ResponsesCreateSubjectAttendance apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesPost(batchId, majorId, classroomId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val classroomId : kotlin.Int = 56 // kotlin.Int | classroom id
val body : RequestsCreateSubjectAttendance =  // RequestsCreateSubjectAttendance | body
try {
    val result : ResponsesCreateSubjectAttendance = apiInstance.apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesPost(batchId, majorId, classroomId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesPost")
    e.printStackTrace()
}
```

### Parameters
| **batchId** | **kotlin.Int**| batch id | |
| **majorId** | **kotlin.Int**| major id | |
| **classroomId** | **kotlin.Int**| classroom id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsCreateSubjectAttendance**](RequestsCreateSubjectAttendance.md)| body | |

### Return type

[**ResponsesCreateSubjectAttendance**](ResponsesCreateSubjectAttendance.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesRecordsStudentPost"></a>
# **apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesRecordsStudentPost**
> ResponsesCreateSubjectAttendanceRecordStudent apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesRecordsStudentPost(batchId, majorId, classroomId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val classroomId : kotlin.Int = 56 // kotlin.Int | classroom id
val body : RequestsCreateSubjectAttendanceRecordStudent =  // RequestsCreateSubjectAttendanceRecordStudent | body
try {
    val result : ResponsesCreateSubjectAttendanceRecordStudent = apiInstance.apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesRecordsStudentPost(batchId, majorId, classroomId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesRecordsStudentPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesRecordsStudentPost")
    e.printStackTrace()
}
```

### Parameters
| **batchId** | **kotlin.Int**| batch id | |
| **majorId** | **kotlin.Int**| major id | |
| **classroomId** | **kotlin.Int**| classroom id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsCreateSubjectAttendanceRecordStudent**](RequestsCreateSubjectAttendanceRecordStudent.md)| body | |

### Return type

[**ResponsesCreateSubjectAttendanceRecordStudent**](ResponsesCreateSubjectAttendanceRecordStudent.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesSubjectAttendanceIdGet"></a>
# **apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesSubjectAttendanceIdGet**
> ResponsesGetSubjectAttendance apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesSubjectAttendanceIdGet(batchId, majorId, classroomId, subjectAttendanceId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val classroomId : kotlin.Int = 56 // kotlin.Int | classroom id
val subjectAttendanceId : kotlin.Int = 56 // kotlin.Int | subject attendance id
try {
    val result : ResponsesGetSubjectAttendance = apiInstance.apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesSubjectAttendanceIdGet(batchId, majorId, classroomId, subjectAttendanceId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesSubjectAttendanceIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdSubjectAttendancesSubjectAttendanceIdGet")
    e.printStackTrace()
}
```

### Parameters
| **batchId** | **kotlin.Int**| batch id | |
| **majorId** | **kotlin.Int**| major id | |
| **classroomId** | **kotlin.Int**| classroom id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **subjectAttendanceId** | **kotlin.Int**| subject attendance id | |

### Return type

[**ResponsesGetSubjectAttendance**](ResponsesGetSubjectAttendance.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="apiV1GeneralAttendancesRecordsStudentPost"></a>
# **apiV1GeneralAttendancesRecordsStudentPost**
> ResponsesCreateGeneralAttendanceRecordStudent apiV1GeneralAttendancesRecordsStudentPost(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
val body : RequestsCreateGeneralAttendanceRecordStudent =  // RequestsCreateGeneralAttendanceRecordStudent | body
try {
    val result : ResponsesCreateGeneralAttendanceRecordStudent = apiInstance.apiV1GeneralAttendancesRecordsStudentPost(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#apiV1GeneralAttendancesRecordsStudentPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#apiV1GeneralAttendancesRecordsStudentPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsCreateGeneralAttendanceRecordStudent**](RequestsCreateGeneralAttendanceRecordStudent.md)| body | |

### Return type

[**ResponsesCreateGeneralAttendanceRecordStudent**](ResponsesCreateGeneralAttendanceRecordStudent.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="createGeneralAttendance"></a>
# **createGeneralAttendance**
> ResponsesCreateGeneralAttendance createGeneralAttendance(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
val body : RequestsCreateGeneralAttendance =  // RequestsCreateGeneralAttendance | body
try {
    val result : ResponsesCreateGeneralAttendance = apiInstance.createGeneralAttendance(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#createGeneralAttendance")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#createGeneralAttendance")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsCreateGeneralAttendance**](RequestsCreateGeneralAttendance.md)| body | |

### Return type

[**ResponsesCreateGeneralAttendance**](ResponsesCreateGeneralAttendance.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="deleteGeneralAttendance"></a>
# **deleteGeneralAttendance**
> ResponsesDeleteGeneralAttendance deleteGeneralAttendance(generalAttendanceId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
val generalAttendanceId : kotlin.Int = 56 // kotlin.Int | general attendance id
try {
    val result : ResponsesDeleteGeneralAttendance = apiInstance.deleteGeneralAttendance(generalAttendanceId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#deleteGeneralAttendance")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#deleteGeneralAttendance")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **generalAttendanceId** | **kotlin.Int**| general attendance id | |

### Return type

[**ResponsesDeleteGeneralAttendance**](ResponsesDeleteGeneralAttendance.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllGeneralAttendances"></a>
# **getAllGeneralAttendances**
> ResponsesGetAllGeneralAttendances getAllGeneralAttendances()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
try {
    val result : ResponsesGetAllGeneralAttendances = apiInstance.getAllGeneralAttendances()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#getAllGeneralAttendances")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#getAllGeneralAttendances")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ResponsesGetAllGeneralAttendances**](ResponsesGetAllGeneralAttendances.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getGeneralAttendance"></a>
# **getGeneralAttendance**
> ResponsesGetGeneralAttendance getGeneralAttendance(generalAttendanceId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
val generalAttendanceId : kotlin.Int = 56 // kotlin.Int | general attendance id
try {
    val result : ResponsesGetGeneralAttendance = apiInstance.getGeneralAttendance(generalAttendanceId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#getGeneralAttendance")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#getGeneralAttendance")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **generalAttendanceId** | **kotlin.Int**| general attendance id | |

### Return type

[**ResponsesGetGeneralAttendance**](ResponsesGetGeneralAttendance.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="updateGeneralAttendance"></a>
# **updateGeneralAttendance**
> ResponsesUpdateGeneralAttendance updateGeneralAttendance(generalAttendanceId, body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = AttendanceApi()
val generalAttendanceId : kotlin.Int = 56 // kotlin.Int | general attendance id
val body : RequestsUpdateGeneralAttendance =  // RequestsUpdateGeneralAttendance | body
try {
    val result : ResponsesUpdateGeneralAttendance = apiInstance.updateGeneralAttendance(generalAttendanceId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AttendanceApi#updateGeneralAttendance")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AttendanceApi#updateGeneralAttendance")
    e.printStackTrace()
}
```

### Parameters
| **generalAttendanceId** | **kotlin.Int**| general attendance id | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RequestsUpdateGeneralAttendance**](RequestsUpdateGeneralAttendance.md)| body | |

### Return type

[**ResponsesUpdateGeneralAttendance**](ResponsesUpdateGeneralAttendance.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

