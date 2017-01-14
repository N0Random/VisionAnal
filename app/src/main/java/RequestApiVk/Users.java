
package RequestApiVk;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("response")
    @Expose
    private List<UserResponse> userResponse = null;

    public List<UserResponse> getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(List<UserResponse> userResponse) {
        this.userResponse = userResponse;
    }

}
