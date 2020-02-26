package maxs.sudoku.sudoku1;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;


import androidx.appcompat.app.AppCompatActivity;


public class GameLevel extends AppCompatActivity {
    Button buttonActive;
    int iActive;
    int Mistakes=3;
    long minutes=0;
    long secundes=0;
    int[] Field;
    int[] FieldToGame;
    int[] firstField;
    int minutesGame=60000;
    int mode=1;
    int contin=0;
    int sz = 81;
    Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_level);


        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final Button button4 = findViewById(R.id.button4);
        final Button button5 = findViewById(R.id.button5);
        final Button button6 = findViewById(R.id.button6);
        final Button button7 = findViewById(R.id.button7);
        final Button button8 = findViewById(R.id.button8);
        final Button button9 = findViewById(R.id.button9);
        final Button buttonMistakes=findViewById(R.id.button_mistakes);
        final Button buttonCancel=findViewById(R.id.button_cancel);
        final Button buttonMode=findViewById(R.id.buttonMode);
        final Button buttonCheck=findViewById(R.id.button_check);
        final Button buttonPause=findViewById(R.id.button_pause);
        final TextView timerView=findViewById(R.id.timerView);
        final int[] FieldDraw={3,4,5,12,13,14,21,22,23,27,28,29,33,34,35,36,37,38,42,43,44,45,46,47,51,52,53,57,58,59,66,67,68,75,76,77};

        final int[] arrayIdButton = {
                R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16, R.id.button17, R.id.button18,
                R.id.button19, R.id.button20, R.id.button21, R.id.button22, R.id.button23, R.id.button24, R.id.button25, R.id.button26, R.id.button27,
                R.id.button28, R.id.button29, R.id.button30, R.id.button31, R.id.button32, R.id.button33, R.id.button34, R.id.button35, R.id.button36,
                R.id.button37, R.id.button38, R.id.button39, R.id.button40, R.id.button41, R.id.button42, R.id.button43, R.id.button44, R.id.button45,
                R.id.button46, R.id.button47, R.id.button48, R.id.button49, R.id.button50, R.id.button51, R.id.button52, R.id.button53, R.id.button54,
                R.id.button55, R.id.button56, R.id.button57, R.id.button58, R.id.button59, R.id.button60, R.id.button61, R.id.button62, R.id.button63,
                R.id.button64, R.id.button65, R.id.button66, R.id.button67, R.id.button68, R.id.button69, R.id.button70, R.id.button71, R.id.button72,
                R.id.button73, R.id.button74, R.id.button75, R.id.button76, R.id.button77, R.id.button78, R.id.button79, R.id.button80, R.id.button81,
                R.id.button82, R.id.button83, R.id.button84, R.id.button85, R.id.button86, R.id.button87, R.id.button88, R.id.button89, R.id.button90,
        };
        MainActivity mainActivity=new MainActivity();
        contin=mainActivity.get_my_continueGame();

        if(contin==0){



        mode=mainActivity.get_my_mode();

        if(mode==1){ minutesGame*=15; }
        if(mode==2) { minutesGame*=30;}
        if(mode==3) { minutesGame*=45;}
        if(mode==4) { minutesGame*=60;}
        Field =newGameField();
        FieldToGame=generateFinishField(mode,Field);
        firstField=copyArr(FieldToGame,sz);
        }
        else{

            loadGame("saveGame");

        }



        if(mode==1){ buttonMode.setText("L");  }
        if(mode==2) {buttonMode.setText("M");}
        if(mode==3) {buttonMode.setText("H");}
        if(mode==4) {buttonMode.setText("E"); }
        buttonMistakes.setText("MISTAKES x"+Mistakes);







        buttonActive = findViewById(arrayIdButton[0]);
        buttonActive.setTextColor(Color.parseColor("#000000"));
        buttonActive.setBackgroundResource(R.drawable.style_view_stroke_active);
        buttons = new Button[sz];
        for (int i = 0; i < sz; i++) {
            buttons[i] = (Button) findViewById(arrayIdButton[i]);
            buttons[i].setTextSize(24);
            if(FieldToGame[i]==0) {
                buttons[i].setText(" ");
            }
            else{
                buttons[i].setText(Integer.toString(FieldToGame[i]));
                if(firstField[i]!=0){
                   buttons[i].setEnabled(false);

                }
            }
        }




        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 81; i++) {
                    if (v.getId() == arrayIdButton[i]) {
                        buttonActive = findViewById(arrayIdButton[i]);
                        iActive=i;
                        buttonActive.setTextColor(Color.parseColor("#000000"));
                        buttonActive.setBackgroundResource(R.drawable.style_view_stroke_active);


                    } else {
                        buttons[i].setTextColor(Color.parseColor("#ffffff"));
                        buttons[i].setBackgroundResource(R.drawable.view_pressed);

                        for(int j=0;j<36;j++) {
                            if(i==FieldDraw[j])
                                buttons[i].setBackgroundResource(R.drawable.view_pressed1);
                        }
                    }
                }
            }
        };

        for (int i = 0; i < sz; i++) {
            buttons[i].setOnClickListener(onClickListener);
        }


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonActive.setText(" ");
            }
        });

        buttonMistakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             int tmp= mistakes(buttonMistakes);
             if(tmp==0)
                for(int i=0;i<sz;i++){
                    if(buttons[i].getText()!=(Integer.toString(Field[i]))){
                        buttons[i].setTextColor(Color.parseColor("#ff0000"));

                    }
                    else{
                        if(i==iActive){
                            buttons[i].setTextColor(Color.parseColor("#000000"));}
                        else{
                            buttons[i].setTextColor(Color.parseColor("#ffffff"));
                        }

                    }

                }
            }
        });

        timer(timerView,minutesGame);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonActive.setText(Integer.toString(1));

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonActive.setText(Integer.toString(2));

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonActive.setText(Integer.toString(3));

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonActive.setText(Integer.toString(4));

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonActive.setText(Integer.toString(5));

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonActive.setText(Integer.toString(6));

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonActive.setText(Integer.toString(7));

            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonActive.setText(Integer.toString(8));

            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonActive.setText(Integer.toString(9));

            }
        });

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winGame(buttons,Field,sz);
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveGame("saveGame",mode,Field,firstField,buttons,sz);
               Intent intent = new Intent(GameLevel.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


            }

    @Override
    public void onBackPressed(){
        try {
            saveGame("saveGame",mode,Field,firstField,buttons,sz);

            Intent intent = new Intent(GameLevel.this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){}
    }

    @Override
    protected void onUserLeaveHint() {
        saveGame("saveGame",mode,Field,firstField,buttons,sz);
        this.finish();
        super.onUserLeaveHint();

    }

    public int[] copyArr(int[] Arr,int sz){
        int[] retArr=new int[sz];
        for(int i=0;i<sz;i++){
            retArr[i]=Arr[i];

        }
        return retArr;
    }

    public void timer(final TextView timerView,int minutesGame){
        new CountDownTimer(minutesGame,1000){
            @Override
            public void onTick(long l){
                long minutesOut=l/60000;
                long secundesOut=(l-(minutesOut)*60000)/1000;
                minutes=minutesOut;
                secundes=secundesOut;
                if(secundesOut>9) timerView.setText(minutesOut+":"+ secundesOut);
                else timerView.setText(minutesOut+":0"+ secundesOut);
            }
            public void onFinish(){
                loseGame();

            }

        }.start();


    }

    public int[] generateFinishField(int mode,int[] field){
        int[] finishField=new int[81];
        for(int i=0;i<81;i++){
            finishField[i]=field[i];

        }
        int sz=0;
        if(mode==1)sz=20;
        if(mode==2)sz=40;
        if(mode==3)sz=50;
        if(mode==4)sz=60;
        Random rand = new Random();
        for(int i=0;i<sz;i++){
            int randI = 0 + (int) (Math.random() * 80);
            if(finishField[randI]==0)i--;
            finishField[randI]=0;
        }

        return finishField;
    }

    public int[] newGameField() {
        int[][] field = new int[9][9];
        Random rand = new Random();
        int sz = 9;
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                field[i][j] = 0;
            }
        }

        int i = 0;
        while (i < 9) {
            int tmp = 1 + (int) (Math.random() * 9);
            int k = 0;
            for (int j = 0; j < 9; j++) {
                if (tmp == field[0][j]) {
                    k = 1;

                }

            }
            if (k == 0) {
                field[0][i] = tmp;
                i++;

            }


        }

        field[1][0] = field[0][3];
        field[1][1] = field[0][4];
        field[1][2] = field[0][5];
        field[1][3] = field[0][6];
        field[1][4] = field[0][7];
        field[1][5] = field[0][8];
        field[1][6] = field[0][0];
        field[1][7] = field[0][1];
        field[1][8] = field[0][2];

        field[2][0] = field[1][3];
        field[2][1] = field[1][4];
        field[2][2] = field[1][5];
        field[2][3] = field[1][6];
        field[2][4] = field[1][7];
        field[2][5] = field[1][8];
        field[2][6] = field[1][0];
        field[2][7] = field[1][1];
        field[2][8] = field[1][2];

        field[3][0] = field[2][1];
        field[3][1] = field[2][2];
        field[3][2] = field[2][3];
        field[3][3] = field[2][4];
        field[3][4] = field[2][5];
        field[3][5] = field[2][6];
        field[3][6] = field[2][7];
        field[3][7] = field[2][8];
        field[3][8] = field[2][0];

        field[4][0] = field[3][3];
        field[4][1] = field[3][4];
        field[4][2] = field[3][5];
        field[4][3] = field[3][6];
        field[4][4] = field[3][7];
        field[4][5] = field[3][8];
        field[4][6] = field[3][0];
        field[4][7] = field[3][1];
        field[4][8] = field[3][2];

        field[5][0] = field[4][3];
        field[5][1] = field[4][4];
        field[5][2] = field[4][5];
        field[5][3] = field[4][6];
        field[5][4] = field[4][7];
        field[5][5] = field[4][8];
        field[5][6] = field[4][0];
        field[5][7] = field[4][1];
        field[5][8] = field[4][2];

        field[6][0] = field[5][1];
        field[6][1] = field[5][2];
        field[6][2] = field[5][3];
        field[6][3] = field[5][4];
        field[6][4] = field[5][5];
        field[6][5] = field[5][6];
        field[6][6] = field[5][7];
        field[6][7] = field[5][8];
        field[6][8] = field[5][0];

        field[7][0] = field[6][3];
        field[7][1] = field[6][4];
        field[7][2] = field[6][5];
        field[7][3] = field[6][6];
        field[7][4] = field[6][7];
        field[7][5] = field[6][8];
        field[7][6] = field[6][0];
        field[7][7] = field[6][1];
        field[7][8] = field[6][2];

        field[8][0] = field[7][3];
        field[8][1] = field[7][4];
        field[8][2] = field[7][5];
        field[8][3] = field[7][6];
        field[8][4] = field[7][7];
        field[8][5] = field[7][8];
        field[8][6] = field[7][0];
        field[8][7] = field[7][1];
        field[8][8] = field[7][2];

        int[] FinalField=new int[81];

        for(i=0;i<sz;i++){
            for(int j=0;j<sz;j++){
                FinalField[i*9+j]=field[i][j];

            }

        }

        return FinalField;
    }

    public void loseGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(GameLevel.this);
        builder.setTitle("You Lose!")


                .setCancelable(false)
                .setNegativeButton(
                                "To the main menu",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                try {
                                    Intent intent = new Intent(GameLevel.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }catch (Exception e){}
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void winGame(Button[] buttons,int[] Field,int sz){
        int tmp=0;

        for(int i=0;i<sz;i++){
            if(buttons[i].getText()!=(Integer.toString(Field[i]))){
                tmp=1;
            }
        }
              if(tmp==0) {


                  AlertDialog.Builder builder = new AlertDialog.Builder(GameLevel.this);
                  builder.setTitle("You Win!")


                          .setCancelable(false)

                          .setNegativeButton(
                                          "To the main menu",
                                  new DialogInterface.OnClickListener() {
                                      public void onClick(DialogInterface dialog, int id) {
                                          dialog.cancel();
                                          try {
                                              Intent intent = new Intent(GameLevel.this, MainActivity.class);
                                              startActivity(intent);
                                              finish();
                                          } catch (Exception e) {
                                          }
                                      }
                                  });
                  saveStat();
                  AlertDialog alert = builder.create();
                  alert.show();
              }
    }

    public int mistakes(Button buttonMistakes){
        if(Mistakes>0){
        Mistakes--;
        buttonMistakes.setText("MISTAKES x"+Mistakes+"");
        return 0;}
       else return 1;

    }

    public String retArrUser(Button[] buttons,int sz){
        String tmp="";

        for(int i=0;i<sz;i++){
            if(((TextView)buttons[i]).getText().toString()==" "){
                tmp+="0";

            }else
           tmp+=((TextView)buttons[i]).getText().toString()+"";


        }
        return tmp;
    }

    public long retMinutesToL(long minutes,long secundes){
        long L=0;
        L+=minutes*60000;
        L+=secundes*1000;


        return L;


    }

    public String retField(int[] Field,int sz){
        String tmp="";

        for(int i=0;i<sz;i++){
            tmp+=Integer.toString(Field[i])+"";


        }
        return tmp;

    }

    public void saveGame(String fileName,int mode,int[] Field,int[]firstFields,Button[] buttons,int sz){
        try {

            BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(openFileOutput(fileName,MODE_PRIVATE)));
            writer.write(mode+"/"+Integer.toString(Mistakes)+"/"+retArrUser(buttons,sz)+"/"+retField(Field,sz)+"/"+retField(firstFields,sz)+"/"+retMinutesToL(minutes,secundes));
            writer.close();

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String loadGameFromFile(String fileName){

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

    public void loadGame(String fileName){
        String line=loadGameFromFile(fileName);
        int sz=81;
        int modeGame=Character.getNumericValue(line.charAt(0));
        int mistakesPlayer=Character.getNumericValue(line.charAt(2));
        int[] fieldPlayer=new int[sz];
        for(int i=0;i<sz;i++){
            fieldPlayer[i]=Character.getNumericValue(line.charAt(i+4));
        }
        int[] fieldGame=new int[sz];
        for(int i=0;i<sz;i++){
            fieldGame[i]=Character.getNumericValue(line.charAt(i+5+81));
        }
        int[] firstsField=new int[sz];
        for(int i=0;i<sz;i++){
            firstsField[i]=Character.getNumericValue(line.charAt(i+6+81+81));
        }
        int minutesInGame=Integer.parseInt(line.substring(line.lastIndexOf('/') + 1, line.length()));

        mode=modeGame;
        Mistakes=mistakesPlayer;
        Field=fieldGame;
        FieldToGame=fieldPlayer;
        minutesGame=minutesInGame;
        firstField=firstsField;


    }

    public void saveStat(){
        String lineSave="";
        long min;
        long sec;
        int countToSave=0;

        long minutesToSave=0;
        long secundesToSave=0;


        if(mode==1){lineSave="l0wStat";
            min=15-minutes;

            sec=60-secundes;

            File fileLow = new File(getFilesDir() + "/l0wStat");
            boolean existsLow = fileLow.exists();
            if(existsLow){
                String line= loadGameFromFile(lineSave);

                int minutesFromFile=Integer.parseInt(line.substring(0,2));
                int secundesFromFile=Integer.parseInt(line.substring(3,5));
                int countFromFile=Integer.parseInt(line.substring(6));


                countToSave=countFromFile+1;
                if(min<minutesFromFile){
                    minutesToSave=min;
                    secundesToSave=sec;

                }else if((min==minutesFromFile)&(sec<secundesFromFile)) {
                    minutesToSave=min;
                    secundesToSave=sec;

                }

            }
            else{
                minutesToSave=min;
                secundesToSave=sec;
                countToSave=1;

            }

        }
        if(mode==2){lineSave="mediumStat";
            min=30-minutes;
            sec=60-secundes;

            File fileMed = new File(getFilesDir() + "/mediumStat");
            boolean existsMedium = fileMed.exists();
            if(existsMedium){ String line= loadGameFromFile(lineSave);
            int minutesFromFile=Integer.parseInt(line.substring(0,2));
            int secundesFromFile=Integer.parseInt(line.substring(3,5));
            int countFromFile=Integer.parseInt(line.substring(6,line.length()));
            countToSave=countFromFile+1;
            if(min<minutesFromFile){
                minutesToSave=min;
                secundesToSave=sec;

            }else if((min==minutesFromFile)&(sec<secundesFromFile)) {
                minutesToSave=min;
                secundesToSave=sec;

            }

        }
            else{
                minutesToSave=min;
                secundesToSave=sec;
                countToSave=1;

            }

        }
        if(mode==3){lineSave="hardStat";
            min=45-minutes;
            sec=60-secundes;

            File fileHar = new File(getFilesDir() + "/hardStat");
            boolean existsHard = fileHar.exists();
            if(existsHard) {String line= loadGameFromFile(lineSave);
            int minutesFromFile=Integer.parseInt(line.substring(0,2));
            int secundesFromFile=Integer.parseInt(line.substring(3,5));
            int countFromFile=Integer.parseInt(line.substring(6,line.length()));
            countToSave=countFromFile+1;
            if(min<minutesFromFile){
                minutesToSave=min;
                secundesToSave=sec;

            }else if((min==minutesFromFile)&(sec<secundesFromFile)) {
                minutesToSave=min;
                secundesToSave=sec;

            }

        }
            else{
                minutesToSave=min;
                secundesToSave=sec;
                countToSave=1;

            }

        }
        if(mode==4){lineSave="extraStat";
            min=60-minutes;
            sec=60-secundes;

            File fileExt = new File(getFilesDir() + "/extraStat");
            boolean existsExtra = fileExt.exists();
            if(existsExtra) {String line= loadGameFromFile(lineSave);
            int minutesFromFile=Integer.parseInt(line.substring(0,2));
            int secundesFromFile=Integer.parseInt(line.substring(3,5));
            int countFromFile=Integer.parseInt(line.substring(6,line.length()));
            countToSave=countFromFile+1;
            if(min<minutesFromFile){
                minutesToSave=min;
                secundesToSave=sec;

            }else if((min==minutesFromFile)&(sec<secundesFromFile)) {
                minutesToSave=min;
                secundesToSave=sec;

            }

        }
            else{
                minutesToSave=min;
                secundesToSave=sec;
                countToSave=1;

            }
        }

        String minSave=""+Long.toString(minutesToSave);
        String secSave=""+Long.toString(secundesToSave);

        if(minutesToSave<10) minSave="0"+Long.toString(minutesToSave);
        if(secundesToSave<10) secSave="0"+Long.toString(secundesToSave);

        try {

            BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(openFileOutput(lineSave,MODE_PRIVATE)));
            writer.write(minSave+"/"+secSave+"/"+countToSave);
            writer.close();

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}




