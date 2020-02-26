package maxs.sudoku.sudoku1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
  static int mode;
  static int continueGame;
    int Panel=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonStart=(Button)findViewById(R.id.buttonStart);
      final  Button buttonLow=(Button)findViewById(R.id.buttonLow);
      final  Button buttonMedium=(Button)findViewById(R.id.buttonMedium);
      final Button buttonHard=(Button)findViewById(R.id.buttonHard);
      final Button buttonExtra=(Button)findViewById(R.id.buttonExtra);
      final Button buttonContinue=(Button)findViewById(R.id.buttonContinue);
      final Button buttonStatistic=(Button)findViewById(R.id.buttonStatistic);
      final LinearLayout selectlevel=findViewById(R.id.selectlevel);
      final ImageView mainBack=findViewById(R.id.mainBack);

        File file = new File(getFilesDir() + "/saveGame");

        boolean exists = file.exists();
        if(exists)buttonContinue.setEnabled(true);
        else buttonContinue.setEnabled(false);

      mode=1;
      continueGame=0;
       final Animation anim = AnimationUtils.loadAnimation(this, R.anim.mytrans);
       final Animation animBack = AnimationUtils.loadAnimation(this, R.anim.mytransback);
        selectlevel.setVisibility(View.GONE);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    selectlevel.setVisibility(View.VISIBLE);

                    selectlevel.startAnimation(anim);
                    Panel=1;
                }catch (Exception e){}

            }
        });
        mainBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Panel==1) {
                    selectlevel.setVisibility(View.GONE);
                    selectlevel.startAnimation(animBack);
                    Panel=0;
                }
            }
        });
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueGame=1;
                Intent intent=new Intent(MainActivity.this,GameLevel.class);
                startActivity(intent);finish();
            }
        });
        buttonStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                Intent intent=new Intent(MainActivity.this,StatisticLevel.class);
                startActivity(intent);finish();
            }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.toString(),
                            Toast.LENGTH_LONG).show();


                }
            }
        });
        buttonLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=1;
                try {
                    Intent intent = new Intent(MainActivity.this, GameLevel.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                        Toast.makeText(MainActivity.this, e.toString(),
                                Toast.LENGTH_LONG).show();
            }}
        });
        buttonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=2;
                Intent intent=new Intent(MainActivity.this,GameLevel.class);
                startActivity(intent);finish();
            }
        });
        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=3;
                Intent intent=new Intent(MainActivity.this,GameLevel.class);
                startActivity(intent);finish();
            }
        });
        buttonExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=4;
                Intent intent=new Intent(MainActivity.this,GameLevel.class);
                startActivity(intent);finish();
            }
        });



    }
    public int get_my_mode(){
        return this.mode;
    }
    public int get_my_continueGame(){
        return this.continueGame;
    }
}
