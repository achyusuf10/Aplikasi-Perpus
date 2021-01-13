/**
 * Created By Achmad Yusuf
 * Github : abdullah1006
 **/
package com.example.crudmysql.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.crudmysql.AddActivity;
import com.example.crudmysql.Model.DataModel;
import com.example.crudmysql.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listUser;

    //Ini Constructor
    public AdapterData(Context ctx, List<DataModel> listUser) {
        this.ctx = ctx;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listUser.get(position);

        holder.tvId.setText(String.valueOf(dm.getId()));
        holder.tvNama.setText(dm.getNama());
        holder.tvJabatan.setText(dm.getJabatan());
        holder.tvNIM.setText(dm.getNim());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_user);
        requestOptions.error(R.drawable.ic_user);

        Glide.with(ctx)
                .load("http://192.168.42.66/crud_ci4/upload/"+dm.getImage())
                .apply(requestOptions)
                .into(holder.mPicture);
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvId,tvNama,tvNIM,tvJabatan;
        CircleImageView mPicture;
        DataModel dm;

        //Masukkan apa aja yang mau di holder/ditampilkan pada list item
        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvJabatan = itemView.findViewById(R.id.tv_jabatan);
            tvNIM = itemView.findViewById(R.id.tv_nim);
            mPicture = itemView.findViewById(R.id.picture);

            //Ketika salah satu item diklik
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goInput = new Intent(ctx, AddActivity.class);
                    goInput.putExtra("id",String.valueOf(dm.getId()));
                    goInput.putExtra("nama",dm.getNama());
                    goInput.putExtra("jenis_kelamin",dm.getJenis_kelamin());
                    goInput.putExtra("alamat",dm.getAlamat());
                    goInput.putExtra("telp",dm.getTelp());
                    goInput.putExtra("nim",dm.getNim());
                    goInput.putExtra("jabatan",dm.getJabatan());
                    goInput.putExtra("image",dm.getImage());
                    ctx.startActivity(goInput);
                }
            });
        }
    }
}

