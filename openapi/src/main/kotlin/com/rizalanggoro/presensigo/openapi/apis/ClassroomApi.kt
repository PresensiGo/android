/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.rizalanggoro.presensigo.openapi.apis

import com.rizalanggoro.presensigo.openapi.models.ResponsesGetAllClassroomWithMajors

import com.rizalanggoro.presensigo.openapi.infrastructure.*
import io.ktor.client.HttpClientConfig
import io.ktor.client.request.forms.formData
import io.ktor.client.engine.HttpClientEngine
import io.ktor.http.ParametersBuilder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.text.DateFormat

    open class ClassroomApi(
    baseUrl: String = ApiClient.BASE_URL,
    httpClientEngine: HttpClientEngine? = null,
    httpClientConfig: ((HttpClientConfig<*>) -> Unit)? = null,
    jsonBlock: GsonBuilder.() -> Unit = ApiClient.JSON_DEFAULT,
    ) : ApiClient(
        baseUrl,
        httpClientEngine,
        httpClientConfig,
        jsonBlock,
    ) {

        /**
        * GET /api/v1/classrooms/batches/{batch_id}
        * 
        * 
         * @param batchId Batch Id 
         * @return ResponsesGetAllClassroomWithMajors
        */
            @Suppress("UNCHECKED_CAST")
        open suspend fun getAllClassroomWithMajors(batchId: kotlin.Int): HttpResponse<ResponsesGetAllClassroomWithMajors> {

            val localVariableAuthNames = listOf<String>()

            val localVariableBody = 
                    io.ktor.client.utils.EmptyContent

            val localVariableQuery = mutableMapOf<String, List<String>>()

            val localVariableHeaders = mutableMapOf<String, String>()

            val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.GET,
            "/api/v1/classrooms/batches/{batch_id}".replace("{" + "batch_id" + "}", "$batchId"),
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = false,
            )

            return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
            ).wrap()
            }

        }
