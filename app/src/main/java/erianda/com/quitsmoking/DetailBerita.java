package erianda.com.quitsmoking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class DetailBerita extends Activity {
	
	// JSON node keys
	private static final String TAG_NAME = "judul";
	private static final String TAG_TGL = "tanggal";
	private static final String TAG_KAT = "kategori";
    private static final String TAG_ISI = "isi";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(TAG_NAME);
        String email = in.getStringExtra(TAG_TGL);
        String mobile = in.getStringExtra(TAG_KAT);
        //String isi = in.getStringExtra(TAG_ISI);
        String isi = getIntent().getExtras().getString("isi");
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblEmail = (TextView) findViewById(R.id.email_label);
        TextView lblMobile = (TextView) findViewById(R.id.mobile_label);
        TextView lblIsi = (TextView) findViewById(R.id.isi);
        
        lblName.setText(name);
        lblEmail.setText(email);
        lblMobile.setText(mobile);
        lblIsi.setText(isi);
    }
}
