package ReqMicrosoftVision;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import egorko.apivk.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Brom on 06.01.2017.
 */
public class SendRequest {
    Context mAppContext;
    public   SendRequest(Context con)
    {
        mAppContext=con;
    }
    private Map<String,String> CreateHeader(){
        Map<String,String> ret=new HashMap<String,String>();
        ret.put(mAppContext.getString(R.string.ContentType),mAppContext.getString(R.string.ContentType_value));
        ret.put(mAppContext.getString(R.string.ApimKey),mAppContext.getString(R.string.ApimKey_value));
        return ret;
    };
    private JsonObject CreateBody(String UrlStr){
        JsonObject ret = new JsonObject();
        ret.addProperty("url",UrlStr);
        return ret;
    };
    private  Map<String,String> CreateQuery(){
        Map<String,String> ret=new HashMap<String,String>();
        ret.put(mAppContext.getString(R.string.visualFeatures),mAppContext.getString(R.string.visualFeatures_value)+","+mAppContext.getString(R.string.visualFeaturesTags_value));
        ret.put(mAppContext.getString(R.string.language),mAppContext.getString(R.string.language_value));
        return ret;
    };
public ResponseMV Send(String UrlImg)
{
    ResponseMV ret =new ResponseMV();
    Map<String,String> Head=CreateHeader();
    JsonObject body=CreateBody(UrlImg);
    Map<String,String> Query=CreateQuery();
    Retrofit restAdapter=new Retrofit.Builder()
            .baseUrl(mAppContext.getString(R.string.base_url_MV))
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RequestMV service=restAdapter.create(RequestMV.class);
    Call<ResponseMV> call=service.Analise(Head,body,Query);
    try {
        Response<ResponseMV> resp= call.execute();
        ret=resp.body();

    } catch (IOException e) {
        e.printStackTrace();
    }



    return ret;
}
}
