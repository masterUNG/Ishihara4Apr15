package appewtc.masterung.ishihara4apr15;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //Explicit ประกาศตัวแปร นะคะ
    private TextView txtQuestion;
    private ImageView imvIshihara;
    private RadioGroup ragChoice;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private Button btnAnswer;
    private int intRadio, intIndex;
    private MyModel objMyModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //InitialWidget
        initialWidget();

        //Button Controller
        buttonController();

        //Radio Controller
        radioController();

        //Interface my MyModel
        aboutMyModel();

    }   // onCreate

    private void aboutMyModel() {
        objMyModel = new MyModel();
        objMyModel.setOnMyModelChangeListener(new MyModel.OnMyModelChangeListener() {
            @Override
            public void onMyModelChangeListener(MyModel myModel) {

                changeView(myModel.getIntButton());

            }   // event
        });
    }

    private void changeView(int intButton) {

        // Change Image
        int intDrawable[] = new int[]{R.drawable.ishihara_01, R.drawable.ishihara_02,
                R.drawable.ishihara_03, R.drawable.ishihara_04, R.drawable.ishihara_04,
                R.drawable.ishihara_06, R.drawable.ishihara_07, R.drawable.ishihara_08,
                R.drawable.ishihara_09, R.drawable.ishihara_10};
        imvIshihara.setImageResource(intDrawable[intButton]);

        // Change Choice
        int intArrayChoice[] = new int[]{R.array.times1, R.array.times2,
                R.array.times3, R.array.times4, R.array.times5, R.array.times6,
                R.array.times7, R.array.times8, R.array.times9, R.array.times10};

        String strChoice[] = new String[4];
        strChoice = getResources().getStringArray(intArrayChoice[intButton]);
        radChoice1.setText(strChoice[0]);
        radChoice2.setText(strChoice[1]);
        radChoice3.setText(strChoice[2]);
        radChoice4.setText(strChoice[3]);
    }   // changeView



    private void radioController() {
        ragChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                //Sound Effeft
                MediaPlayer soundRadio = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_shut);
                soundRadio.start();

                //Setup intRadio
                switch (i) {
                    case R.id.radioButton:
                        intRadio = 1;
                        break;
                    case R.id.radioButton2:
                        intRadio = 2;
                        break;
                    case R.id.radioButton3:
                        intRadio = 3;
                        break;
                    case R.id.radioButton4:
                        intRadio = 4;
                        break;
                    default:
                        intRadio = 0;
                        break;
                }   // switch

            }   // event
        });
    }

    private void buttonController() {
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Sound Effect
                MediaPlayer soundButton = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_long);
                soundButton.start();

                //Check Zero
                checkZero();

            }   // event
        });
    }

    private void checkZero() {
        if (intRadio == 0) {
            Toast.makeText(MainActivity.this, "กรุณาตอบคำถาม เว้ยเห้ย", Toast.LENGTH_SHORT).show();
        } else {

            checkTimes();

        }
    }

    private void checkTimes() {

        if (intIndex == 9) {

            //Intent to ShowScore
            Intent objIntent = new Intent(MainActivity.this, ShowScoreActivity.class);
            startActivity(objIntent);
            finish();

        } else {

            //Call View
            txtQuestion.setText(Integer.toString(intIndex + 2) + ". What is this ?");
            intIndex += 1;

            //Call Model
            objMyModel.setIntButton(intIndex);


        }

    }

    private void initialWidget() {
        txtQuestion = (TextView) findViewById(R.id.textView);
        imvIshihara = (ImageView) findViewById(R.id.imageView);
        ragChoice = (RadioGroup) findViewById(R.id.ragChoice);
        radChoice1 = (RadioButton) findViewById(R.id.radioButton);
        radChoice2 = (RadioButton) findViewById(R.id.radioButton2);
        radChoice3 = (RadioButton) findViewById(R.id.radioButton3);
        radChoice4 = (RadioButton) findViewById(R.id.radioButton4);
        btnAnswer = (Button) findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class นี่คือคลาสหลัก
