package com.kftc.bfop.useorgsampleapp.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.kftc.bfop.useorgsampleapp.App;
import com.kftc.bfop.useorgsampleapp.R;
import com.kftc.bfop.useorgsampleapp.util.Constants;
import com.kftc.bfop.useorgsampleapp.util.MessageUtil;
import com.kftc.bfop.useorgsampleapp.util.StringUtil;

/**
 * Created by KFTC Mobile Development Team on 2017. 1. 16..
 *
 * @author Sangyeop Han
 */
public class SettingActivity extends Activity {

    private RadioGroup rg_svr;
    private EditText m_etAppKey;
    private EditText m_etAppSecret;
    private EditText m_etWebCallbackUrl;
    private EditText m_etCallbackUrl;
    private Spinner m_spScope;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        rg_svr = (RadioGroup) findViewById(R.id.rgSvr);
        m_etAppKey = (EditText) findViewById(R.id.etAppKey);
        m_etAppSecret = (EditText) findViewById(R.id.etAppSecret);
        m_etWebCallbackUrl = (EditText) findViewById(R.id.etWebCallbackUrl);
        m_etCallbackUrl = (EditText) findViewById(R.id.etCallbackUrl);
        m_spScope = (Spinner) findViewById(R.id.spScope);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sp_scope_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_spScope.setAdapter(adapter);

        loadPreferences();
    }

    /**
     * input form 에 SharedPreferences 값 로딩
     */
    private void loadPreferences() {

        setRadioSvrCheckedFromPref();
        m_etAppKey.setText(StringUtil.getPropString("APP_KEY"));
        m_etAppSecret.setText(StringUtil.getPropString("APP_SECRET"));
        m_etWebCallbackUrl.setText(StringUtil.getPropString("WEB_CALLBACK_URL"));
        m_etCallbackUrl.setText(StringUtil.getPropString("APP_CALLBACK_URL"));
        m_spScope.setSelection(StringUtil.getPropString("SCOPE").equals("inquiry") ? 0 : 1);
    }

    /**
     * 호출서버 라디오버튼 클릭상태 반영
     */
    private void setRadioSvrCheckedFromPref(){

        int id = 0;
        String env = App.getEnv();
        switch(env){
            case Constants.ENV_TEST: id = R.id.radioSvr_TEST; break;
            case Constants.ENV_PRD: id = R.id.radioSvr_PRD; break;
        }
        rg_svr.check(id);
    }

    /**
     * 설정값을 SharedPreferences 에 저장
     *
     * @param v
     */
    public void onSave(View v) {

        SharedPreferences.Editor editor = App.getPref().edit();

        // 클릭된 호출서버 라디오버튼에서 선택한 환경값 획득
        String radioSvrId = getResources().getResourceEntryName(rg_svr.getCheckedRadioButtonId());
        String env = radioSvrId.substring(radioSvrId.indexOf('_')+1);
        editor.putString("ENV", env);

        editor.putString("APP_KEY", m_etAppKey.getText().toString());
        editor.putString("APP_SECRET", m_etAppSecret.getText().toString());
        editor.putString("WEB_CALLBACK_URL", m_etWebCallbackUrl.getText().toString());
        editor.putString("APP_CALLBACK_URL", m_etCallbackUrl.getText().toString());
        editor.putString("SCOPE", m_spScope.getSelectedItem().toString());

        editor.apply();

        MessageUtil.showToast("저장되었습니다.", 1500);
    }

    /**
     * 현재 SharedPreferences 에 저장되어 있는 설정값들을, Constants의 기본값으로 overwrite 한다.
     *
     * @param v
     */
    public void onReset(View v){

        // Dialog를 사용하여 confirm창 처럼 활용한다.
        MessageUtil.getDialogBuilder("", "초기화 하시겠습니까?", true, this)
        .setPositiveButton("초기화", new DialogInterface.OnClickListener() {
            // 초기화 선택시
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = App.getPref().edit();
                editor.putString("ENV", Constants.ENV_DEFAULT);
                editor.putString("APP_KEY", Constants.APP_KEY);
                editor.putString("APP_SECRET", Constants.APP_SECRET);
                editor.putString("WEB_CALLBACK_URL", Constants.WEB_CALLBACK_URL);
                editor.putString("APP_CALLBACK_URL", Constants.APP_CALLBACK_URL);
                editor.putString("SCOPE", Constants.SCOPE);
                editor.apply();
                MessageUtil.showToast("초기화되었습니다.", 1500);
                loadPreferences();
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            // 취소 선택시
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        }).create().show();
    }

    /**
     * 사용자 로그인 세션이 계속 남아있어서, 제거를 위해 추가한 메서드
     *
     * @param v
     */
    public void onSessionCookieRemoveClicked(View v) {

        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeSessionCookies(new ValueCallback<Boolean>() {
                @Override
                public void onReceiveValue(Boolean value) {
                Log.d("", "## 롤리팝 이상 버전의 removeSessionCookie() 호출 후");
                }
            });

        }else{
            cookieManager.removeSessionCookie();
            Log.d("", "## 롤리팝 미만 버전의 removeSessionCookie() 호출 후");
        }
        MessageUtil.showToast("세션정보가 삭제되었습니다.", 1500);
    }

    /**
     * 호출서버 라디오버튼 클릭 이벤트 핸들러
     *
     * @param view
     */
    public void onRadioSvrClicked(View view) {
        // nothing to do
    }

}