package com.xiayang.learningforums.web;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * desc   :
 * <p>
 * author : Alexander
 * e-mail : wfy19961024@gmail.com
 * date   : 2020/3/29
 */
public class NetJavaScriptInterface {
    Context context;

    public NetJavaScriptInterface(Context context) {
        this.context = context;
    }

    @android.webkit.JavascriptInterface
    public void openImage(String[] imgs, String imgUrl) {
        int length = imgs.length;

        int position = 0;
        for (int i = 0; i < length; i++) {
            if (imgUrl.equals(imgs[i])) {
                position = i;
            }
        }
        Log.i("Alexander", "openImage: pos" + position);
        Log.i("Alexander", "openImage: url" + imgUrl);
        Intent intent = new Intent(context, DisplayImageActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("imags", imgs);
        context.startActivity(intent);
    }
}
