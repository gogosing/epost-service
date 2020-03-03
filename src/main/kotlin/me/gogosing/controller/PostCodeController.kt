package me.gogosing.controller

import io.crsdevbros.integration.model.PostCodePaginationResult
import io.crsdevbros.integration.model.PostCodeRequest
import me.gogosing.service.PostCodeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 주소 검색 Controller.
 * Created by JinBum Jeong on 2020/03/02.
 */
@RestController
@RequestMapping("/v1")
class PostCodeController(
    private val postCodeService: PostCodeService
) {

    /**
     * 주소 검색.
     * @param pagingRequest 검색 요청 payload.
     * @return 주소 검색 결과.
     */
    @PostMapping("/post")
    fun postCode(@RequestBody pagingRequest: PostCodeRequest): PostCodePaginationResult {
        val filterTerm = pagingRequest.term
        if (filterTerm.isBlank()) {
            return PostCodePaginationResult(paging = pagingRequest.paging, list = emptyList())
        }
        return postCodeService.getPaginatedPostCodeList(filterTerm, pagingRequest.paging)
    }
}
