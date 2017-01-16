
package RequestApiVk;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("response")
    @Expose
    private PhotoResp response;

    public PhotoResp getResponse() {
        return response;
    }

    public void setResponse(PhotoResp response) {
        this.response = response;
    }

}
