package com.rizalanggoro.presensigo.openapi.auth

interface Authentication {

    /**
     * Apply authentication settings to header and query params.
     *
     * @param query Query parameters.
     * @param headers Header parameters.
     */
    fun apply(query: MutableMap<String, List<String>>, headers: MutableMap<String, String>)

}
