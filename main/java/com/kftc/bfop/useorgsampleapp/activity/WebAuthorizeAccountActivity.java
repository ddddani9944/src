package com.kftc.bfop.useorgsampleapp.activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.kftc.bfop.useorgsampleapp.App;
import com.kftc.bfop.useorgsampleapp.R;
import com.kftc.bfop.useorgsampleapp.activity.base.BaseWebActivity;
import com.kftc.bfop.useorgsampleapp.util.StringUtil;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by KFTC Mobile Development Team on 2017. 1. 16..
 *
 * @author Sangyeop Han
 */
public class WebAuthorizeAccountActivity extends BaseWebActivity {

    private static String URI = "/oauth/2.0/authorize_account";
    private EditText m_etUrl;
    private WebView m_webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_authorize_account);

        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("계좌등록 확인 (웹 Only)");

        List<NameValuePair> params = new LinkedList<NameValuePair>();
        params.add(new BasicNameValuePair("response_type", "code"));
        params.add(new BasicNameValuePair("client_id", StringUtil.getPropString("APP_KEY")));
        params.add(new BasicNameValuePair("redirect_uri", StringUtil.getPropString("WEB_CALLBACK_URL")));
        params.add(new BasicNameValuePair("scope", StringUtil.getPropString("SCOPE")));
        params.add(new BasicNameValuePair("client_info", "whatever_you_want"));

        String paramString = URLEncodedUtils.format(params, "utf-8");
        String urlToLoad = (App.getApiBaseUrl() + URI) + "?" + paramString;

        m_etUrl = (EditText) findViewById(R.id.etUrl);
        m_webView = (WebView) findViewById(R.id.webView);
        m_etUrl.setText(urlToLoad);

        WebSettings settings = m_webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true); //HSY: 로그인을 위해 필요
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        m_webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
                new AlertDialog.Builder(WebAuthorizeAccountActivity.this)
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
            }
        });
        m_webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // Ignore SSL certificate errors
            }
        });

        m_webView.loadUrl(urlToLoad);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity
                        .class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    public void onGo(View v) {
        m_webView.loadUrl(m_etUrl.getText().toString());
    }
}
