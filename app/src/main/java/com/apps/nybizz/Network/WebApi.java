package com.apps.nybizz.Network;

import com.apps.nybizz.Response.AboutUsRespponse;
import com.apps.nybizz.Response.CommonResponse;
import com.apps.nybizz.Response.GetAllVideoResponbse;
import com.apps.nybizz.Response.HomeResponse;
import com.apps.nybizz.Response.LoginResponse;
import com.apps.nybizz.Response.ProductDetailsresponse;
import com.apps.nybizz.Response.ProfileResponse;
import com.apps.nybizz.Response.RegistrationResponse;
import com.apps.nybizz.Response.VendorDetailsResponse;
import com.apps.nybizz.VideosResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebApi {

    @POST("/video/getAll")
    Call<VideosResponse> getAllVideos(@HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST("/api/customer/register")
    Call<RegistrationResponse> register(@Field("email") String email,
                                        @Field("phone") String phone,
                                        @Field("password") String password,
                                        @Field("password_confirmation") String password_confirmation);

    @FormUrlEncoded
    @POST("/api/customer/login")
    Call<LoginResponse> login(@Field("email") String email,
                              @Field("password") String password);


    @POST("/api/customer/logout")
    Call<RegistrationResponse> logout();


    @GET("/api/customer/profile")
    Call<ProfileResponse> profile();

    @FormUrlEncoded
    @POST("/api/customer/profile")
    Call<RegistrationResponse> profile(@Field("name") String name,
                                       @Field("email") String email,
                                        @Field("phone") String phone,
                                        @Field("gender") String gender,
                                       @Field("dob") String dob);

    @GET("/api/customer/about_us")
    Call<AboutUsRespponse> about_us();

    @GET("/api/customer/privacy_policy")
    Call<AboutUsRespponse> privacy_policy();

    @GET("/api/customer/terms_and_conditions")
    Call<AboutUsRespponse> terms_and_conditions();

    @GET("/api/customer/product/list")
    Call<GetAllVideoResponbse> getproductlist();

    @GET("/api/customer/product/")
    Call<ProductDetailsresponse> getproductDetails(@Query("id") String id);


    @GET("/api/customer/vendor/")
    Call<VendorDetailsResponse> getVendorDetails(@Query("id") String id);

    @FormUrlEncoded
    @POST("/api/customer/cart/product/")
    Call<CommonResponse> getCommonResponse(@Field("quantity") String quantity,
                                           @Field("product_id") String product_id,
                                           @Field("vendor_id") String vendor_id
                                           );

    @GET("/api/customer/home")
    Call<HomeResponse> gethome();


}
