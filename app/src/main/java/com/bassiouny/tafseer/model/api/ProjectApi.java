package com.bassiouny.tafseer.model.api;


import com.bassiouny.tafseer.model.model.Aya;
import com.bassiouny.tafseer.model.model.AyaList;
import com.bassiouny.tafseer.model.model.Tafseer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProjectApi {


    @GET("surah/{id}")
    Call<BaseResponse<AyaList>> getSuraDetails(@Path("id") String index);

    @GET("ayah/{sura_id}:{aya_id}")
    Call<BaseResponse<Aya>> getAyaInfo(@Path("sura_id") String suraId,@Path("aya_id") String ayaId);


    @GET("http://api.quran-tafseer.com/tafseer/{tafseer_id}/{sura_id}/{aya_id}")
    Call<Tafseer> getTafseer(@Path("tafseer_id") String tafseerId, @Path("sura_id") String suraId, @Path("aya_id") String ayaId);



}
