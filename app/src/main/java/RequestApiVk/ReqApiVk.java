package RequestApiVk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import com.vk.sdk.VKAccessToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import egorko.apivk.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Brom on 05.01.2017.
 */
public class ReqApiVk  {
    private Context mAppContext;
    public   ReqApiVk(Context con)
    {
        mAppContext=con;
    }
    public Map<String,String> CreateParamGetUser(String UserId, VKAccessToken accessToken)
    {
        Map<String,String> ret=new HashMap<String,String>();
        ret.put(mAppContext.getString(R.string.user_ids),UserId);
        ret.put(mAppContext.getString(R.string.access_token),accessToken.accessToken);
        ret.put(mAppContext.getString(R.string.version), String.valueOf(5.60));
        return ret;
    }
    public Map<String,String> CreateParamGetPhoto(int UserId, VKAccessToken accessToken,int offset,int count)
    {
        Map<String,String> ret=new HashMap<String,String>();
        ret.put(mAppContext.getString(R.string.owner_id), String.valueOf(UserId));
        ret.put(mAppContext.getString(R.string.offset),String.valueOf(offset));
        ret.put(mAppContext.getString(R.string.count),String.valueOf(count));
        ret.put(mAppContext.getString(R.string.photo_sizes), String.valueOf(0));
        ret.put(mAppContext.getString(R.string.no_service_albums), String.valueOf(0));
        ret.put(mAppContext.getString(R.string.need_hidden), String.valueOf(0));
        ret.put(mAppContext.getString(R.string.skip_hidden), String.valueOf(0));
        ret.put(mAppContext.getString(R.string.access_token),accessToken.accessToken);
        ret.put(mAppContext.getString(R.string.version), String.valueOf(5.60));
        return ret;
    }
    public Photo getPhoto(String UserId,VKAccessToken accessToken) {
        Retrofit restAdapter=new Retrofit.Builder()
                .baseUrl(mAppContext.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInt service=restAdapter.create(RequestInt.class);
        Call<Users> gUsr=service.getUserId(CreateParamGetUser(UserId,accessToken));
        Photo userPhoto=new Photo();
        try{
            Response<Users> respUser=gUsr.execute();
            if(respUser.isSuccessful()) {
                Users user = respUser.body();
                if(user==null)
                    throw new NullPointerException("Wrong id person");
                Call<Photo> gPhoto = service.getAllPhoto(CreateParamGetPhoto(user.getUserResponse().get(0).getId(), accessToken, 0,200));
                Response<Photo> respPhoto = gPhoto.execute();
                if(respPhoto.isSuccessful()){
                userPhoto = respPhoto.body();
                    if(userPhoto.getResponse().getItems().size()==0)
                        return null;
                    int n=userPhoto.getResponse().getCount();
                    int offset=200;
                while(offset<n){
                    gPhoto = service.getAllPhoto(CreateParamGetPhoto(user.getUserResponse().get(0).getId(), accessToken, offset,200));
                    respPhoto = gPhoto.execute();
                    userPhoto.getResponse().getItems().addAll(respPhoto.body().getResponse().getItems().subList(0,respPhoto.body().getResponse().getItems().size()));
                    offset+=200;

                }

                }
            }
            else  throw new NullPointerException("Wrong id person");
        }catch (IOException e)  {
            e.printStackTrace();
        }
        catch (NullPointerException npe)
        {

            npe.printStackTrace();
            return null;
        }
        return userPhoto;
    };
}
