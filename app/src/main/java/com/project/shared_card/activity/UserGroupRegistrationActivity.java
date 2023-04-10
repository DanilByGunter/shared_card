package com.project.shared_card.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.shared_card.R;
import com.project.shared_card.converter.DbBitmapUtility;
import com.project.shared_card.database.ImplDB;
import com.project.shared_card.database.entity.group.GroupEntity;
import com.project.shared_card.model.SignUp;
import java.io.FileOutputStream;
import java.io.IOException;

public class UserGroupRegistrationActivity extends AppCompatActivity {
    static String TEXT_VIEW_KEY = "1";
    static String EDIT_VIEW_KEY = "2";
    static String  SCREEN_REGISTRATION= "3";
    ImplDB db;
    ImageView image;
    Button button;
    TextView textView;
    EditText editText;
    Boolean ruleRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_group_registration);
        Bundle arguments = getIntent().getExtras();
        String textForTextView = arguments.get(TEXT_VIEW_KEY).toString();
        String textForEditView = arguments.get(EDIT_VIEW_KEY).toString();
        ruleRegistration = arguments.getBoolean(SCREEN_REGISTRATION);
        db = new ImplDB(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        image = findViewById(R.id.image_user_group);
        textView = findViewById(R.id.text_choice_of_avatar);
        textView.setText(textForTextView);
        editText = findViewById(R.id.edit_text_your_name);
        editText.setHint(textForEditView);
        button = findViewById(R.id.button_continue);
        button.setOnClickListener(this::onClick);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityIfNeeded(photoPickerIntent,1);
            }
        });
    }
    public void onClick(View v) {
        if (editText.getText().toString().equals("")){
            return;
        }
        if(ruleRegistration) {
            if (image.getDrawable() == null) {
                byte[] picture = DbBitmapUtility.getBytes(((BitmapDrawable) getDrawable(R.drawable.defaul_avatar).getCurrent()).getBitmap());
                FileOutputStream fos;
                try {
                    fos = openFileOutput("me.png", MODE_PRIVATE);
                    fos.write(picture);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //Запрос на сервер возврат последнего id пользователя
            //Сохраняем id в ресурсах
            long idUser = 1;
            SignUp user = new SignUp(idUser, editText.getText().toString(), "me.png");
            db.getUserNameRepository().createUser(user);
            //Сохраняем id в ресурсах
            long idGroup = -1;
            SignUp group = new SignUp(idGroup, "", "res/drawable-v24/grocery_card.png");
            db.getGroupNameRepository().createGroups(group);

            db.getGroupRepository().createRepository(new GroupEntity(idUser, idGroup, true));
        }
        else{
            if (image.getDrawable() == null) {
                byte[] picture = DbBitmapUtility.getBytes(((BitmapDrawable) getDrawable(R.drawable.defaul_avatar).getCurrent()).getBitmap());
                FileOutputStream fos;
                try {
                    fos = openFileOutput(String.format("group/{0}.png",getString(R.string.select_group)), MODE_PRIVATE);
                    fos.write(picture);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //Запрос на сервер возврат последнего id группы
            //Сохраняем id в ресурсах
            long idGroup = 1;
            SignUp group = new SignUp(idGroup, editText.getText().toString(), String.format("group/{0}.png",getString(R.string.select_group)));
            db.getGroupNameRepository().createGroups(group);
        }
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            String FilePath = data.getDataString();
            image.setImageURI(Uri.parse(FilePath));
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                        byte[] picture = DbBitmapUtility.getBytes(((BitmapDrawable) image.getDrawable()).getBitmap());
                        FileOutputStream fos;
                        try {
                            if(ruleRegistration)
                                fos = openFileOutput("me.png", MODE_PRIVATE);
                            else
                                fos = openFileOutput(String.format("group/{0}.png",getString(R.string.select_group)), MODE_PRIVATE);
                            fos.write(picture);
                            }
                             catch (IOException e){
                                throw new RuntimeException(e);
                            }
                }
            });
            thread.start();
        }
    }
}


