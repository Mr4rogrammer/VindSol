package com.mrprogrammer.vindsol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mrprogrammer.vindsol.Model.Url;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class Info extends AppCompatActivity {

    private FirebaseDatabase rootnode;
    private DatabaseReference reference;
    ProgressBar progressBar;
    FloatingActionButton download, share;
    private PDFView pdfView;
    long downid = 0, ddid = 0;
    String type,database="";
    String link;
    String fname;
    int REQUEST_PERMISSION = 1;
    int REQUEST_PERMISSION_dw = 2;


    private void requestStoragePermission_dwn() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("Permission is needed to access files from your device...")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Info.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_dw);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_dw);
        }
    }

    private void permission_dwn() {
        if (ContextCompat.checkSelfPermission(Info.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Info.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_dw);
            }
        } else {

            requestStoragePermission_dwn();

        }
    }


    private void requestStoragePermission_share() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("Permission is needed to access files from your device...")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Info.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
        }
    }

    private void permission_share() {
        if (ContextCompat.checkSelfPermission(Info.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Info.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            }
        } else {

            requestStoragePermission_share();

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);


        try {
            Intent i = getIntent();
            type = i.getStringExtra("types");
            database=i.getStringExtra("database");

        } catch (Exception e) {
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        ImageView back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        try {
            registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        } catch (Exception e) {
            Toast.makeText(this, "Receiver error", Toast.LENGTH_SHORT).show();
        }
        pdfView = findViewById(R.id.pdfview);
        progressBar = findViewById(R.id.pro1);
        download = findViewById(R.id.down);
        share = findViewById(R.id.share);





        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permission_share();

            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                permission_dwn();


            }
        });


        rootnode = FirebaseDatabase.getInstance();

        try {
            reference = rootnode.getReference(database).child(type).child("data");
        } catch (Exception e) {
            Toast.makeText(this, "No data found !", Toast.LENGTH_SHORT).show();
            finish();
        }

        try {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Url url = snapshot.getValue(Url.class);

                    // Toast.makeText(Info.this, url.getUrl().toString(), Toast.LENGTH_SHORT).show();
                    link = url.getUrl();
                    try {
                        new RetrivedPdf().execute(link);
                    } catch (Exception e) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Info.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Database ref error " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        // Toast.makeText(this, type, Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                URL url = null;
                try {
                    url = new URL(link);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    Toast.makeText(Info.this, "Please wait ...", Toast.LENGTH_SHORT).show();
                    String filename = UUID.randomUUID().toString() + ".pdf";
                    fname = filename;
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(link + ""));
                    request.setTitle(filename);
                    request.setDescription("Downloading to share...");
                    request.setMimeType("application/pdf");
                    request.allowScanningByMediaScanner();
                    request.setAllowedOverMetered(true);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    downid = dm.enqueue(request);


                } catch (Exception e) {
                    Toast.makeText(Info.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {

                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 2) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                URL url = null;
                try {
                    url = new URL(link);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    Toast.makeText(Info.this, "Please wait ...", Toast.LENGTH_SHORT).show();
                    String filename = UUID.randomUUID().toString() + ".pdf";
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(link + ""));
                    request.setTitle(filename);
                    request.setDescription("Downloading please wait...");
                    request.setMimeType("application/pdf");
                    request.allowScanningByMediaScanner();
                    request.setAllowedOverMetered(true);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    ddid = dm.enqueue(request);


                } catch (Exception e) {
                    Toast.makeText(Info.this, "Download Error !", Toast.LENGTH_SHORT).show();
                }
            } else {

                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (id == ddid) {
                Toast.makeText(context, "Download Completed !", Toast.LENGTH_SHORT).show();
            } else if (id == downid) {
                try {
                    String targetpath;
                    targetpath = Environment.getExternalStorageDirectory() + File.separator + "Download" + File.separator + fname;


                    File output = new File(targetpath);


                    //     Toast.makeText(context, targetpath, Toast.LENGTH_LONG).show();

                    Uri uri = null;

                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

                        uri = Uri.fromFile(output);
                    } else {
                        uri = FileProvider.getUriForFile(Info.this, Info.this.getPackageName() + ".provider", output);
                    }
                    Intent share = new Intent();

                    share.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                    share.setAction(Intent.ACTION_SEND);
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.setType("application/pdf");

                    startActivity(Intent.createChooser(share, "Vind Sol share"));
                } catch (Exception e) {
                    Toast.makeText(context, "Sharing Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    class RetrivedPdf extends AsyncTask<String, Void, InputStream> {

        @Override
        protected void onPostExecute(InputStream inputStream) {
            try {

                pdfView.fromStream(inputStream).onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        //  Toast.makeText(Info.this, "done", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        download.setVisibility(View.VISIBLE);
                        share.setVisibility(View.VISIBLE);
                    }
                }).load();






            } catch (Exception e) {
                Toast.makeText(Info.this, "Pdf load error " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (MalformedURLException e) {
                // progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Info.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                //  progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Info.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return inputStream;


        }
    }
}