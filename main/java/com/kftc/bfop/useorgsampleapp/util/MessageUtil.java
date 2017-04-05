package com.kftc.bfop.useorgsampleapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.kftc.bfop.useorgsampleapp.App;


/**
 * Created by LeeHyeonJae on 2017-02-23.
 */
public class MessageUtil {

    /**
     * Toast 메시지 띄우기
     *
     * @param msg
     * @param duration
     */
    public static void showToast(String msg, int duration){

        Toast.makeText(App.getAppContext(), msg, duration).show();
    }


    /**
     * AlertDialog Builder 리턴
     *
     * @param title
     * @param msg
     * @param cancelable
     * @return
     */
    public static AlertDialog.Builder getDialogBuilder(String title, String msg, boolean cancelable, Context context){

        return new AlertDialog.Builder(context).setTitle(title).setMessage(msg).setCancelable(cancelable);
    }

}
