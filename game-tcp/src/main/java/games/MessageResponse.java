package games;

import com.google.gson.annotations.SerializedName;

public class MessageResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("object")
    private String object;
    @SerializedName("created")
    private Long created;
    @SerializedName("model")
    private String model;
    @SerializedName("choices")
    private Choice[] choices;
    @SerializedName("usage")
    private Usage usage;

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public Long getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public Choice[] getChoices() {
        return choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public static class Choice {
        @SerializedName("text")
        private String text;
        @SerializedName("index")
        private Integer index;
        @SerializedName("logprobs")
        private Object logprobs;
        @SerializedName("finish_reason")
        private String finishReason;

        public String getText() {
            return text.replace("\n\n", "");
        }

        public Integer getIndex() {
            return index;
        }

        public Object getLogprobs() {
            return logprobs;
        }

        public String getFinishReason() {
            return finishReason;
        }
    }

    public static class Usage {
        @SerializedName("prompt_tokens")
        private Integer promptTokens;
        @SerializedName("completion_tokens")
        private Integer completionTokens;
        @SerializedName("total_tokens")
        private Integer totalTokens;

        public Integer getPromptTokens() {
            return promptTokens;
        }

        public Integer getCompletionTokens() {
            return completionTokens;
        }

        public Integer getTotalTokens() {
            return totalTokens;
        }
    }
}
