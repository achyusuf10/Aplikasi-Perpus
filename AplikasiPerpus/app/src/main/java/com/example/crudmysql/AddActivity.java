/**
 * Created By Achmad Yusuf
 * Github : abdullah1006
 **/
package com.example.crudmysql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.crudmysql.API.RetroServer;
import com.example.crudmysql.Model.ResponseModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {
    private EditText etNama,etAlamat,etTelepon,etNim;
    private TextView tvID;
    private Spinner spJk,spJabatan;
    private String nama,jenisKelamin,alamat,telepon,jabatan,nim,image;
    private int iduser;
    private FloatingActionButton mFabChoosePic;
    private CircleImageView mImage;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        /*Initialize*/
        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etTelepon = findViewById(R.id.et_telp);
        spJk = findViewById(R.id.sp_jenisKelamin);
        etNim = findViewById(R.id.et_nim);
        spJabatan = findViewById(R.id.sp_jabatan);
        mImage = findViewById(R.id.picture);
        mFabChoosePic = findViewById(R.id.fabChoosePic);
        tvID=findViewById(R.id.tv_id);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.jenis_kelamin,
                R.layout.color_spinner_layout
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spJk.setAdapter(adapter);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.jabatan,
                R.layout.color_spinner_layout
        );
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spJabatan.setAdapter(adapter2);

        //Kirim data dari Main Activity
        Intent data = getIntent();
        String id = data.getStringExtra("id");
        tvID.setText(id);
        //Ketika data ada/ not null
        if(id !=null){
            etNama.setText(data.getStringExtra("nama"));
            jenisKelamin = data.getStringExtra("jenis_kelamin");
            if(jenisKelamin.equals("Laki-Laki")){
                spJk.setSelection(0);
            }else{
                spJk.setSelection(1);
            }
            etAlamat.setText(data.getStringExtra("alamat"));
            etTelepon.setText(data.getStringExtra("telp"));
            etNim.setText(data.getStringExtra("nim"));
            jabatan = data.getStringExtra("jabatan");
            if(jabatan.equals("Ketua")){ spJk.setSelection(0);}
            else if(jabatan.equals("Wakil Ketua")){ spJabatan.setSelection(1);}
            else if(jabatan.equals("Sekertaris")){ spJabatan.setSelection(2);}
            else if(jabatan.equals("Bendahara")){ spJabatan.setSelection(3);}
            else{ spJabatan.setSelection(4);}
            image = data.getStringExtra("image");
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.ic_user);
            requestOptions.error(R.drawable.ic_user);


            Glide.with(AddActivity.this)
                    .load("http://192.168.42.66/crud_ci4/upload/"+image)
                    .apply(requestOptions)
                    .into(mImage);
            iduser = Integer.parseInt(id);
        }
        mFabChoosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });
    }

    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //Function Buat Data
    public void createData(){
        Call<ResponseModel> simpanData = RetroServer.getUserAPI().ardCreateUser(
                jabatan,nama,nim,jenisKelamin,alamat,telepon
        );

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String message = response.body().getMessage();
                Toast.makeText(AddActivity.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AddActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Funcion Perbarui Data
    private void updateData(){
        Call<ResponseModel> perbaruiData = RetroServer.getUserAPI().ardUpdateUser(
                iduser,
                jabatan,
                nama,
                nim,
                jenisKelamin,
                alamat,
                telepon
        );
        perbaruiData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Toast.makeText(AddActivity.this, "Berhasil Mengupdate", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AddActivity.this, "Gagal Hubung Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Function Alert/Peringatan jika mau di hapus
    private void alertData(){
        AlertDialog.Builder dialogPesan = new AlertDialog.Builder(AddActivity.this);
        dialogPesan.setMessage("Yakin Untuk Menghapus ?");
        dialogPesan.setCancelable(true);
        //Positive Button
        dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteData();
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

    //Function Delete Data
    private void deleteData(){
        Call<ResponseModel> hapusData = RetroServer.getUserAPI().ardDeleteUser(iduser);
        hapusData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String message = response.body().getMessage();
                Toast.makeText(AddActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AddActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_add, menu);
        String id = tvID.getText().toString().trim();
        MenuItem itemDelete = menu.findItem(R.id.action_delete);
        MenuItem itemSave = menu.findItem(R.id.action_save);
        if(id.equals("")){
            itemDelete.setVisible(false);
            itemSave.setVisible(true);
        }else{
            itemDelete.setVisible(true);
            itemSave.setVisible(true);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                validateData();
                return true;
            case R.id.action_delete:
                alertData();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void validateData(){
        String idusr = tvID.getText().toString();
        nama = etNama.getText().toString();
        alamat = etAlamat.getText().toString();
        telepon = etTelepon.getText().toString();
        jenisKelamin = spJk.getSelectedItem().toString();
        nim = etNim.getText().toString();
        jabatan = spJabatan.getSelectedItem().toString();

        //Buat Peringatan jika editText gk terisi
        if(nama.trim().equals("")){
            etNama.setError("Nama harus diisi");
        }else if(alamat.trim().equals("")){
            etAlamat.setError("Alamat Harus Diisi");
        }else if(telepon.trim().equals("")){
            etTelepon.setError("No Telepon harus diisi");
        }else if(nim.trim().equals("")){
            etNim.setError("NIM harus diisi");
        }else{
            if (idusr.equals("")){
                createData();
            }else{
                updateData();
            }
        }
    }
}