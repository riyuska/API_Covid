package com.alkhairunasriyuska.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.alkhairunasriyuska.api.Adapter.ProvinsiAdapter;
import com.alkhairunasriyuska.api.Model.ModelDataIndonesia;
import com.alkhairunasriyuska.api.Model.ModelDataProvinsi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tnegara, tsembuh, tpositif, tmeninggal;
    RecyclerView list;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tnegara = findViewById(R.id.tnegara);
        tsembuh = findViewById(R.id.tsembuh);
        tpositif = findViewById(R.id.tpositif);
        tmeninggal = findViewById(R.id.tmeninggal);
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        dialog= new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();
        getData();
        getProvinsi();

    }

    public void getData(){
        Call<List<ModelDataIndonesia>> call = Api.service().getData();
        call.enqueue(new Callback<List<ModelDataIndonesia>>() {
            @Override
            public void onResponse(Call<List<ModelDataIndonesia>> call, Response<List<ModelDataIndonesia>> response) {
                tnegara.setText(response.body().get(0).getName());
                tsembuh.setText(response.body().get(0).getSembuh());
                tpositif.setText(response.body().get(0).getPositif());
                tmeninggal.setText(response.body().get(0).getMeninggal());

            }

            @Override
            public void onFailure(Call<List<ModelDataIndonesia>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getProvinsi(){
        Call<List<ModelDataProvinsi>> call = Api.service().getProvinsi();
        call.enqueue(new Callback<List<ModelDataProvinsi>>() {
            @Override
            public void onResponse(Call<List<ModelDataProvinsi>> call, Response<List<ModelDataProvinsi>> response) {
                list.setAdapter(new ProvinsiAdapter(MainActivity.this,response.body()));
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<ModelDataProvinsi>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }
}