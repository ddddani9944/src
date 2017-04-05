package com.kftc.bfop.useorgsampleapp.util;

/**
 * Created by LeeHyeonJae on 2017-02-24.
 */
public class ActivityUtil {

    /**
     * url 에서 AuthorizationCode와 Scope를 추출하여 TokenRequestViewWebActivity 로 이동한다.
     *      ==> 이렇게 하려다가 BaseWebActivity로 이동함.
     *
     * @param url
     */
/*    public static void goWebAuthCodeView(String url) {

        String callbackUrl = StringUtil.getPropString("WEB_CALLBACK_URL"); // 여러 요청 중에서 callback url 요청에 대한 것만 필터링하여 수행한다.
        if(url.contains(callbackUrl)){
            String authCode = StringUtil.getParamValFromUrlString(url, "code");
            String scope = StringUtil.getParamValFromUrlString(url, "scope");
            Log.d("", "## authCode: ["+authCode+"], scope: ["+scope+"]");
//                    MessageUtil.showToast("AuthorizationCode: "+authCode, 3000);
            Intent intent = new Intent(App.getAppContext(), TokenRequestViewWebActivity.class);
            intent.putExtra("AuthorizationCode", authCode);
            intent.putExtra("Scope", scope);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Activity가 아닌 Context에서 Intent를 사용, Activity를 호출할 때에는 이 플래그가 필요하다고 한다.
            App.getAppContext().getApplicationContext().startActivity(intent);
        }
    }*/

}