package ru.polosatuk.mycard.network.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.polosatuk.mycard.network.dto.NewsDTO;

public interface NytApi {
    @GET("{section}.json")
    Single<NewsDTO> getNewsInSection(@Path("section")String section);
}
