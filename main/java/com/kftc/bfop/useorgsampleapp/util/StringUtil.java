package com.kftc.bfop.useorgsampleapp.util;

import android.util.Log;

import com.kftc.bfop.useorgsampleapp.App;

import java.lang.reflect.Field;
import java.util.StringTokenizer;

/**
 * Created by LeeHyeonJae on 2017-02-21.
 */
public class StringUtil {

    public static final String EMPTY = "";

    /**
     * NVL 처리
     *
     * @param str
     * @param defaultStr
     * @return
     */
    public static String defaultString(final String str, final String defaultStr) {
        return str == null ? defaultStr : str;
    }

    /**
     * NVL 처리
     *
     * @param str
     * @return
     */
    public static String defaultString(final String str) {
        return str == null ? EMPTY : str;
    }

    /**
     * NVL 처리
     *
     * @param src
     * @param defaultStr
     * @return
     */
    public static String defaultString(Object src, final String defaultStr){
        if(src != null){
            if(src instanceof String){
                return (String)src;
            }else{
                return String.valueOf(src);
            }
        }else{
            return (defaultStr == null) ? EMPTY : defaultStr;
        }
    }

    /**
     * NVL 처리
     *
     * @param src
     * @return
     */
    public static String defaultString(Object src){
        return defaultString(src, null);
    }

    /**
     * 빈 값이면 true 리턴
     *
     * @param src
     * @return
     */
    public static boolean isBlank(final CharSequence cs){
        int strLen;
        if(cs == null || (strLen = cs.length()) == 0){
            return true;
        }
        for(int i=0; i<strLen; i++){
            if(Character.isWhitespace(cs.charAt(i)) == false){
                return false;
            }
        }
        return true;
    }

    /**
     * 빈 값이면 false 리턴
     *
     * @param cs
     * @return
     */
    public static boolean isNotBlank(final CharSequence cs){
        return !StringUtil.isBlank(cs);
    }

    /**
     * Constants의 정적멤버 이름을 매개변수로 받아서 값을 리턴한다.
     *
     * @param constantsFieldName
     * @return
     */
    public static String getConstantsVal(String constantsFieldName) {

        Field field = null;
        Object value = null;
        try {
            field = Constants.class.getDeclaredField(constantsFieldName);
            field.setAccessible(true);
            value = field.get(null);
        } catch (IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        String ret = StringUtil.defaultString(value);
        Log.d("##", "ret:" + ret);
        return ret;
    }

    /**
     * 프로퍼티 키를 입력받아서
     *  (1) SharedPreferences 에서 해당 값을 찾고,
     *  (2) 없을 경우 Constants에서 해당 값을 찾아 리턴한다.
     *
     * 주의! : (2)의 경우 필드명 reflection을 사용하기 때문에, propKey는 Constants의 필드명에 정의된 것을 사용해야만 한다.
     *
     * @param propKey
     * @return
     */
    public static String getPropString(String propKey){

        return App.getPref().getString(propKey, getConstantsVal(propKey));
    }


    /**
     * url 문자열에 포함된 특정 파라미터의 값을 리턴한다.
     *
     * @param url
     * @param paramKey
     * @return
     */
    public static String getParamValFromUrlString(String url, String paramKey){

        Log.d("## url", url);
        String[] urlParamPair = url.split("\\?");
        if(urlParamPair.length < 2){
            Log.d("##", "파라미터가 존재하지 않는 URL 입니다.");
        }
        String queryString = urlParamPair[1];
        Log.d("## queryString", queryString);
        StringTokenizer st = new StringTokenizer(queryString, "&");
        while(st.hasMoreTokens()){
            String pair = st.nextToken();
            Log.d("## pair", pair);
            String[] pairArr = pair.split("=");
            if(paramKey.equals(pairArr[0])){
                return pairArr[1]; // 찾았을 경우
            }
        }
        return "";
    }


}
