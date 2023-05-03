package games;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChatGPTService {

    @POST("v1/completions")
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer "
    })
    Call<MessageResponse> sendMessage(@Body MessageRequest messageRequest);
}