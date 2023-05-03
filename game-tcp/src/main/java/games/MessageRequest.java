package games;

import java.awt.Point;

import com.google.gson.annotations.SerializedName;

public class MessageRequest {
    @SerializedName("prompt")
    private String prompt;
    @SerializedName("temperature")
    private Double temperature;
    @SerializedName("model")
    private String model = "text-davinci-003";

    public MessageRequest(Point player, Point enemy, Double temperature) {

        this.prompt = "my coord x=" + player.x + " and y=" + player.y
                + ", yours x=" + enemy.x + ",y=" + enemy.y
                + ". Where you willing to move, max 3 points in any direction, please only give your x and y, use this format: (x,y)";
        this.temperature = temperature;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}