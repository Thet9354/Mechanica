package com.example.mechanica.Onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mechanica.HomePage_Activity;
import com.example.mechanica.MainActivity;
import com.example.mechanica.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration_Activity extends AppCompatActivity {

    private TextView txtView_logIn;
    private EditText editTxt_name, editTxt_email, editTxt_password, editTxt_passwordConfirmation;
    private ImageView btn_google, btn_instagram, btn_facebook, imgView_learnLoop;
    private Button btn_getStarted;

    //String to store input data
    private String mName, mEmail, mPassword, mConfirmPassword;

    //Google sign in variables
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    //Facebook sign in variables
    CallbackManager callbackManager;
    AccessToken accessToken;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://mechanica-1674603366861-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference databaseReference  = database.getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getApplicationContext(), gso);

        callbackManager = CallbackManager.Factory.create();

        accessToken = AccessToken.getCurrentAccessToken();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });


        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        btn_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mName = editTxt_name.getText().toString();
                mEmail = editTxt_email.getText().toString();
                mPassword = editTxt_password.getText().toString();
                mConfirmPassword = editTxt_passwordConfirmation.getText().toString();

                validateName();
                validateEmail();
                validatePassword();
                validateConfirmPassword();
                validateInputs();
            }
        });

        txtView_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignInPage_Activity.class));
            }
        });

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();

            }
        });

        btn_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration_Activity.this, HomePage_Activity.class);
                startActivity(intent);
            }
        });

        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginManager.getInstance().logInWithReadPermissions(Registration_Activity.this, Arrays.asList("email"));


            }
        });

        imgView_learnLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    private void validateInputs() {

        if (!validateName() | !validateEmail() | !validatePassword() | !validateConfirmPassword())
        {
            return;
        }
        else
        {

            addData();

        }
    }

    private void addData() {

        //Adding data into google realtime database
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Checking if phone number is not registered before

                if (snapshot.hasChild(mName))
                {
                    //--->Asking user if he/she wants ti log in to existing account
                    AlertDialog.Builder builder = new AlertDialog.Builder(Registration_Activity.this);
                    builder.setTitle("Mechanica");
                    builder.setMessage("Hey there, it seems like there's an existing account with the same name.");
                    builder.setNegativeButton("Register a new account", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), Registration_Activity.class));
                        }
                    });
                    builder.setPositiveButton("Log in to existing account", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), SignInPage_Activity.class));
                        }
                    });
                    builder.create().show();
                }
                else
                {
                    //--->Adding user's personal information to firebase
                    databaseReference.child(mName).child("User's Information").child("Full Name").setValue(mName);
                    databaseReference.child(mName).child("User's Information").child("Email").setValue(mEmail);
                    databaseReference.child(mName).child("User's Information").child("Password").setValue(mPassword);

                    //--->Adding user's setting to firebase realtime database
                    databaseReference.child(mName).child("User's Information").child("User's Settings").child("Night Mode").setValue("ON");
                    databaseReference.child(mName).child("User's Information").child("User's Settings").child("Notifications").setValue("OFF");
                    databaseReference.child(mName).child("User's Information").child("User's Settings").child("Private Account").setValue("OFF");

                    //--->Security and Privacy
                    databaseReference.child(mName).child("User's Information").child("User's Settings").child("Security and Privacy").child("Personalized Ads").setValue("ON");
                    databaseReference.child(mName).child("User's Information").child("User's Settings").child("Security and Privacy").child("Information Sharing").setValue("ON");
                    databaseReference.child(mName).child("User's Information").child("User's Settings").child("Security and Privacy").child("Personalized Place").setValue("ON");
                    databaseReference.child(mName).child("User's Information").child("User's Settings").child("Security and Privacy").child("Precise Location").setValue("OFF");

                    //--->TextSize
                    databaseReference.child(mName).child("User's Information").child("User's Settings").child("Text Size").child("Default Text Size preference").setValue("ON");
                    databaseReference.child(mName).child("User's Information").child("User's Settings").child("Security and Privacy").child("Customize Text Size").setValue("OFF");

                    //--->Language
                    databaseReference.child(mName).child("User's Information").child("User's Settings").child("Language").child("Recommended").setValue("English");

                    //--->Additional Resources
                    databaseReference.child(mName).child("User's Information").child("User's Settings").child("Additional Resources").child("Send crash reports").setValue("ON");

                    Toast.makeText(Registration_Activity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), HomePage_Activity.class);
                    intent.putExtra("Name", mName);
                    intent.putExtra("Email", mEmail);
                    intent.putExtra("Password", mPassword);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private boolean validateConfirmPassword() {

        if (mConfirmPassword.isEmpty())
        {
            editTxt_passwordConfirmation.setError("Required");
            return false;
        }
        else if (!mConfirmPassword.equals(mPassword))
        {
            editTxt_passwordConfirmation.setError("Your passwords do not match");
            return false;
        }
        else
            return true;
    }

    private boolean validatePassword() {

        //Regex pattern to require alphanumeric and special characters
        Pattern regexPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        Matcher matcher = regexPassword.matcher(mPassword);

        if (mPassword.isEmpty())
        {
            editTxt_password.setError("Required");
            return false;
        }
        else if (!matcher.matches())
        {
            editTxt_password.setError("Your password's not strong enough");
            return false;
        }
        else
            return true;
    }

    private boolean validateEmail() {

        if (mEmail.isEmpty())
        {
            editTxt_email.setError("Required");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches())
        {
            editTxt_email.setError("Invalid Email Address");
            return false;
        }
        else
            return true;
    }

    private boolean validateName() {

        //Regex pattern to allow only alphabets
        Pattern regexName = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = regexName.matcher(mName);

        if (mName.isEmpty())
        {
            editTxt_name.setError("Required");
            return false;
        }
        else if (!matcher.matches())
        {
            editTxt_name.setError("Invalid input");
            return false;
        }
        else
            return true;
    }

    private void initWidget() {

        // TextView
        txtView_logIn = findViewById(R.id.txtView_logIn);

        // EditText
        editTxt_name = findViewById(R.id.editTxt_name);
        editTxt_email = findViewById(R.id.editTxt_email);
        editTxt_password = findViewById(R.id.editTxt_password);
        editTxt_passwordConfirmation = findViewById(R.id.editTxt_passwordConfirmation);

        // ImageView
        btn_google = findViewById(R.id.btn_google);
        btn_instagram = findViewById(R.id.btn_instagram);
        btn_facebook = findViewById(R.id.btn_facebook);
        imgView_learnLoop = findViewById(R.id.imgView_learnLoop);

        // Button
        btn_getStarted = findViewById(R.id.btn_getStarted);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);


                //Get visual value on the user
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                mName = acct.getDisplayName();
                mEmail = acct.getEmail();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("Name", mName);
                intent.putExtra("Email", mEmail);
                startActivity(intent);

                System.out.println(mName);
                System.out.println(mEmail);

            }catch (ApiException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            // Application code

                            try {
                                mName = object.getString("name");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }
}