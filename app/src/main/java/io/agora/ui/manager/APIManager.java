package io.agora.ui.manager;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SendCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.agora.ui.callback.APIManagerCallback;
import io.agora.ui.model.HealthRecordVo;
import io.agora.ui.model.User;

// Imports to the JSONObject object, necessary for the push message
// Parse Dependencies


public class APIManager {

    private static final APIManager ourInstance = new APIManager();
    private static String TAG = "YUG";
    private FirebaseAuth mAuth;

    public static APIManager getInstance() {
        return ourInstance;
    }

    private APIManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void registerUser(Activity activity, final String fullname, String email, String password,
                             final boolean isEMT, final APIManagerCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            createNewUser(task.getResult().getUser(), fullname, isEMT);
                            callback.onSuccess(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            callback.onFailure("Authentication failed.");
                        }
                    }
                });
    }

    private void createNewUser(FirebaseUser userFromRegistration, String fullname, boolean isEMT) {
        String email = userFromRegistration.getEmail();
        String userId = userFromRegistration.getUid();

        User user = new User(fullname, email, isEMT);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("users").child(userId).setValue(user);
    }

    public void loginUser(Activity activity, String email, String password, final APIManagerCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            callback.onSuccess(user);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            callback.onFailure(task.getException().toString());
                        }
                    }
                });
    }

    public void getUserProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }

    public void sendPush(String roomName) {
        JSONObject data = new JSONObject();
        // Put data in the JSON object
        try {
            data.put("alert", "Emergency Call @ " + roomName);
            data.put("title", "Please open the app to join the call.");
            data.put("roomName", roomName);
        } catch (JSONException e) {
            // should not happen
            throw new IllegalArgumentException("unexpected parsing error", e);
        }
        ParsePush push = new ParsePush();
        push.setData(data);
        push.sendInBackground(new SendCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.d(TAG, "Error in push: " + e.toString());
                }
            }
        });
    }

    public void addHealthRecords(List<String> healthConditions) {
        HealthRecordVo healthRecordVo = new HealthRecordVo(healthConditions);

        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();
        String userId = user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("healthRecords").child(userId).setValue(healthRecordVo);
    }

    public void getHealthRecords(String email) {
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("healthRecords").child(email);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void addResponder(final String responders) {
        final FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();
        final String userId = user.getUid();

        getResponders(userId, new APIManagerCallback() {
            @Override
            public void onSuccess(Object object) {
                List<String> responderList;
                if (object == null) {
                    responderList = new ArrayList<>();
                } else {
                    responderList = (List<String>) object;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                responderList.add(responders);
                myRef.child("myResponders").child(userId).setValue(responderList);
            }

            @Override
            public void onFailure(String status) {

            }
        });
    }

    public void getResponders(final String userId, final APIManagerCallback callback) {
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("myResponders").child(userId);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*HashMap<String, String> map = (HashMap<String, String>) dataSnapshot.getValue();
                //Getting Collection of values from HashMap
                Collection<String> values = map.values();
                //Creating an ArrayList of values*/

                ArrayList<String> listOfValues = (ArrayList<String>) dataSnapshot.getValue();

                Log.d(TAG, "Value is: " + listOfValues);
                callback.onSuccess(listOfValues);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                callback.onFailure("Failed to read value." + error.toException());
            }
        });
    }
}
