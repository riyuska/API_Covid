package com.alkhairunasriyuska.api;

import com.alkhairunasriyuska.api.Model.ModelDataIndonesia;
import com.alkhairunasriyuska.api.Model.ModelDataProvinsi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("indonesia")
    Call<List<ModelDataIndonesia>> getData();

    @GET("indonesia/provinsi")
    Call<List<ModelDataProvinsi>> getProvinsi();
}
