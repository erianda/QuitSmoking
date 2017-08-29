package erianda.com.quitsmoking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProfileCurrent extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    TextView nama, tanggal, tersimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_current);
        mDatabaseHelper = new DatabaseHelper(this);
        nama = (TextView)findViewById(R.id.namaCurrent);
        tanggal = (TextView)findViewById(R.id.berhentiCurrent);
        tersimpan = (TextView)findViewById(R.id.savingCurrent);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());


        // textView is the TextView view that should display it
        //textView.setText(currentDateTimeString);
        List<Contact> contacts = mDatabaseHelper.getAllContacts();
        boolean x = mDatabaseHelper.isMasterEmpty();
        if(x==true){
            Intent i = new Intent(this, Profile.class);
            startActivity(i);
        }

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn
                    .getPhoneNumber()+ " ,Harga: " + cn.getHarga()+" ,SQL "+ x;
            // Writing Contacts to log
            Log.d("Name: ", log);


            try {
                Date currentTanggal = df.parse(formattedDate);
                Date tanggalBerhenti = df.parse(cn.getPhoneNumber());
            long diff =   currentTanggal.getTime()- tanggalBerhenti.getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            int uangTersimpan = (int) (Integer.parseInt(cn.getHarga()) * days);


            nama.setText("Nama : "+cn.getName());
            tanggal.setText("Lama Berhenti : "+ days +" hari");
            tersimpan.setText("Uang Tersimpan : Rp "+uangTersimpan);
            } catch (ParseException e) {
                e.printStackTrace();

            }

        }
    }

}
