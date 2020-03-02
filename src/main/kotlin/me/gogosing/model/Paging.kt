package io.crsdevbros.integration.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 페이징 모델.
 */
data class Paging(

    /**
      * 페이지 번호.
      */
    @JsonProperty("no")
    val no: Int = 1,

    /**
     * 페이지 당 노출 데이터 수.
     */
    @JsonProperty("limit")
    val limit: Int = 10,

    /**
     * 전체 항목 수.
     */
    @JsonProperty("total")
    val total: Int = 0
)
