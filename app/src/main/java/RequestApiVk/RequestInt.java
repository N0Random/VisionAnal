package RequestApiVk;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Brom on 05.01.2017.
 */
public interface RequestInt {
    @GET("method/users.get")
    Call<Users> getUserId(@QueryMap Map<String, String> parameters);
    @GET("method/photos.getAll")
    Call<Photo> getAllPhoto(@QueryMap Map<String, String> parameters);

}
