
package ReqMicrosoftVision;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMV {

    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("requestId")
    @Expose
    private String requestId;
    @SerializedName("tags")
    @Expose
    private List<Tags> tags = null;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    public String toString() {
        StringBuilder retStr = new StringBuilder();

        retStr.append("Теги:\n");
        for (Tags iter : tags)
            retStr.append(iter.toString());

        retStr.append(description.getTags()+"\n")
                .append("--------------------------\n")
                .append("Возможно на картинке:\n");

        for (Caption iter : description.getCaptions())
            retStr.append(iter.toString());


        retStr .append("--------------------------\n")
               .append("Метаданные:\n")
               .append(metadata.toString());
        return retStr.toString();
    }


    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
