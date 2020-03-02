package me.gogosing.model.epost

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

/**
 * 우체국 API 주소 검색 응답 모델.
 */
@JacksonXmlRootElement(localName = "NewAddressListResponse")
data class ExternalResponse(

    /**
     * 응답 header.
     */
    @JacksonXmlProperty(localName = "cmmMsgHeader")
    val header: ExternalResponseHeader,

    /**
     * 응답 body.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "newAddressListAreaCdSearchAll")
    val body: List<ExternalResponseBody>?
)

/**
 * 우체국 API 주소 검색 응답 header 모델.
 */
data class ExternalResponseHeader(

    /**
     * 검색된 전체 데이터 개수.
     */
    @JacksonXmlProperty(localName = "totalCount")
    val total: Int = 0,

    /**
     * 페이지당 출력될 개수.
     */
    @JacksonXmlProperty(localName = "countPerPage")
    val limit: Int = 10,

    /**
     * 현재 페이지 번호.
     */
    @JacksonXmlProperty(localName = "currentPage")
    val no: Int = 1
)

/**
 * 우체국 API 주소 검색 응답 body 모델.
 */
data class ExternalResponseBody(

    /**
     * 우편번호(5자리).
     */
    @JacksonXmlProperty(localName = "zipNo")
    val code: String,

    /**
     * 도로명주소.
     */
    @JacksonXmlProperty(localName = "lnmAdres")
    val address: String,

    /**
     * 지번주소.
     */
    @JacksonXmlProperty(localName = "rnAdres")
    val addressLegacy: String
)
