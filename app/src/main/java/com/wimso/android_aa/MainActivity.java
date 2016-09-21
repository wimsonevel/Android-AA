package com.wimso.android_aa;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wimso.android_aa.model.Post;
import com.wimso.android_aa.network.ApiService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.post_text) EditText postText;
    @ViewById(R.id.post_title) TextView postTitle;
    @ViewById(R.id.post_body) TextView postBody;

    @RestService ApiService apiService;

    private ProgressDialog progressDialog;

    @AfterViews
    public void bindViews() {
        // Default Text
        postText.setText("1");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
    }

    @Click(R.id.btn_post)
    void getPostClicked() {
        progressDialog.show();

        if(!TextUtils.isEmpty(postText.getText())) {
            requestPost(postText.getText().toString());
        }
    }

    @Background
    void requestPost(String id) {
        Post post = apiService.getPost(id);
        updateViews(post);
    }

    @UiThread
    void updateViews(Post post) {
        progressDialog.dismiss();

        if(post == null) {
            Toast.makeText(this, "Cannot load data", Toast.LENGTH_SHORT).show();
            return;
        }

        postTitle.setText(post.getTitle());
        postBody.setText(post.getBody());
    }

}