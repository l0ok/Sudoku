package maxs.sudoku.sudoku1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class StatisticLevel extends AppCompatActivity {

    TextView mode;
    TextView time;
    TextView countG;




        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.stat_level);
            final Button buttonLow=(Button)findViewById(R.id.buttonLow);
            final  Button buttonMedium=(Button)findViewById(R.id.buttonMedium);
            final Button buttonHard=(Button)findViewById(R.id.buttonHard);
            final Button buttonExtra=(Button)findViewById(R.id.buttonExtra);
            final Button buttonBack=(Button)findViewById(R.id.buttonBack);

            mode=findViewById(R.id.textViewMode);
            time=findViewById(R.id.textViewTimeZ);
            countG=findViewById(R.id.textViewCountZ);





            mode.setText("MODE");
            time.setText("");
            countG.setText("");

            final File fileLow = new File(getFilesDir() + "/l0wStat");
            File fileMed = new File(getFilesDir() + "/mediumStat");
            File fileHar = new File(getFilesDir() + "/hardStat");
            File fileExt = new File(getFilesDir() + "/extraStat");
            boolean existsLow = fileLow.exists();
            boolean existsMedium = fileMed.exists();
            boolean existsHard = fileHar.exists();
            boolean existsExtra = fileExt.exists();
            if(existsLow)buttonLow.setEnabled(true);
            else buttonLow.setEnabled(false);
            if(existsMedium)buttonMedium.setEnabled(true);
            else buttonMedium.setEnabled(false);
            if(existsHard)buttonHard.setEnabled(true);
            else buttonHard.setEnabled(false);
            if(existsExtra)buttonExtra.setEnabled(true);
            else buttonExtra.setEnabled(false);



            buttonLow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mode.setText("LOW");
                    loadStat("l0wStat");
                }
            });
            buttonMedium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mode.setText("MEDIUM");
                    loadStat("mediumStat");

                }
            });
            buttonHard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mode.setText("HARD");
                    loadStat("hardStat");

                }
            });
            buttonExtra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mode.setText("EXTRA");
                    loadStat("extraStat");

                }
            });
            buttonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(StatisticLevel.this,MainActivity.class);
                    startActivity(intent);finish();

                }
            });


        }

        public String loadFromFile(String fileName){

            BufferedReader bufferedReader= null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String line="";

            while (true){
                try {
                    if (!((line=bufferedReader.readLine())!=null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return line;
            }


            return null;

        }

        public void loadStat(String fileName){
            String line= loadFromFile(fileName);

            int minutes=Integer.parseInt(line.substring(0,2));
           int secundes=Integer.parseInt(line.substring(3,5));
           int count=Integer.parseInt(line.substring(6));
           time.setText(Integer.toString(minutes)+":"+Integer.toString(secundes));
            countG.setText(Integer.toString(count));



        }





}
