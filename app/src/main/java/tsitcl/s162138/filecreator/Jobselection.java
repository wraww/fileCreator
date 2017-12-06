package tsitcl.s162138.filecreator;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

public class Jobselection extends AppCompatActivity
        implements View.OnClickListener {
    private static final int REQUEST_CODE = 200 ;
    Button createButton;
    EditText photogname;
    EditText projnum;
    EditText phase;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobselection);
        createButton = (Button) findViewById(R.id.createButton);
        createButton.setOnClickListener(this);
        photogname = (EditText) findViewById(R.id.editText);//added just to clear error
        projnum = (EditText) findViewById(R.id.editText);//added just to clear erro
        phase = (EditText) findViewById(R.id.editText);// added just to clear error
        datePicker = (DatePicker) findViewById(R.id.datePicker);

    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClick(View createButton) {
        String date = getDateFromDatePicker(datePicker).toString();
        String photog = "FinalTest";//just to clear error
//        photogname.getText().toString();
        String proj = projnum.getText().toString() + "." + phase.getText().toString();
        String state;
        state = Environment.getExternalStorageState();

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("Permisson", "Permission is granted");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File appDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + date + "/" + proj + "/" + photog);
            boolean isDirectoryCreated = appDirectory.exists();
            if (!isDirectoryCreated) {
                isDirectoryCreated = appDirectory.mkdirs();
            }
            if (isDirectoryCreated) {
                Toast.makeText(Jobselection.this, "Folder is created", Toast.LENGTH_LONG).show();
            } else
                Log.d("error", "dir.already exists");
        }
    }
}

//        Intent launchUnitLoc = new Intent(this, UnitLocation.class);
//        startActivity(launchUnitLoc);