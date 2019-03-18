package com.sky.gank.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.sky.gank.R;

/**
 * Created by yuetu-develop on 2017/9/22.
 */

public class ShareUtils {

    public static void share(Context context, int stringRes, String title) {
        share(context, context.getString(stringRes), title);
    }

    public static void shareImage(Context context, Uri uri, String title) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        context.startActivity(Intent.createChooser(shareIntent, title));
    }

    public static void share(Context context, String extraText, String extraTitle) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.action_share));
        intent.putExtra(Intent.EXTRA_TEXT, extraTitle+"\n"+extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(
                Intent.createChooser(intent, context.getString(R.string.action_share)));
    }
}
