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
 * @param date 
 * @param id 
 * @param schoolId 
 */


data class DtoLateness (

    @SerializedName("date")
    val date: kotlin.String,

    @SerializedName("id")
    val id: kotlin.Int,

    @SerializedName("school_id")
    val schoolId: kotlin.Int

) {


}

