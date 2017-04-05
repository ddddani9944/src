package com.kftc.bfop.useorgsampleapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kftc.bfop.useorgsampleapp.R;

/**
 * Created by KFTC Mobile Development Team on 2017. 1. 16..
 *
 * @author Sangyeop Han
 */
public class TokenRequestViewAppActivity extends Activity {

    private EditText m_etRspCode;
    private EditText m_etRspMsg;
    private EditText m_etAuthorizationCode;
    private EditText m_etScope;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_request_view_app);

        m_etRspCode = (EditText) findViewById(R.id.etRspCode);
        m_etRspMsg = (EditText) findViewById(R.id.etRspMsg);
        m_etAuthorizationCode = (EditText) findViewById(R.id.etAuthorizationCode);
        m_etScope = (EditText) findViewById(R.id.etScope);

        Intent intent = getIntent();
        if (intent == null){
            return;
        }

        if (intent.hasExtra("RspCode")) m_etRspCode.setText(intent.getStringExtra("RspCode"));
        if (intent.hasExtra("RspMsg")) m_etRspMsg.setText(intent.getStringExtra("RspMsg"));
        if (intent.hasExtra("AuthorizationCode")) m_etAuthorizationCode.setText(intent.getStringExtra("AuthorizationCode"));
        if (intent.hasExtra("Scope")) m_etScope.setText(intent.getStringExtra("Scope"));
    }

    public void onRequestToken(View v) {

        String rspCode = m_etRspCode.getText().toString();
        String rspMsg = m_etRspMsg.getText().toString();
        String authCode = m_etAuthorizationCode.getText().toString();
        String scope = m_etScope.getText().toString();

        if (rspCode.equals("") || rspMsg.equals("") || authCode.equals("")) {
            Toast.makeText(this, "일부 항목이 입력되지 않았습니다.", Toast.LENGTH_LONG);
            return;
        }

        Intent intent = new Intent(this, TokenRequestSenderActivity.class);
        intent.putExtra("RspCode", rspCode);
        intent.putExtra("RspMsg", rspMsg);
        intent.putExtra("AuthorizationCode", authCode);
        intent.putExtra("Scope", scope);
        intent.putExtra("Invoker", "APP");
        startActivity(intent);
    }
}