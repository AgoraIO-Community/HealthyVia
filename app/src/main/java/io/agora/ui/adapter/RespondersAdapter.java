package io.agora.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.agora.largegroupcall.R;
import io.agora.ui.model.Item_Friends_Talke_Model;


public class RespondersAdapter extends RecyclerView.Adapter<RespondersAdapter.ViewHolder> {

    Dialog myDialog;
    private Context context;
    private ArrayList<Item_Friends_Talke_Model> modelArrayList;

    public RespondersAdapter(Context context, ArrayList<Item_Friends_Talke_Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_responder, parent, false);
        return new ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item_Friends_Talke_Model item_friends_talke_model = modelArrayList.get(position);

        holder.item_responder_name.setText(item_friends_talke_model.getItem_friend_talke_name_text());
        holder.item_responder_image.setImageResource(item_friends_talke_model.getItem_friend_talke_image());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog = new Dialog(context);
                myDialog.getWindow();
                myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                myDialog.setCancelable(true);
                myDialog.setContentView(R.layout.friends_popup_talke);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_responder_image;
        TextView item_responder_name;

        public ViewHolder(View itemView) {
            super(itemView);

            item_responder_name = itemView.findViewById(R.id.item_responder_name);
            item_responder_image = itemView.findViewById(R.id.item_responder_image);
        }
    }
}
