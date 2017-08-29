package erianda.com.quitsmoking;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Achievement extends ListActivity {
    DatabaseHelper mDatabaseHelper;
    int uangTersimpan=0;


    static final String[] hari1 =
            new String[] { "Tekanan darah dan denyut jantung membaik. Suhu tangan dan kaki " +
                    "kembali normal", "Karbon monoksida ( CO) meninggalkan sistem peredaran darah" +
                    " dan pernapasan. Oksigen dalam darah meningkat ke titik normal", "Tekanan darah lebih rendah dan kegiatan jantung lebih kuat\n" +
                    "menurunkan risiko penyakit arteri koroner (CAD)"};

    static final String[] hari2 =
            new String[] { "Tekanan darah dan denyut jantung membaik. Suhu tangan dan kaki " +
                    "kembali normal", "Karbon monoksida ( CO) meninggalkan sistem peredaran darah" +
                    " dan pernapasan. Oksigen dalam darah meningkat ke titik normal", "Tekanan darah lebih rendah dan kegiatan jantung lebih kuat\n" +
                    "menurunkan risiko penyakit arteri koroner (CAD)"};
    static final String[] hari3 =
            new String[] { "Tekanan darah dan denyut jantung membaik. Suhu tangan dan kaki " +
                    "kembali normal", "Karbon monoksida ( CO) meninggalkan sistem peredaran darah" +
                    " dan pernapasan. Oksigen dalam darah meningkat ke titik normal", "Tekanan darah lebih rendah dan kegiatan jantung lebih kuat\n" +
                    "menurunkan risiko penyakit arteri koroner (CAD)", "indera pengecap dan " +
                    "penciuman membaik"};
    static final String[] hari4 =
            new String[] { "Tekanan darah dan denyut jantung membaik. Suhu tangan dan kaki " +
                    "kembali normal", "Karbon monoksida ( CO) meninggalkan sistem peredaran darah" +
                    " dan pernapasan. Oksigen dalam darah meningkat ke titik normal", "Tekanan darah lebih rendah dan kegiatan jantung lebih kuat\n" +
                    "menurunkan risiko penyakit arteri koroner (CAD)", "indera pengecap dan " +
                    "penciuman membaik","Nikotin keluar dari tubuh","Dapat berolahraga dan " +
                    "melakukan aktivitas fisik tanpa merasa lelah dan sakit"};
    static final String[] hari5 =
            new String[] { "Tekanan darah dan denyut jantung membaik. Suhu tangan dan kaki " +
                    "kembali normal", "Karbon monoksida ( CO) meninggalkan sistem peredaran darah" +
                    " dan pernapasan. Oksigen dalam darah meningkat ke titik normal", "Tekanan darah lebih rendah dan kegiatan jantung lebih kuat\n" +
                    "menurunkan risiko penyakit arteri koroner (CAD)", "indera pengecap dan " +
                    "penciuman membaik","Nikotin keluar dari tubuh","Dapat berolahraga dan " +
                    "melakukan aktivitas fisik tanpa merasa lelah dan sakit","Sirkulasi anda " +
                    "membaik dan fungsi paru-paru meningkat"};
    static final String[] hari6 =
            new String[] { "Tekanan darah dan denyut jantung membaik. Suhu tangan dan kaki " +
                    "kembali normal", "Karbon monoksida ( CO) meninggalkan sistem peredaran darah" +
                    " dan pernapasan. Oksigen dalam darah meningkat ke titik normal", "Tekanan darah lebih rendah dan kegiatan jantung lebih kuat\n" +
                    "menurunkan risiko penyakit arteri koroner (CAD)", "indera pengecap dan " +
                    "penciuman membaik","Nikotin keluar dari tubuh","Dapat berolahraga dan " +
                    "melakukan aktivitas fisik tanpa merasa lelah dan sakit","Sirkulasi anda " +
                    "membaik dan fungsi paru-paru meningkat","Sesak napas dan batuk-batuk " +
                    "berkurang"};
    static final String[] hari7 =
            new String[] { "Tekanan darah dan denyut jantung membaik. Suhu tangan dan kaki " +
                    "kembali normal", "Karbon monoksida ( CO) meninggalkan sistem peredaran darah" +
                    " dan pernapasan. Oksigen dalam darah meningkat ke titik normal", "Tekanan darah lebih rendah dan kegiatan jantung lebih kuat\n" +
                    "menurunkan risiko penyakit arteri koroner (CAD)", "indera pengecap dan " +
                    "penciuman membaik","Nikotin keluar dari tubuh","Dapat berolahraga dan " +
                    "melakukan aktivitas fisik tanpa merasa lelah dan sakit","Sirkulasi anda " +
                    "membaik dan fungsi paru-paru meningkat","Sesak napas dan batuk-batuk " +
                    "berkurang","risiko serangan jantung menurun sampai setengah, dibanding " +
                    "dengan perokok aktif"};
    static final String[] hari8 =
            new String[] { "Tekanan darah dan denyut jantung membaik. Suhu tangan dan kaki " +
                    "kembali normal", "Karbon monoksida ( CO) meninggalkan sistem peredaran darah" +
                    " dan pernapasan. Oksigen dalam darah meningkat ke titik normal", "Tekanan darah lebih rendah dan kegiatan jantung lebih kuat\n" +
                    "menurunkan risiko penyakit arteri koroner (CAD)", "indera pengecap dan " +
                    "penciuman membaik","Nikotin keluar dari tubuh","Dapat berolahraga dan " +
                    "melakukan aktivitas fisik tanpa merasa lelah dan sakit","Sirkulasi anda " +
                    "membaik dan fungsi paru-paru meningkat","Sesak napas dan batuk-batuk " +
                    "berkurang","risiko serangan jantung menurun sampai setengah, dibanding " +
                    "dengan perokok aktif","Kesehatan hati dan otak akan aman seperti orang yang " +
                    "tidak merokok"};

    static final String[] hari9 =
            new String[] { "Tekanan darah dan denyut jantung membaik. Suhu tangan dan kaki " +
                    "kembali normal", "Karbon monoksida ( CO) meninggalkan sistem peredaran darah" +
                    " dan pernapasan. Oksigen dalam darah meningkat ke titik normal", "Tekanan darah lebih rendah dan kegiatan jantung lebih kuat\n" +
                    "menurunkan risiko penyakit arteri koroner (CAD)", "indera pengecap dan " +
                    "penciuman membaik","Nikotin keluar dari tubuh","Dapat berolahraga dan " +
                    "melakukan aktivitas fisik tanpa merasa lelah dan sakit","Sirkulasi anda " +
                    "membaik dan fungsi paru-paru meningkat","Sesak napas dan batuk-batuk " +
                    "berkurang","risiko serangan jantung menurun sampai setengah, dibanding " +
                    "dengan perokok aktif","Kesehatan hati dan otak akan aman seperti orang yang " +
                    "tidak merokok","Risiko kanker paru setengahnya dibandingkan perokok\n" +
                    "Risiko Anda dari kanker mulut, tenggorokan, kerongkongan, kandung kemih, " +
                    "ginjal dan pankreas juga akan menurun"};

    static final String[] hari10 =
            new String[] { "Tekanan darah dan denyut jantung membaik. Suhu tangan dan kaki " +
                    "kembali normal", "Karbon monoksida ( CO) meninggalkan sistem peredaran darah" +
                    " dan pernapasan. Oksigen dalam darah meningkat ke titik normal", "Tekanan darah lebih rendah dan kegiatan jantung lebih kuat\n" +
                    "menurunkan risiko penyakit arteri koroner (CAD)", "indera pengecap dan " +
                    "penciuman membaik","Nikotin keluar dari tubuh","Dapat berolahraga dan " +
                    "melakukan aktivitas fisik tanpa merasa lelah dan sakit","Sirkulasi anda " +
                    "membaik dan fungsi paru-paru meningkat","Sesak napas dan batuk-batuk " +
                    "berkurang","risiko serangan jantung menurun sampai setengah, dibanding " +
                    "dengan perokok aktif","Kesehatan hati dan otak akan aman seperti orang yang " +
                    "tidak merokok","Risiko kanker paru setengahnya dibandingkan perokok\n" +
                    "Risiko Anda dari kanker mulut, tenggorokan, kerongkongan, kandung kemih, " +
                    "ginjal dan pankreas juga akan menurun","risiko stroke dan serangan jantung menurun sampai tingkat bukan perokok"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());

        mDatabaseHelper = new DatabaseHelper(this);
        List<Contact> contacts = mDatabaseHelper.getAllContacts();
        boolean x = mDatabaseHelper.isMasterEmpty();
        if (x == true) {
            Intent i = new Intent(this, Profile.class);
            startActivity(i);
        }

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn
                    .getPhoneNumber() + " ,Harga: " + cn.getHarga() + " ,SQL " + x;
            // Writing Contacts to log
            Log.d("Name: ", log);


            try {
                Date currentTanggal = df.parse(formattedDate);
                Date tanggalBerhenti = df.parse(cn.getPhoneNumber());
                long diff = currentTanggal.getTime() - tanggalBerhenti.getTime();
                long seconds = diff / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;
                uangTersimpan = (int) days;
            } catch (ParseException e) {
                e.printStackTrace();

            }

            if(uangTersimpan>5475){
                setListAdapter(new MobileArrayAdapter(this, hari10));
            }
            else if(uangTersimpan>3650){
                setListAdapter(new MobileArrayAdapter(this, hari9));
            }
            else if(uangTersimpan>1825){
                setListAdapter(new MobileArrayAdapter(this, hari8));
            }

            else if(uangTersimpan>365){
                setListAdapter(new MobileArrayAdapter(this, hari7));
            }
            else if(uangTersimpan>270){
                setListAdapter(new MobileArrayAdapter(this, hari6));
            }
            else if(uangTersimpan>84){
                setListAdapter(new MobileArrayAdapter(this, hari5));
            }
            else if(uangTersimpan>14){
                setListAdapter(new MobileArrayAdapter(this, hari4));
            }
            else if(uangTersimpan>3){
                setListAdapter(new MobileArrayAdapter(this, hari3));
            }
            else if(uangTersimpan>2){
                setListAdapter(new MobileArrayAdapter(this, hari2));
            }
            else if(uangTersimpan==1) {
                setListAdapter(new MobileArrayAdapter(this, hari1));
            }

        }
    }
}
