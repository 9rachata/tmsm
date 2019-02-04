package app.coe.com.tmsm.api;

import java.util.List;

import app.coe.com.tmsm.models.AllThings;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface FeedData {


    @GET("/api/resultESP.php")
    Call <List<AllThings>> getUser();
}


