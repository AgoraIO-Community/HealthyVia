package io.agora.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import io.agora.largegroupcall.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView user, comment, call, more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        user = findViewById(R.id.user);
        comment = findViewById(R.id.comment);
        call = findViewById(R.id.call);
        more = findViewById(R.id.more);

        user.setOnClickListener(this);
        comment.setOnClickListener(this);
        call.setOnClickListener(this);
        more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user:
                user.setImageResource(R.drawable.friends_2);
                comment.setImageResource(R.drawable.chat_1);
                call.setImageResource(R.drawable.call_1);
                more.setImageResource(R.drawable.more_1);
                break;
            case R.id.comment:
                user.setImageResource(R.drawable.friends_1);
                comment.setImageResource(R.drawable.chat_2);
                call.setImageResource(R.drawable.call_1);
                more.setImageResource(R.drawable.more_1);
                break;
            case R.id.call:
                user.setImageResource(R.drawable.friends_1);
                comment.setImageResource(R.drawable.chat_1);
                call.setImageResource(R.drawable.call_2);
                more.setImageResource(R.drawable.more_1);
                break;
            case R.id.more:
                user.setImageResource(R.drawable.friends_1);
                comment.setImageResource(R.drawable.chat_1);
                call.setImageResource(R.drawable.call_1);
                more.setImageResource(R.drawable.more_2);
                break;
        }
    }
}
