# StudentApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apiV1AuthStudentsAccountsStudentTokenIdEjectPost**](StudentApi.md#apiV1AuthStudentsAccountsStudentTokenIdEjectPost) | **POST** /api/v1/auth/students/accounts/{student_token_id}/eject |  |
| [**apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentAccountsGet**](StudentApi.md#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentAccountsGet) | **GET** /api/v1/batches/{batch_id}/majors/{major_id}/classrooms/{classroom_id}/student-accounts |  |
| [**getAllStudentsByClassroomId**](StudentApi.md#getAllStudentsByClassroomId) | **GET** /api/v1/batches/{batch_id}/majors/{major_id}/classrooms/{classroom_id}/students |  |
| [**getProfileStudent**](StudentApi.md#getProfileStudent) | **GET** /api/v1/students/profile |  |
| [**loginStudent**](StudentApi.md#loginStudent) | **POST** /api/v1/auth/students/login |  |
| [**refreshTokenStudent**](StudentApi.md#refreshTokenStudent) | **POST** /api/v1/auth/students/refresh-token |  |


<a id="apiV1AuthStudentsAccountsStudentTokenIdEjectPost"></a>
# **apiV1AuthStudentsAccountsStudentTokenIdEjectPost**
> ResponsesEjectStudentToken apiV1AuthStudentsAccountsStudentTokenIdEjectPost(studentTokenId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = StudentApi()
val studentTokenId : kotlin.Int = 56 // kotlin.Int | student token id
try {
    val result : ResponsesEjectStudentToken = apiInstance.apiV1AuthStudentsAccountsStudentTokenIdEjectPost(studentTokenId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling StudentApi#apiV1AuthStudentsAccountsStudentTokenIdEjectPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling StudentApi#apiV1AuthStudentsAccountsStudentTokenIdEjectPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **studentTokenId** | **kotlin.Int**| student token id | |

### Return type

[**ResponsesEjectStudentToken**](ResponsesEjectStudentToken.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentAccountsGet"></a>
# **apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentAccountsGet**
> ResponsesGetAllStudentAccountsByClassroomId apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentAccountsGet(batchId, majorId, classroomId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = StudentApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val classroomId : kotlin.Int = 56 // kotlin.Int | classroom id
try {
    val result : ResponsesGetAllStudentAccountsByClassroomId = apiInstance.apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentAccountsGet(batchId, majorId, classroomId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling StudentApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentAccountsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling StudentApi#apiV1BatchesBatchIdMajorsMajorIdClassroomsClassroomIdStudentAccountsGet")
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

[**ResponsesGetAllStudentAccountsByClassroomId**](ResponsesGetAllStudentAccountsByClassroomId.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getAllStudentsByClassroomId"></a>
# **getAllStudentsByClassroomId**
> GetAllStudentsByClassroomIdRes getAllStudentsByClassroomId(batchId, majorId, classroomId)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = StudentApi()
val batchId : kotlin.Int = 56 // kotlin.Int | batch id
val majorId : kotlin.Int = 56 // kotlin.Int | major id
val classroomId : kotlin.Int = 56 // kotlin.Int | classroom id
try {
    val result : GetAllStudentsByClassroomIdRes = apiInstance.getAllStudentsByClassroomId(batchId, majorId, classroomId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling StudentApi#getAllStudentsByClassroomId")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling StudentApi#getAllStudentsByClassroomId")
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

[**GetAllStudentsByClassroomIdRes**](GetAllStudentsByClassroomIdRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="getProfileStudent"></a>
# **getProfileStudent**
> GetProfileStudentRes getProfileStudent()



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = StudentApi()
try {
    val result : GetProfileStudentRes = apiInstance.getProfileStudent()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling StudentApi#getProfileStudent")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling StudentApi#getProfileStudent")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GetProfileStudentRes**](GetProfileStudentRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="loginStudent"></a>
# **loginStudent**
> LoginStudentRes loginStudent(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = StudentApi()
val body : LoginStudentReq =  // LoginStudentReq | body
try {
    val result : LoginStudentRes = apiInstance.loginStudent(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling StudentApi#loginStudent")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling StudentApi#loginStudent")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**LoginStudentReq**](LoginStudentReq.md)| body | |

### Return type

[**LoginStudentRes**](LoginStudentRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a id="refreshTokenStudent"></a>
# **refreshTokenStudent**
> RefreshTokenStudentRes refreshTokenStudent(body)



### Example
```kotlin
// Import classes:
//import com.rizalanggoro.presensigo.openapi.infrastructure.*
//import com.rizalanggoro.presensigo.openapi.models.*

val apiInstance = StudentApi()
val body : RefreshTokenStudentReq =  // RefreshTokenStudentReq | body
try {
    val result : RefreshTokenStudentRes = apiInstance.refreshTokenStudent(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling StudentApi#refreshTokenStudent")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling StudentApi#refreshTokenStudent")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | [**RefreshTokenStudentReq**](RefreshTokenStudentReq.md)| body | |

### Return type

[**RefreshTokenStudentRes**](RefreshTokenStudentRes.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

