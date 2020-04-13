package me.gogosing.model

/**
 * 주소 검색 payload.
 */
data class PostCodeRequest(

    /**
     * 검색어.
     */
    val term: String,

    /**
     * 페이징 정보.
     */
    val paging: Paging
)
