package Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cred.ease.MainActivity;
import com.cred.ease.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 1234;
    private static  String Email = null;
    private static  String pass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_signup);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);

        Button btn = findViewById(R.id.login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful())
                      {
                          Toast.makeText(SignUpActivity.this,"Successfully Authenticated",Toast.LENGTH_LONG).show();
                          Log.v("Success","Succesfully Logged In");
                          startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                      }
                      else
                      {
                          Toast.makeText(SignUpActivity.this,"Login Attempt Failed",Toast.LENGTH_LONG).show();
                          Log.e("Failed","Failed",task.getException());
                      }

                  }
              });
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(this, "User is Already Signed In", Toast.LENGTH_SHORT).show();

    }
}

