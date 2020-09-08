package com.ankit.testingapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.fxn.pix.Options;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;
import com.google.android.material.snackbar.Snackbar;

import java.security.cert.PKIXRevocationChecker;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<User> dataModels;
    ListView listView;
    private static UsersAdapter adapter;
    private ArrayList<String> returnValue = new ArrayList<String>();

private Options options;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView=(ListView)findViewById(R.id.list);

        dataModels= new ArrayList<>();

        dataModels.add(new User("Apple Pie", "Android 1.0", "1","September 23, 2008"));
        dataModels.add(new User("Banana Bread", "Android 1.1", "2","February 9, 2009"));
        dataModels.add(new User("Cupcake", "Android 1.5", "3","April 27, 2009"));
        dataModels.add(new User("Donut","Android 1.6","4","September 15, 2009"));
        dataModels.add(new User("Eclair", "Android 2.0", "5","October 26, 2009"));
        dataModels.add(new User("Froyo", "Android 2.2", "8","May 20, 2010"));
        dataModels.add(new User("Gingerbread", "Android 2.3", "9","December 6, 2010"));
        dataModels.add(new User("Honeycomb","Android 3.0","11","February 22, 2011"));
        dataModels.add(new User("Ice Cream Sandwich", "Android 4.0", "14","October 18, 2011"));
        dataModels.add(new User("Jelly Bean", "Android 4.2", "16","July 9, 2012"));
        dataModels.add(new User("Kitkat", "Android 4.4", "19","October 31, 2013"));
        dataModels.add(new User("Lollipop","Android 5.0","21","November 12, 2014"));
        dataModels.add(new User("Marshmallow", "Android 6.0", "23","October 5, 2015"));

        adapter= new UsersAdapter(dataModels,MainActivity.this);

     options = Options.init()
            .setRequestCode(100)                                           //Request code for activity results
            .setCount(3)                                                   //Number of images to restict selection count
            .setFrontfacing(false)                                         //Front Facing camera on start
            .setPreSelectedUrls(returnValue)                               //Pre selected Image Urls
            .setExcludeVideos(false)                                       //Option to exclude videos
            .setVideoDurationLimitinSeconds(30)                            //Duration for video recording
            .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT)     //Orientaion
            .setPath("/pix/images");                                       //Custom Path For media Storage

    Pix.start(MainActivity.this, options);

        listView.setAdapter(adapter);
       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                User dataModel= dataModels.get(position);
*//*
                Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*//*

            }
        });*/
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Pix.start(this, options);
                } else {
                    Toast.makeText(this, "Approve permissions to open Pix ImagePicker", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("dadadadada ",requestCode+" dfghfhgfhrfh "+resultCode+" sdfsdfsdf"+data);
        if (resultCode == Activity.RESULT_OK) {
            returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
                   // myAdapter.addImage(returnValue)
        }
}
}
