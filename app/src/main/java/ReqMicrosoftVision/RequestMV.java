package ReqMicrosoftVision;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Brom on 06.01.2017.
 */
public interface RequestMV {
    @POST("vision/v1.0/analyze")
    Call<ResponseMV> Analise(@HeaderMap Map<String, String> hedParam, @Body JsonObject urlSrt, @QueryMap Map<String, String> parameters);
}
