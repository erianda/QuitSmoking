package erianda.com.quitsmoking;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListBerita extends ListActivity {


    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://erianda.net/project/quitsmoking/getBerita.php";

    // JSON Node names
    private static final String TAG_CONTACTS = "listberita";

    private static final String TAG_NAME = "judul";
    private static final String TAG_TGL = "tanggal";
    private static final String TAG_KAT = "kategori";
    private static final String TAG_ISI = "isi";

    // contacts JSONArray
    JSONArray contacts = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.berita);

        contactList = new ArrayList<HashMap<String, String>>();


        ListView lv = getListView();

        // Listview on item click listener
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.judul))
                        .getText().toString();
                String tgl = ((TextView) view.findViewById(R.id.tgl))
                        .getText().toString();
                String kat = ((TextView) view.findViewById(R.id.kategori))
                        .getText().toString();



                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(), DetailBerita.class);
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_TGL, tgl);
                in.putExtra(TAG_KAT, kat);
                in.putExtra(TAG_ISI, contactList.get(position).get("isi"));

                startActivity(in);

            }
        });

        // Calling async task to get json
        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ListBerita.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray(TAG_CONTACTS);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);


                        String name = c.getString(TAG_NAME);
                        String tgl = c.getString(TAG_TGL);
                        String kat = c.getString(TAG_KAT);
                        String isi = c.getString(TAG_ISI);

                        // Phone node is JSON Object


                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value

                        contact.put(TAG_NAME, name);
                        contact.put(TAG_TGL, tgl);
                        contact.put(TAG_KAT, kat);
                        contact.put(TAG_ISI, isi);


                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    ListBerita.this, contactList,
                    R.layout.list_item, new String[] { TAG_NAME, TAG_TGL, TAG_KAT}, new int[] { R
                    .id.judul, R.id.tgl, R.id.kategori});

            setListAdapter(adapter);
        }

    }

}