
package ReqMicrosoftVision;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Tags {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("confidence")
    @Expose
    private String confidence;


    public String toString()
    {
        StringBuilder retStr =new StringBuilder();
        retStr.append(name)
                .append("\t Вероятность \t")
                .append(confidence+"\n");
        return retStr.toString();
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

}