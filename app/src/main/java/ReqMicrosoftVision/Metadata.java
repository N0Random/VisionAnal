
package ReqMicrosoftVision;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("format")
    @Expose
    private String format;
    public String toString()
    {
        StringBuilder retStr =new StringBuilder();
        retStr.append("Ширина:")
                .append(width)
                .append("\t Высота:")
                .append(height)
                .append("\t Формат:")
                .append(format);
        return retStr.toString();
    };
    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
