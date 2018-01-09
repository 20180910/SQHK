package com.sk.sqhk.base;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.baseclass.rx.RxUtils;
import com.library.base.MyBaseActivity;
import com.sk.sqhk.Config;
import com.sk.sqhk.GetSign;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by Administrator on 2017/12/18.
 */

public abstract class BaseActivity extends MyBaseActivity {
    protected final String TAG=this.getClass().getSimpleName();
    protected final String noLoginCode="0";
    protected String getUserId() {
        return SPUtils.getString(mContext, Config.user_id, noLoginCode);
    }
    public boolean noLogin(){
        if(noLoginCode.equals(getUserId())){
            return true;
        }else{
            return false;
        }
    }
    protected String getSign(Map map) {
        return GetSign.getSign(map);
    }

    protected String getSign(String key, String value) {
        return GetSign.getSign(key, value);
    }
    protected void initWebViewForContent(WebView webview, String content) {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        //自适应屏幕  
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webview.getSettings().setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
        //设置Web视图
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                initWebTopView();
            }
        });

        webview.loadDataWithBaseURL(null, getNewContent(content), "text/html", "utf-8", null);
//        webview.loadUrl(url);
        // 设置WevView要显示的网页
//        webview.loadDataWithBaseURL(null, content, "text/html", "utf-8",null);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setJavaScriptEnabled(true); //设置支持Javascript
        webview.requestFocus(); //触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    protected void initWebViewForUrl(WebView webview, String url) {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        //自适应屏幕  
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webview.getSettings().setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
        //设置Web视图
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                initWebTopView();
            }
        });

//        webview.loadDataWithBaseURL(null, getNewContent(url), "text/html", "utf-8",null);
        webview.loadUrl(url);
        // 设置WevView要显示的网页
//        webview.loadDataWithBaseURL(null, content, "text/html", "utf-8",null);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setJavaScriptEnabled(true); //设置支持Javascript
        webview.requestFocus(); //触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    protected static String getNewContent(String htmltext) {
        try {
            Document doc = Jsoup.parse(htmltext);
            Elements elements = doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("width", "100%").attr("height", "auto");
            }
            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }

    public void countDown(TextView textView) {
        textView.setEnabled(false);
        final long count = 30;
        Subscription subscribe = Observable.interval(1, TimeUnit.SECONDS)
                .take(31)//计时次数
                .map(integer -> count - integer)
                .compose(RxUtils.appSchedulers())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        textView.setEnabled(true);
                        textView.setText("获取验证码");
                    }
                    @Override
                    public void onNext(Long aLong) {
                        textView.setText("剩下" + aLong + "s");
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                });
        addSubscription(subscribe);
    }
}

