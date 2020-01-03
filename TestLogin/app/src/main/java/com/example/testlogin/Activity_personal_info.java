package com.example.testlogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.GlobalParams.GlobalParam;
import com.example.HttpUtil.AskForInternet;
import com.example.HttpUtil.ConstsUrl;
import com.example.HttpUtil.ImageLoader;
import com.example.HttpUtil.StreamTool;
import com.example.entity.User;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Activity_personal_info extends AppCompatActivity implements View.OnClickListener{

    private static int RESULT_LOAD_IMAGE = 1;//读取照片的标志，将其设为1
    private String pathString;//这是上传图片的url

    private Toolbar toolbar;
    private User user;
    private String email,name,sex,age,signature,photo_url,userID;
    private ImageView photo_view;
    private TextView email_view,name_view,sex_view,age_view,signature_view;
    private ImageLoader imageLoader;
    private Intent intent;

    private Bitmap bitmap;

    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        toolbar=findViewById(R.id.personal_info_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        intiview();
        intiEvent();
    }

    private void intiEvent() {
        photo_view.setOnClickListener(this);
        name_view.setOnClickListener(this);
        sex_view.setOnClickListener(this);
        age_view.setOnClickListener(this);
        signature_view.setOnClickListener(this);
    }

    private void intiview() {
//        intent=getIntent();
//        user= (User) intent.getSerializableExtra("user");
        user= GlobalParam.user;
        email=user.getEmail();
        name=user.getUsername();
        sex=user.getSex();
        age=user.getBirth();
        signature=user.getSignature();
        userID=String.valueOf(user.getUserId());
        photo_url=ConstsUrl.BASE_URL+user.getPhoto();

        photo_view=findViewById(R.id.personal_image);
        email_view=findViewById(R.id.personal_email);
        name_view=findViewById(R.id.personal_name);
        sex_view=findViewById(R.id.personal_sex) ;
        age_view=findViewById(R.id.personal_age);
        signature_view=findViewById(R.id.personal_signature);

        photo_view.setTag(photo_url);
        imageLoader=new ImageLoader();
        imageLoader.showImageByAsyncTask(photo_view,photo_url);

        email_view.setText(email);
        name_view.setText(name);
        sex_view.setText(sex);
        age_view.setText(age);
        signature_view.setText(signature);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home://返回箭头
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_image:
                choosePhoto();
                break;
            case R.id.personal_name:
                showEditDialog("username");
                break;
            case R.id.personal_sex:
                showSexDialog();
                break;
            case R.id.personal_age:
                break;
            case R.id.personal_signature:
                showEditDialog("signature");
                break;
        }
    }

    //选择照片
    private void choosePhoto() {
        intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        this.startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }

    private void showEditDialog(final String str) {
        final EditText editText=new EditText(Activity_personal_info.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (str.equals("username")) {
            editText.setText(name);
            builder.setTitle("修改你的昵称");
        }else if (str.equals("signature")){
            editText.setText(signature);
            builder.setTitle("修改签名");
        }
        builder.setView(editText);
        builder.setCancelable(false);
        builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String me=editText.getText().toString();
                List<NameValuePair> paras=new ArrayList<NameValuePair>();
                paras.add(new BasicNameValuePair("operation","upuser"));
                paras.add(new BasicNameValuePair("attribute",str));
                paras.add(new BasicNameValuePair("value",me));
                paras.add(new BasicNameValuePair("userID",userID));
                new MyAsyncTask().execute(paras);
                if (str.equals("username")) {
                    name_view.setText(me);
                    GlobalParam.user.setUsername(me);
                }else if (str.equals("signature")){
                    signature_view.setText(me);
                    GlobalParam.user.setSignature(me);
                }
                refresh();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog=builder.create();
        alertDialog.show();
    }

    private void showSexDialog() {
        final String item[]={"男","女"};
        int index=0;//默认性别为男
        if (sex!=null)
            index=sex.equals("男")? 0:1;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setSingleChoiceItems(item,index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<NameValuePair> paras=new ArrayList<NameValuePair>();
                paras.add(new BasicNameValuePair("operation","upuser"));
                paras.add(new BasicNameValuePair("attribute","sex"));
                paras.add(new BasicNameValuePair("value",item[which].toString()));
                paras.add(new BasicNameValuePair("userID",userID));
                new MyAsyncTask().execute(paras);
                sex_view.setText(item[which].toString());
                GlobalParam.user.setSex(item[which]);
                refresh();
                alertDialog.dismiss();
            }
        });
        alertDialog=builder.create();
        alertDialog.show();
    }


    /************************************刷新信息**************************************************/
    private void refresh(){
        intiview();
        //发送广播，此广播注册在MainActivity中
        Intent intent =new Intent("update_info");
        sendBroadcast(intent);
    }
    /************************************网络相关**************************************************/
    public class MyAsyncTask extends AsyncTask<List<NameValuePair>,Void,String>{

        @Override
        protected String doInBackground(List<NameValuePair>[] lists) {
            return AskForInternet.post(ConstsUrl.LOGIN_URL,lists[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                int code = jsonObject.getInt("code");
                if (code==0)
                    Toast.makeText(Activity_personal_info.this, "修改失败",
                            Toast.LENGTH_SHORT).show();
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
