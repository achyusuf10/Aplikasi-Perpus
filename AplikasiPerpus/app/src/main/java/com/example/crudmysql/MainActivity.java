/**
* Created By Achmad Yusuf
* Github : abdullah1006
**/
package com.example.crudmysql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crudmysql.API.RetroServer;
import com.example.crudmysql.Adapter.AdapterData;
import com.example.crudmysql.Model.DataModel;
import com.example.crudmysql.Model.ResponseModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;
    private FloatingActionButton fabTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srlData = findViewById(R.id.srl_data);
        pbData = findViewById(R.id.pb_data);
        fabTambah = findViewById(R.id.fab_tambah);

        rvData = findViewById(R.id.rv_data);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        getData();

        //Ketika Di refresh
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                getData();
                srlData.setRefreshing(false);
            }
        });

        //Ketika FAB ditekan
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    public void getData(){
        pbData.setVisibility(View.VISIBLE);
        Call<ResponseModel> tampilData = RetroServer.getUserAPI().ardGetUser();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                //Log.e("Succes",response.body().getMessage());
                listData = response.body().getData();
                adData = new AdapterData(MainActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
                pbData.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Hubung Server", Toast.LENGTH_SHORT).show();
                //Log.e("Failure",t.getLocalizedMessage());
                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                LogOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void LogOut() {
        AlertDialog.Builder dialogPesan = new AlertDialog.Builder(MainActivity.this);
        dialogPesan.setMessage("Yakin untuk Keluar ?");
        dialogPesan.setCancelable(true);
        //Positive Button
        dialogPesan.setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                dialogInterface.dismiss();
                finish();
            }
        });
        //Negative Button
        dialogPesan.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialogPesan.show();
    }

}