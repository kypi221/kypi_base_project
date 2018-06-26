package com.kypi.demoproject.data.remote;

import com.kypi.demoproject.data.remote.response.IReadListBookDemoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface IReadDemoApiService {

    @GET("/api/chapter/content")
    Observable<IReadListBookDemoResponse> getChapterContent(@Header("Authorization")String basicAuthenIRead,
                                                            @Query("chapterId") int chapterID);

    // Get Ranking
    @GET("/api/book/getBXH")
    Observable<IReadListBookDemoResponse> getRankingBooks(@Header("Authorization")String basicAuthenIRead,
                                                    @Query("page")int page,
                                                    @Query("size")int size);
}
