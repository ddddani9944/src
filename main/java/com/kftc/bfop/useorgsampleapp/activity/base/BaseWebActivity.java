package com.kftc.bfop.useorgsampleapp.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kftc.bfop.useorgsampleapp.App;
import com.kftc.bfop.useorgsampleapp.R;
import com.kftc.bfop.useorgsampleapp.activity.TokenRequestViewWebActivity;
import com.kftc.bfop.useorgsampleapp.util.Constants;
import com.kftc.bfop.useorgsampleapp.util.StringUtil;

/**
 * Created by LeeHyeonJae on 2017-02-23.
 *
 * WebView 를 사용하는 Activity들의 공통기능을 모아놓을 목적으로 생성한 클래스
 *
 */
public abstract class BaseWebActivity extends Activity{

    /**
     * URL 펼침/접음 버튼의 버튼명/기능 토글
     *
     * @param v
     */
    public void onFold(View v) {

        EditText edt = (EditText) findViewById(R.id.etUrl); // URL 표시부
        Button btn = (Button) findViewById(R.id.btnFold); // 펼침/접음 버튼

        if(Constants.BTN_NAME_UNFOLD.equals(btn.getText())){
            edt.setSingleLine(false);
            btn.setText(Constants.BTN_NAME_FOLD);
        }else{
            edt.setSingleLine(true);
            btn.setText(Constants.BTN_NAME_UNFOLD);
        }
    }


    /**
     * url 에서 AuthorizationCode와 Scope를 추출하여 TokenRequestViewWebActivity 로 이동한다.
     *
     * @param url
     */
    public void goWebAuthCodeView(String url) {

        String callbackUrl = StringUtil.getPropString("WEB_CALLBACK_URL"); // 여러 요청 중에서 web callback url 요청에 대한 것만 필터링하여 수행한다.
        if(url.startsWith(callbackUrl)){
            String authCode = StringUtil.getParamValFromUrlString(url, "code");
            String scope = StringUtil.getParamValFromUrlString(url, "scope");
            Log.d("", "## authCode: ["+authCode+"], scope: ["+scope+"]");
//                    MessageUtil.showToast("AuthorizationCode: "+authCode, 3000);
            Intent intent = new Intent(App.getAppContext(), TokenRequestViewWebActivity.class);
            intent.putExtra("AuthorizationCode", authCode);
            intent.putExtra("Scope", scope);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Activity가 아닌 Context에서 Intent를 사용하여 Activity를 호출할 때에는 이 플래그가 필요하다고 한다.
            startActivity(intent);
        }
    }
}
