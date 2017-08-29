package erianda.com.quitsmoking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Profile extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd;
    private EditText editText,editText2, editText3;
    private TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        mDatabaseHelper = new DatabaseHelper(this);
        hasil = (TextView)findViewById(R.id.hasil);

        List<Contact> contacts = mDatabaseHelper.getAllContacts();
        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn
                    .getPhoneNumber()+ " ,Harga: " + cn.getHarga();
            // Writing Contacts to log
            Log.d("Name: ", log);
            if(cn.getID()!=0) {
                Intent i = new Intent(this, ProfileCurrent.class);
                startActivity(i);
            }
            hasil.setText(cn.getName()+" "+cn.getPhoneNumber()+" "+cn.getHarga());

        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                String newEntry2 = editText2.getText().toString();
                String newEntry3 = editText3.getText().toString();
                mDatabaseHelper.addContact(new Contact(newEntry, newEntry2, newEntry3));

                Intent i = new Intent(Profile.this, ProfileCurrent.class);
                startActivity(i);



            }
        });





    }




    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
