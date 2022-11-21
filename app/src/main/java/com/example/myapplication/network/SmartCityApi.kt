package com.example.myapplication.network

import com.example.myapplication.SmartCityApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface SmartCityApi {
    @POST("/prod-api/api/login")
    fun postLogin(@Body user:Login): Call<Message>

    @POST("/prod-api/api/register")
    fun postRegister(@Body user:Register): Call<Message>

    @GET("/prod-api/api/rotation/list")
    fun getHomeBanner():Call<HomeBanner>

    @GET("/prod-api/api/service/list")
    fun getHomeService():Call<HomeService>

    @GET("/prod-api/press/category/list")
    fun getNewsClass():Call<NewsClass>

    @GET("/prod-api/press/press/list")
    fun getHot(@Query("hot")hot:String):Call<NewsList>

    @GET("/prod-api/press/press/list")
    fun getNewsList(@Query("title")title:String?,@Query("type")type:String?):Call<NewsList>

    @GET("/prod-api/press/press/{id}")
    fun getNewContent(@Path("id")id:Int):Call<NewContent>

    @GET("/prod-api/api/hospital/banner/list")
    fun getHospBanner(@Query("id")id:Int):Call<HospBanner>

    @GET("/prod-api/api/hospital/hospital/list")
    fun getHospList(@Query("hospitalName")hospitalName:String?):Call<HospList>

    @GET("/prod-api/api/hospital/hospital/{id}")
    fun getHospContent(@Path("id")id:Int):Call<HospContent>

    @GET("/prod-api/api/common/user/getInfo")
    fun getUserInfo(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<UserInfo>

    @GET("/prod-api/api/hospital/patient/list")
    fun getHospUSer(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<HospUser>

    @POST("/prod-api/api/hospital/patient")
    fun postAdd(@Body user:PostAdd,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @GET("/prod-api/api/hospital/category/list")
    fun getCate(@Query("type")type:String):Call<Cate>

    @POST("/prod-api/api/hospital")
    fun postHospApi(@Body user:HospApi,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @GET("/prod-api/api/hospital/reservation/list")
    fun getYY(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<YY>

    @GET("/prod-api/api/garbage-classification/ad-banner/list")
    fun getAdBanner(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<AdBanner>

    @GET("/prod-api/api/garbage-classification/news-type/list")
    fun getNewsType(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<NewsType>

    @GET("/prod-api/api/garbage-classification/news/list")
    fun GarNewsList(@Query("type")type:Int,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<GarNewsList>

    @GET("/prod-api/api/garbage-classification/news/{id}")
    fun getGarNewsContent(@Path("id")id: Int,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<GarNewsContent>

    @GET("/prod-api/api/garbage-classification/news-comment/list")
    fun getGarComment(@Query("newsId")newsId:Int,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<GarComment>

    @POST("/prod-api/api/garbage-classification/news-comment")
    fun postComment(@Body user:ReleaseComment,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @GET("/prod-api/api/garbage-classification/garbage-classification/list")
    fun getGarLjType(@Query("Name")Name:String?,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<GarLjType>

    @GET("/prod-api/api/garbage-classification/poster/list")
    fun getLjBanner(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<GarLjBanner>

    @GET("/prod-api/api/garbage-classification/garbage-classification/hot/list")
    fun getHotTitle(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<HotTitle>

}