package egorko.apivk;

import com.vk.sdk.VKSdk;

/**
 * Created by Brom on 04.01.2017.
 */
public class VkInit extends android.app.Application{
    @Override
    public void onCreate(){
        super.onCreate();
        VKSdk.initialize(this);
    }

}
