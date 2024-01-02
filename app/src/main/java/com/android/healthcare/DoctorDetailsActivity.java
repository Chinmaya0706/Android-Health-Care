package com.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 = {
            {"Doctor Name : Dr. Chinmaya Kumar Jena","Hospital Address : Balasore","Exp : 5yrs","Mob No : 6370342926","2000"},
            {"Doctor Name : Prasad Pawar","Hospital Address : Soro","Exp : 6yrs","Mob No : 7750873336","1500"},
            {"Doctor Name : Tanmaya Kumar Jena","Hospital Address : Mumbai","Exp : 10yrs","Mob No : 7992735474","300"},
            {"Doctor Name : Animesh Panda","Hospital Address : Cuttack","Exp : 5yrs","Mob No : 7834209117","1500"},
            {"Doctor Name : R.N Sahoo","Hospital Address : Soro","Exp : 15yrs","Mob No : 7991082840","2500"}
    };
    private String[][] doctor_details2 = {
            {"Doctor Name : Neelam Patil","Hospital Address : Delhi","Exp : 15yrs","Mob No : 7991082840","4500"},
            {"Doctor Name : Narendra Modi","Hospital Address : Surat","Exp : 5yrs","Mob No : 4327891022","1500"},
            {"Doctor Name : Sudheer Swain","Hospital Address : Puri","Exp : 10yrs","Mob No : 5438920172","10000"},
            {"Doctor Name : Chinmaya Kumar Jena","Hospital Address : Balasore","Exp : 5yrs","Mob No : 6370342926","1500"},
            {"Doctor Name : Chinmaya Kumar Jena","Hospital Address : Balasore","Exp : 5yrs","Mob No : 6370342926","3700"}
    };
    private String[][] doctor_details3 = {
            {"Doctor Name : Dr. Chinmaya Kumar Jena","Hospital Address : Balasore","Exp : 5yrs","Mob No : 6370342926","1500"},
            {"Doctor Name : Prasad Pawar","Hospital Address : Soro","Exp : 6yrs","Mob No : 7750873336","1500"},
            {"Doctor Name : Tanmaya Kumar Jena","Hospital Address : Mumbai","Exp : 10yrs","Mob No : 7992735474","1500"},
            {"Doctor Name : Animesh Panda","Hospital Address : Cuttack","Exp : 5yrs","Mob No : 7834209117","1500"},
            {"Doctor Name : R.N Sahoo","Hospital Address : Soro","Exp : 15yrs","Mob No : 7991082840","1500"}
    };
    private String[][] doctor_details4 = {
            {"Doctor Name : Neelam Patil","Hospital Address : Delhi","Exp : 15yrs","Mob No : 7991082840","2500"},
            {"Doctor Name : Narendra Modi","Hospital Address : Surat","Exp : 5yrs","Mob No : 4327891022","4500"},
            {"Doctor Name : Sudheer Swain","Hospital Address : Puri","Exp : 10yrs","Mob No : 5438920172","1500"},
            {"Doctor Name : Chinmaya Kumar Jena","Hospital Address : Balasore","Exp : 5yrs","Mob No : 6370342926","1500"},
            {"Doctor Name : Chinmaya Kumar Jena","Hospital Address : Balasore","Exp : 5yrs","Mob No : 6370342926","1500"}
    };
    private String[][] doctor_details5 = {
            {"Doctor Name : Neelam Patil","Hospital Address : Delhi","Exp : 15yrs","Mob No : 7991082840","10000"},
            {"Doctor Name : Narendra Modi","Hospital Address : Surat","Exp : 5yrs","Mob No : 4327891022","1500"},
            {"Doctor Name : Sudheer Swain","Hospital Address : Puri","Exp : 10yrs","Mob No : 5438920172","2400"},
            {"Doctor Name : Chinmaya Kumar Jena","Hospital Address : Balasore","Exp : 5yrs","Mob No : 6370342926","1600"},
            {"Doctor Name : Chinmaya Kumar Jena","Hospital Address : Balasore","Exp : 5yrs","Mob No : 6370342926","1500"}
    };
    Button back;
    TextView tv;
    String[][] doctor_details = {};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        back = findViewById(R.id.buttonDDBack);
        tv = findViewById(R.id.textViewDDtitle);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("Family Physicians")==0) doctor_details = doctor_details1;
        else if(title.compareTo("Dietician")==0) doctor_details = doctor_details2;
        else if(title.compareTo("Dentist")==0) doctor_details = doctor_details3;
        else if(title.compareTo("Surgeon")==0) doctor_details = doctor_details4;
        else doctor_details = doctor_details5;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setElevation(20.0f);
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
                finish();
            }
        });
        list = new ArrayList();
        for(int i = 0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Fees : "+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,
                list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst = findViewById(R.id.listviewDD);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                intent.putExtra("text1",title);
                intent.putExtra("text2",doctor_details[i][0]);
                intent.putExtra("text3",doctor_details[i][1]);
                intent.putExtra("text4",doctor_details[i][3]);
                intent.putExtra("text5",doctor_details[i][4]);
                startActivity(intent);
            }
        });
    }
}