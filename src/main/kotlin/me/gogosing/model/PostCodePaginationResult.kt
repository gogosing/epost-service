package io.crsdevbros.integration.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 주소 검색 결과 모델.
 */
data class PostCodePaginationResult(

    /**
     * 페이징 정보.
     */
    @JsonProperty("paging")
    val paging: Paging,

    /**
     * 주소 목록.
     */
    @JsonProperty("list")
    val list: List<PostCodeItem>
)

/**
 * 주소 정보 모델.
 */
data class PostCodeItem(

    /**
     * 우편번호(5자리).
     */
    @JsonProperty("code")
    val code: String,

    /**
     * 도로명주소.
     */
    @JsonProperty("address")
    val address: String,

    /**
     * 지번주소.
     */
    @JsonProperty("addressLegacy")
    val addressLegacy: String
)
