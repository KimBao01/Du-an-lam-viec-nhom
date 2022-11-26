package com.example.project_english_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Second extends AppCompatActivity {
    private Button btn;
    ImageView view;
    TextView name,id,email;
    Button signOut;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_playagain_corect);
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        view = (ImageView) findViewById(R.id.view);
//        name = (TextView) findViewById(R.id.name);
//        email = (TextView)findViewById(R.id.email);
//        id = (TextView) findViewById(R.id.id);
//        signOut = (Button)findViewById(R.id.signout);
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if (acct != null) {
//            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
//            name.setText(personName);
//            id.setText(personId);
//            email.setText(personEmail);

//        }
        btn = (Button) findViewById(R.id.btn_PlayAgain_Correct);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gobackActivity_correct_home();
            }
        });

    }

    public void gobackActivity_correct_home() {
        Intent intent = new Intent(this, Activity_correct_home.class);
        startActivity(intent);
    }
}
