package me.gogosing.service

import me.gogosing.model.Paging
import me.gogosing.model.PostCodeItem
import me.gogosing.model.PostCodePaginationResult
import me.gogosing.model.epost.ExternalResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.net.URLEncoder

/**
 * 주소 검색 서비스 인터페이스.
 * Created by JinBum Jeong on 2020/03/02.
 */
interface PostCodeService {

    /**
     * 주소 검색.
     * @param term 검색어.
     * @param paging 페이징 정보.
     * @return 주소 검색 결과.
     */
    fun getPaginatedPostCodeList(term: String, paging: Paging): PostCodePaginationResult
}

@Service
class PostCodeServiceImpl(
    @Value("\${e-post.service-key:}")
    private val serviceKey: String,
    private val restTemplate: RestTemplate
) : PostCodeService {

    private val logger = LoggerFactory.getLogger(PostCodeServiceImpl::class.java)

    /**
     * 주소 검색 외부 API URI.
     */
    private val externalBaseURI =
        """
            http://openapi.epost.go.kr
            /postal/retrieveNewAdressAreaCdSearchAllService/retrieveNewAdressAreaCdSearchAllService
            /getNewAddressListAreaCdSearchAll
        """.trimIndent().replace("\\n".toRegex(), "")

    override fun getPaginatedPostCodeList(term: String, paging: Paging): PostCodePaginationResult {
        try {
            val externalResponse = restTemplate.getForObject(
                buildExternalURIWithQueryParam(term, paging), ExternalResponse::class.java)

            if (externalResponse != null) {
                val postCodeList = externalResponse.body?.map {
                    PostCodeItem(code = it.code, address = it.address, addressLegacy = it.addressLegacy)
                } ?: emptyList()
                return PostCodePaginationResult(
                    paging = Paging(
                        no = externalResponse.header.no,
                        limit = externalResponse.header.limit,
                        total = externalResponse.header.total),
                    list = postCodeList
                )
            }
        } catch (exception: RestClientException) {
            logger.error("외부 API의 응답이 정상이 아니거나, 결과가 null 입니다.", exception)
        }
        return PostCodePaginationResult(paging = Paging(), list = emptyList())
    }

    /**
     * 가공된 외부 서비스 URI 생성.
     * @param term 검색어.
     * @param paging 페이징 정보.
     * @return 가공된 URI.
     */
    private fun buildExternalURIWithQueryParam(term: String, paging: Paging): URI {
        return UriComponentsBuilder
            .fromUriString(externalBaseURI)
            .queryParam("ServiceKey", serviceKey)
            .queryParam("srchwrd", URLEncoder.encode(term, Charsets.UTF_8.toString()))
            .queryParam("currentPage", paging.no)
            .queryParam("countPerPage", paging.limit)
            .build(true).toUri()
    }
}

/**
 * RestTemplate Configuration.
 */
@Configuration
class RestTemplateConfiguration {

    @Bean
    fun restTemplate() = RestTemplate()
}
