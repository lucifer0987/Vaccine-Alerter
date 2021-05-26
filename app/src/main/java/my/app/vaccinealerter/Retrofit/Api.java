package my.app.vaccinealerter.Retrofit;

import my.app.vaccinealerter.Models.getDistrict;
import my.app.vaccinealerter.Models.getResults;
import my.app.vaccinealerter.Models.getState;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface Api {
    String BASE_URL = "https://cdn-api.co-vin.in/api/v2/";

    @GET("admin/location/states")
    Call<getState> getState(@Header("User-Agent") String token);

    @GET
    Call<getDistrict> getDistrict(@Url String url, @Header("User-Agent") String token);

    @GET
    Call<getResults> getResults(@Url String url, @Header("User-Agent") String token);

    /*
    @POST("users/login")
    Call<LoginUserGet> loginUser(@Body LoginUserPost loginUserPost);

    @GET("users/me")
    Call<UserInfoGet> getUserInfo(@Header("x-auth-token") String token);

    @POST("users")
    Call<UserInfoGet> signUpUser(@Body SignUpPost signUpPost);

    @GET("card/me")
    Call<Card> getUserCard(@Header("x-auth-token") String token);

    @PUT("card/me")
    Call<Card> putUserData(@Header("x-auth-token") String token, @Body Card post);
     */

}
