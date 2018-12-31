package io.agora.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import io.agora.largegroupcall.R;
import io.agora.largegroupcall.model.ConstantApp;
import io.agora.largegroupcall.ui.BaseActivity;
import io.agora.largegroupcall.ui.LiveRoom17Activity;
import io.agora.largegroupcall.ui.SettingsActivity;
import io.agora.rtc.Constants;
import io.agora.ui.adapter.RespondersAdapter;
import io.agora.ui.manager.APIManager;
import io.agora.ui.model.Item_Friends_Talke_Model;


public class HomeActivity extends BaseActivity implements View.OnClickListener {

    ImageView user, comment, call, more;

    Integer[] item_responder_image = {R.drawable.man, R.drawable.girl, R.drawable.girl2, R.drawable.profile1};
    String[] item_responder_name = {"Rajvi", "Akash", "Vrusti", "Vaibhav"};

    private RecyclerView recyclerView;
    private RespondersAdapter respondersAdapter;
    private ArrayList<Item_Friends_Talke_Model> modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        user = findViewById(R.id.user);
        comment = findViewById(R.id.comment);
        call = findViewById(R.id.call);
        more = findViewById(R.id.more);

        user.setOnClickListener(this);
        comment.setOnClickListener(this);
        call.setOnClickListener(this);
        more.setOnClickListener(this);

        findViewById(R.id.add_responder).setOnClickListener(this);
        findViewById(R.id.add_responder_ic).setOnClickListener(this);

        findViewById(R.id.with_responders).setOnClickListener(this);
        findViewById(R.id.without_responder).setOnClickListener(this);
        findViewById(R.id.only_responder).setOnClickListener(this);

        recyclerView = findViewById(R.id.recycle_responder);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        modelArrayList = new ArrayList<>();

        for (int i = 0; i < item_responder_image.length; i++) {
            Item_Friends_Talke_Model item_responder_model = new Item_Friends_Talke_Model(item_responder_image[i], item_responder_name[i]);
            modelArrayList.add(item_responder_model);
        }
        respondersAdapter = new RespondersAdapter(HomeActivity.this, modelArrayList);
        recyclerView.setAdapter(respondersAdapter);
    }

    @Override
    protected void initUIandEvent() {

    }

    @Override
    protected void deInitUIandEvent() {

    }

    public void forwardToLiveRoom(int cRole, String roomName) {
        Intent i = new Intent(HomeActivity.this, LiveRoom17Activity.class);
        i.putExtra(ConstantApp.ACTION_KEY_CROLE, cRole);
        i.putExtra(ConstantApp.ACTION_KEY_ROOM_NAME, roomName);
        startActivity(i);
    }

    public void forwardToSettings() {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_responder:
            case R.id.add_responder_ic:
                final Dialog myDialog;
                myDialog = new Dialog(HomeActivity.this);
                myDialog.getWindow();
                myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                myDialog.setCancelable(true);
                myDialog.setContentView(R.layout.add_responders);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();

                final EditText responderEmail = myDialog.findViewById(R.id.responder_email);
                myDialog.findViewById(R.id.add_responder_email).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        List<String> responderEmailList = new ArrayList<>();
//                        responderEmailList.add(responderEmail.getText().toString());
                        APIManager.getInstance().addResponder(responderEmail.getText().toString());
                        Toast.makeText(HomeActivity.this, "Added Responder to your My Responders List.",
                                Toast.LENGTH_LONG).show();
                        myDialog.dismiss();
                    }
                });
                break;
            case R.id.with_responders:
                HomeActivity.this.forwardToLiveRoom(Constants.CLIENT_ROLE_BROADCASTER, "EMT + My Responders");
                APIManager.getInstance().sendPush("EMT + My Responders");
                break;
            case R.id.without_responder:
                HomeActivity.this.forwardToLiveRoom(Constants.CLIENT_ROLE_BROADCASTER, "Only EMT");
                APIManager.getInstance().sendPush("Only EMT");
                break;
            case R.id.only_responder:
                HomeActivity.this.forwardToLiveRoom(Constants.CLIENT_ROLE_BROADCASTER, "Only Responders");
                APIManager.getInstance().sendPush("Only Responders");
                break;
            case R.id.user:
                user.setImageResource(R.drawable.siren_1);
                comment.setImageResource(R.drawable.records);
                call.setImageResource(R.drawable.call_1);
                more.setImageResource(R.drawable.more_1);
                break;
            case R.id.comment:
                user.setImageResource(R.drawable.siren);
                comment.setImageResource(R.drawable.records_1);
                call.setImageResource(R.drawable.call_1);
                more.setImageResource(R.drawable.more_1);
                break;
            case R.id.call:
                user.setImageResource(R.drawable.siren);
                comment.setImageResource(R.drawable.records);
                call.setImageResource(R.drawable.call_2);
                more.setImageResource(R.drawable.more_1);
                break;
            case R.id.more:
                user.setImageResource(R.drawable.siren);
                comment.setImageResource(R.drawable.records);
                call.setImageResource(R.drawable.call_1);
                more.setImageResource(R.drawable.more_2);
                break;
        }
    }
}
