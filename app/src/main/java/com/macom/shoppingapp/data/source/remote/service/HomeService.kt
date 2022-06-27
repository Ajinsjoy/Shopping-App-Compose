package com.macom.shoppingapp.data.source.remote.service

import com.macom.shoppingapp.data.source.remote.dto.HomeDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface HomeService {
    @GET("v3/69ad3ec2-f663-453c-868b-513402e515f0")
    suspend fun getHomeScreenItems(): ApiResponse<HomeDto>
}