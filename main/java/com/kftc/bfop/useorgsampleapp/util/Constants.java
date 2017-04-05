package com.kftc.bfop.useorgsampleapp.util;

/**
 * Created by LeeHyeonJae on 2017-02-21.
 */
public interface Constants {

    /**
     * SharedPreferences 설정 이름
     */
    String APP_SETTING = "APP_SETTING";

    /**
     * 호출서버 환경의 선택지
     */
    String ENV_TEST = "TEST";
    String ENV_PRD = "PRD";

    /**
     * 기본 호출서버 환경
     *      - SharedPreferences 에 ENV가 저장되어 있지 않을 경우 사용할 기본값.
     */
    String ENV_DEFAULT = ENV_TEST ;

    /**
     * API 호출 기본 URL
     */
    String API_BASE_URL_TEST = "https://testapi.open-platform.or.kr";
    String API_BASE_URL_PRD = "https://openapi.open-platform.or.kr";

    //================================================== 사용자정의 설정 기본값 - start ==================================================
    // 기본 값은 여기에 정의된 것을 사용하지만, SharedPreferences 를 사용하여 수정한 정보를 영구적으로 단말에 저장할 수 있다.
    /**
     * 오픈플랫폼에서 발급한 이용기관의 앱의 Key
     */
    String APP_KEY = "l7xx2387628cf42a4845b221f88029ea5a0a"; // kftcedu00

    /**
     * 오픈플랫폼에서 발급한 이용기관의 앱 Client Secret
     */
    String APP_SECRET = "75518cc321b841059889d0e40f4d1f0b"; // kftcedu00

    /**
     * 오픈플랫폼에 등록된 이용기관 앱의 Redirect URL (웹 방식 호출의 경우)
     */
    String WEB_CALLBACK_URL = "http://localhost:8090/openapi/test/callback.html";

    /**
     * 오픈플랫폼에 등록된 이용기관 앱의 Redirect URL (앱 방식 호출의 경우)
     */
    String APP_CALLBACK_URL = "bfoptest://result";

    /**
     * 조회서비스 or 출금서비스 범위 지정자
     */
    String SCOPE = "transfer";
    //================================================== 사용자정의 설정 기본값 - end ====================================================

    String BTN_NAME_FOLD = "접음";
    String BTN_NAME_UNFOLD = "펼침";
}