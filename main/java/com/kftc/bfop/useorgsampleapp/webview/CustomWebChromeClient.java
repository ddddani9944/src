package com.kftc.bfop.useorgsampleapp.webview;

import android.webkit.WebChromeClient;

/**
 * Created by LeeHyeonJae on 2017-02-27.
 *
 * 최초 이 클래스를 생성했던 것은, WebView에서 javscript window.open() 이 실행될 때 열리고 닫히는 것을 구현하기 위한 것이었다.
 * 그러나 몇 번의 시도 끝에도 잘 작동하지 않았기 때문에 일단 봉인해 둔다.
 *
 */
public class CustomWebChromeClient extends WebChromeClient {

/*    WebView originalWebView;

    public CustomWebChromeClient(WebView originalWebView){
        this.originalWebView = originalWebView;
    }

    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {

        WebView newWebView = new WebView(view.getContext());

        newWebView.setWebViewClient(new WebViewClient());
        newWebView.setWebChromeClient(new CustomWebChromeClient(newWebView));
        newWebView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        newWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        newWebView.getSettings().setSupportMultipleWindows(true);

        originalWebView.addView(newWebView);    // 화면에 보여질 수 있도록 add view
        //view.addView(newWebView);    // 화면에 보여질 수 있도록 add view

        WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
        transport.setWebView(newWebView);
        resultMsg.sendToTarget();

        return true;
    }

    @Override
    public void onCloseWindow(WebView window) {
        Log.d("", "## onCloseWindow() called");

        super.onCloseWindow(window);

        window.setVisibility(View.GONE);
        originalWebView.removeView(window);

    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {

        new AlertDialog.Builder(App.getAppContext())
                .setTitle("확인")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,
                        new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        })
                .setCancelable(false)
                .create()
                .show();

        return true;

    }*/
}
