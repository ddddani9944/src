package com.kftc.bfop.useorgsampleapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.kftc.bfop.useorgsampleapp.util.Constants;
import com.kftc.bfop.useorgsampleapp.util.StringUtil;

import static com.kftc.bfop.useorgsampleapp.util.StringUtil.getConstantsVal;


/**
 * Created by LeeHyeonJae on 2017-02-21.
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate(){

        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return context;
    }

    /**
     * SharedPreferences 를 리턴한다.
     *  - 주의: App 클래스에 ApplicationContext 멤버가 바인딩 되기 전에 호출되어서는 안된다.
     *
     * @return
     */
    public static SharedPreferences getPref(){

        return App.getAppContext().getSharedPreferences(Constants.APP_SETTING, Context.MODE_PRIVATE);
    }

    /**
     * ENV(호출서버환경)값을 SharedPreferences 에서 읽어 리턴한다.
     *
     * @return
     */
    public static String getEnv(){

        SharedPreferences pref = getPref();
        String env = pref.getString("ENV", StringUtil.EMPTY);
//        Log.d("##", "env1: "+env);
        if(StringUtil.isBlank(env)){
            env = Constants.ENV_DEFAULT; // 기본값
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("ENV", env);
            editor.apply();
            Log.d("##", "ENV(호출서버환경)값이 존재하지 않으므로 기본 ENV 값 저장: [" + env+ "]");
        }
//        Log.d("##", "env2: "+env);
        return env;
    }

    /**
     * API 호출 base url 을 리턴한다.
     *
     * @return
     */
    public static String getApiBaseUrl(){

        String key = "API_BASE_URL_" + getEnv();
        String apiBaseUrl = getConstantsVal(key);
//        Log.d("##", "apiBaseUrl: "+apiBaseUrl);
        return apiBaseUrl;
    }

}
