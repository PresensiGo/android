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

package com.rizalanggoro.presensigo.openapi.models


import com.google.gson.annotations.SerializedName

/**
 * 
 *
 * @param classroomId 
 * @param date 
 * @param id 
 */


data class DtoAttendance (

    @SerializedName("classroom_id")
    val classroomId: kotlin.Int,

    @SerializedName("date")
    val date: kotlin.String,

    @SerializedName("id")
    val id: kotlin.Int

) {


}

