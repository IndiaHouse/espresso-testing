package tasting.espresso.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import tasting.espresso.R;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        TextView emailTextView = (TextView) findViewById(R.id.email);
        TextView passwordTextView = (TextView) findViewById(R.id.password);

        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            if (uri == null) {
                return;
            }
            String email = uri.getQueryParameter("email");
            String password = uri.getQueryParameter("password");
            emailTextView.setText(email);
            passwordTextView.setText(password);
        }
    }
}
