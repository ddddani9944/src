package com.kftc.bfop.useorgsampleapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kftc.bfop.useorgsampleapp.R;

/**
 *
 */
public class TokenRequestViewWebActivity extends Activity {

    private EditText m_etAuthorizationCode;
    private EditText m_etScope;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_request_view_web);

        m_etAuthorizationCode = (EditText) findViewById(R.id.etAuthorizationCode);
        m_etScope = (EditText) findViewById(R.id.etScope);

        Intent intent = getIntent();
        if (intent == null){
            return;
        }

        if (intent.hasExtra("AuthorizationCode")) m_etAuthorizationCode.setText(intent.getStringExtra("AuthorizationCode"));
        if (intent.hasExtra("Scope")) m_etScope.setText(intent.getStringExtra("Scope"));
    }

    public void onRequestToken(View v) {

        String authCode = m_etAuthorizationCode.getText().toString();
        String scope = m_etScope.getText().toString();

        if (authCode.equals("")) {
            Toast.makeText(this, "일부 항목이 입력되지 않았습니다.", Toast.LENGTH_LONG);
            return;
        }

        Intent intent = new Intent(this, TokenRequestSenderActivity.class);
        intent.putExtra("RspCode", "");
        intent.putExtra("RspMsg", "");
        intent.putExtra("AuthorizationCode", authCode);
        intent.putExtra("Scope", scope);
        intent.putExtra("Invoker", "WEB"); // token 요청시 redirect_uri 분기를 위해서 설정
        startActivity(intent);
    }
}