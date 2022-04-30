package mad.a1byi60;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
    String webLink = "https://hkuspace.hku.hk/cc"; // a string field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webLink = getIntent().getStringExtra("KEY_URL"); // get weblink
        if (webLink.isEmpty()) // display local file for empty text
            webLink = "https://hkuspace.hku.hk/cc";
        if (webLink.equals("60")) // display local file for empty text
            webLink = "file:///android_asset/A1.html";
        WebView aWebView = (WebView)findViewById(R.id.webView);
        WebSettings aWebSettings = aWebView.getSettings();
        aWebSettings.setJavaScriptEnabled(true); // enable JavaScript usage
        aWebView.loadUrl(webLink); // display the web
        aWebView.setWebViewClient(new WebViewClient()); // set to display on webView
    }
}