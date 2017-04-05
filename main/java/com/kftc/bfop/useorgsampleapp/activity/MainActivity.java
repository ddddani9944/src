package com.kftc.bfop.useorgsampleapp.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.kftc.bfop.useorgsampleapp.R;
import com.kftc.bfop.useorgsampleapp.util.StringUtil;

/**
 * MainActivity
 */
public class MainActivity extends Activity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * 이용기관 샘플앱 실행시 최초 수행되는 이벤트
     *
     * @author 오토에버시스템 강현경, 김민재(marchis@naver.com)
     * @since 2016.05.29.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        //getPreferences();

        parseResult();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * 사용자 로그인 연결 (웹 방식)
     */
    public void onWebLoginAgree(View v) {
        Intent intent = new Intent(this, WebLoginAgreeActivity.class);
        startActivity(intent);
    }

    /**
     * 사용자 로그인 연결 (앱 방식)
     */
    public void onAppLoginAgree(View v) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("kftcbfop://authorize?response_type=code" +
                    "&client_id=" + StringUtil.getPropString("APP_KEY") +
                    "&redirect_uri=" + StringUtil.getPropString("APP_CALLBACK_URL")));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.kftc.openplatform"));
            startActivity(intent);
        }
    }

    /**
     * 계좌등록 (웹 방식)
     */
    public void onWebRegister(View v) {
        Intent intent = new Intent(this, WebRegisterAccountActivity.class);
        startActivity(intent);
    }

    /**
     * 계좌등록 (앱 방식)
     */
    public void onAppRegister(View v) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(
                    "kftcbfop://register?response_type=code&client_id=" + StringUtil.getPropString("APP_KEY") +
                            "&scope=" + StringUtil.getPropString("SCOPE") + "&redirect_uri=" + StringUtil.getPropString("APP_CALLBACK_URL")));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=패키지명"));
            startActivity(intent);
        }
    }

    /**
     * 계좌등록 확인 (웹 Only)
     */
    public void onWebAuthorizeAccount(View v) {
        Intent intent = new Intent(this, WebAuthorizeAccountActivity.class);
        startActivity(intent);
    }

    /**
     * 설정 버튼 클릭 시
     */
    public void onSetting(View v) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    /**
     * 포커스를 잃었을때, 돌아왔을때 발생되는 이벤트 처리
     *
     * @author 오토에버시스템 강현경, 김민재(marchis@naver.com)
     * @since 2016.05.31.
     */
    @Override
    protected void onResume() {
        super.onResume();
        //getPreferences();
    }

    /**
     * 앱-to-앱 호출 결과 처리
     * 1. AndroidManifest.xml 파일을 통해 결과 값을 받고자 하는 Activity의 URL Scheme 지정 필수
     * 2. onCreate 함수에서 아래 함수 호출하여 결과값 파싱
     * 오픈플랫폼 앱에서는 항상 FLAG_ACTIVITY_NEW_TASK, FLAG_ACTIVITY_CLEAR_TOP이 지정되어 이용기관 앱 호출
     *
     * @author 금융결제원 한상엽
     * @since 2017.01.25.
     */
    public void parseResult() {

        Intent intent = getIntent();
        if (intent == null || intent.getData() == null) return;

        if (intent.getAction().equals(Intent.ACTION_VIEW)) {
            Uri uri = intent.getData();
            String rspCode = uri.getQueryParameter("rsp_code");
            String rspMsg = uri.getQueryParameter("rsp_msg");
            String authCode = uri.getQueryParameter("authorization_code");
            String scope = uri.getQueryParameter("scope");
            /*Toast.makeText(this, String.format("코드: %s\n메시지: %s\n인증 코드: %s\n인증 범위: %s", rspCode,
                    rspMsg, authCode, scope), Toast.LENGTH_LONG).show();*/

            intent = new Intent(this, TokenRequestViewAppActivity.class);
            intent.putExtra("RspCode", rspCode);
            intent.putExtra("RspMsg", rspMsg);
            intent.putExtra("AuthorizationCode", authCode);
            intent.putExtra("Scope", scope);
            startActivity(intent);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}
