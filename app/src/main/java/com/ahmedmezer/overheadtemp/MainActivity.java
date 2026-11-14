package com.ahmedmezer.overheadtemp;

/*
 * Bubble point temperature calculation
 * Developer: Process engineer Ahmed Mezer
 *
 * Shows the calculator page (assets/index.html) full screen. Works offline.
 * "Export PDF report" in the page calls AndroidBridge.printReport(), which opens
 * the Android print screen where the report is saved with "Save as PDF".
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String JOB_NAME = "Bubble point temperature report";
    private WebView web;

    /** Methods the page can call as window.AndroidBridge.* */
    private class Bridge {
        @JavascriptInterface
        public void printReport() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    exportPdf();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        web = new WebView(this);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);          // remembers the last inputs
        s.setTextZoom(100);
        web.setWebViewClient(new WebViewClient());
        web.addJavascriptInterface(new Bridge(), "AndroidBridge");
        setContentView(web);
        if (savedInstanceState != null) {
            web.restoreState(savedInstanceState);
        } else {
            web.loadUrl("file:///android_asset/index.html");
        }
    }

    private void exportPdf() {
        try {
            PrintManager pm = (PrintManager) getSystemService(Context.PRINT_SERVICE);
            pm.print(JOB_NAME, web.createPrintDocumentAdapter(JOB_NAME),
                    new PrintAttributes.Builder()
                            .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                            .build());
        } catch (Exception e) {
            Toast.makeText(this, "PDF export is not available on this device", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        web.saveState(outState);
    }
}
