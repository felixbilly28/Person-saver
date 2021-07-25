package com.wv.personsaver;


import androidx.appcompat.app.AppCompatActivity;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Button btn_add, btn_mix, btn_clear,btn_reset;
    private EditText firstText, lastText, yearOfBirthText;
    private CheckBox genderCheckBoxMale, genderCheckBoxFemale;
    private TextView screenview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingUIWidgets();


        genderCheckBoxMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (genderCheckBoxMale.isChecked()) {
                    genderCheckBoxFemale.setChecked(false);
                }
            }
        });
        genderCheckBoxFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if (genderCheckBoxFemale.isChecked()) {
                    genderCheckBoxMale.setChecked(false);
                }
            }
        });
    }

    private void bindingUIWidgets() {
        btn_reset = findViewById(R.id.btn_reset);
        btn_add = findViewById(R.id.btn_add);
        btn_clear = findViewById(R.id.btn_clear);
        btn_mix = findViewById(R.id.btn_mix);
        firstText = findViewById(R.id.editfirstname);
        lastText = findViewById(R.id.editlastname);
        genderCheckBoxFemale = findViewById(R.id.editfemale);
        genderCheckBoxMale = findViewById(R.id.editmale);
        yearOfBirthText = findViewById(R.id.edityob);
        screenview = findViewById(R.id.screenview);
    }

    private String readGenderCheckBox() {
        if(genderCheckBoxMale.isChecked()){
            genderCheckBoxFemale.setChecked(false);
            return "Male";
        }
        if(genderCheckBoxFemale.isChecked()){
            return "Female";
        }
        return null;
    }





    public void addForm(View view) {
        if(validatingFormEntriesCheck()){
            DAO.getInstance().getPersonList().add(new Person(firstText.getText().toString(),
                    lastText.getText().toString(),readGenderCheckBox(),
                    convertEditTextToInteger(yearOfBirthText)));
            popUpMessage(firstText, lastText);
            reset(this.btn_reset);
        }

        if(DAO.getInstance().listSize()>1)
        {
            Compare();
        }
    }

    private boolean validatingFormEntriesCheck() {
        return isValidatedData();
    }

    private boolean isValidatedData() {
        boolean validatedData = false;
        if (!firstText.getText().toString().isEmpty() && !lastText.getText().toString().isEmpty() && readGenderCheckBox() != null && genderCheckBoxMale.isChecked() || genderCheckBoxFemale.isChecked()) {
            validatedData = true;
        }
            return validatedData;

    }



    private void popUpMessage(EditText editText1, EditText editText2) {
        Toast.makeText(this,"Saved "+ editText1.getText().toString()+
                " "+editText2.getText().toString(),Toast.LENGTH_SHORT).show();
    }

    private int convertEditTextToInteger(EditText year) {
        return Integer.valueOf(year.getText().toString());
    }

    public void mixForm(View view) {
         int sixe;
        sixe = DAO.getInstance().listSize();
            StringBuilder sb = new StringBuilder();
            boolean appendSeparator = false;
            for(int y=0; y < sixe; y++){
                if (appendSeparator)
                appendSeparator = true;
                sb.append(y+1 +" - " +DAO.getInstance().getPersonList().get(y).getFname() + " " +DAO.getInstance().getPersonList().get(y).getLname() +" " + DAO.getInstance().getPersonList().get(y).getGender() +" " + DAO.getInstance().getPersonList().get(y).getYob() + "\n");
            }
        screenview.setText(sb.toString());
    }

    public void Compare(){
        int realsize = DAO.getInstance().listSize();
        int lastage = DAO.getInstance().getPersonList().get(realsize-1).getYob();
        int blastage = DAO.getInstance().getPersonList().get(realsize-2).getYob();
        if (lastage>blastage){
            popUpMessage2(DAO.getInstance().getPersonList().get(realsize-1).getFname() +" est moins agé");
        }else if ( lastage<blastage){
            popUpMessage2(DAO.getInstance().getPersonList().get(realsize-1).getFname() +" est plus agé");
        }else {
            popUpMessage2("Les deux derniers ont le meme age");
        }
    }

    private void popUpMessage2(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void reset(View view){
        firstText.setText("");
        lastText.setText("");
        yearOfBirthText.setText("");
        screenview.setText("");
    }

    public void Clear(View view) {
        if (DAO.getInstance().listSize() > 0) {
            DAO.getInstance().ClearList();
            popUpMessage2("liste vidée avec succes");
            screenview.setText("");
        }else{
            popUpMessage2("La liste est déjà vide");
        }
    }

}