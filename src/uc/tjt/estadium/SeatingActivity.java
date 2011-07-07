package uc.tjt.estadium;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
public class SeatingActivity extends Activity {

	private static final FrameLayout.LayoutParams ZOOM_PARAMS =
		new FrameLayout.LayoutParams(
		ViewGroup.LayoutParams.FILL_PARENT,
		ViewGroup.LayoutParams.WRAP_CONTENT,
		Gravity.BOTTOM);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seating);
        String address = getString(R.string.chelseaSeatingPlan);
        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        
        
        
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        FrameLayout mContentView = (FrameLayout) getWindow().
        getDecorView().findViewById(android.R.id.content);

        final View zoom = myWebView.getZoomControls();
        mContentView.addView(zoom, ZOOM_PARAMS);
        zoom.setVisibility(View.GONE);

        
        myWebView.loadUrl(address);
    }
	
}
