
package ReqMicrosoftVision;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Caption {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("confidence")
    @Expose
    private String confidence;

    public String toString()
    {
        StringBuilder retStr =new StringBuilder();
        retStr.append(text)
                .append("\n Вероятность:")
                .append(confidence+"\n");
        return retStr.toString();
    };
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

}
