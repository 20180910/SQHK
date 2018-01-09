package com.sk.sqhk.module.home.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.customview.MyRadioButton;
import com.github.retrofitutil.NetWorkManager;
import com.library.base.BaseObj;
import com.library.base.MyCallBack;
import com.library.base.ResponseObj;
import com.sk.sqhk.Config;
import com.sk.sqhk.GetSign;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.broadcast.MyOperationBro;
import com.sk.sqhk.module.home.fragment.HomeFragment;
import com.sk.sqhk.module.home.fragment.MyFragment;
import com.sk.sqhk.module.home.fragment.SelectFragment;
import com.sk.sqhk.module.my.activity.LoginActivity;
import com.sk.sqhk.network.NetApiRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/11/4.
 */

public class MainActivity extends BaseActivity {
//    @BindView(R.id.status_bar)
//    View status_bar;

    HomeFragment homeFragment;
    SelectFragment selectFragment;
    MyFragment myFragment;

    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.rb_home_tab1)
    MyRadioButton rb_home_tab1;

    @BindView(R.id.rb_home_tab2)
    MyRadioButton rb_home_tab2;

    @BindView(R.id.rb_home_tab3)
    MyRadioButton rb_home_tab3;
    private MyRadioButton selectView;


    private LocalBroadcastManager localBroadcastManager;
    private MyOperationBro myOperationBro;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        homeFragment = new HomeFragment();
        addFragment(R.id.fl_content, homeFragment);

        setTabClickListener();

        setBroadcast();
    }

    private void setTabClickListener() {
        selectView = rb_home_tab1;
        rb_home_tab1.setOnClickListener(getTabClickListener(1));
        rb_home_tab2.setOnClickListener(getTabClickListener(2));
        rb_home_tab3.setOnClickListener(getTabClickListener(3));
    }

    @NonNull
    private MyOnClickListener getTabClickListener(final int index) {
        return new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                switch (index) {
                    case 1:
                        selectHome();
                        break;
                    case 2:
                        selectChaXun();
                        break;
                    case 3:
                        selectFile();
                        if (TextUtils.equals(noLoginCode, getUserId())) {
                            STActivity(LoginActivity.class);
                            selectView.setChecked(true);
                        } else {
                            selectMy();
                        }
                        break;
                }
            }
        };
    }

    private void selectFile() {
//        uploadFile();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
            Log.i(TAG + "===", "===" + uri.getScheme());
           /* String[] proj = {MediaStore.Images.Media.DATA};
            Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            String img_path = actualimagecursor.getString(actual_image_column_index);
            Log.i(TAG + "===", "===" + img_path);
            File file = new File(img_path);
            Toast.makeText(MainActivity.this, file.toString(), Toast.LENGTH_SHORT).show();*/
            String path = "";
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                path = getPath(this, uri);
            } else {//4.4以下下系统调用方法
                path = getRealPathFromURI(uri);
            }
            Log.i(TAG + "===", "===" + path);
            uploadFile(path);

        }
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    @SuppressLint("NewApi")
    public String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (null != cursor && cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }

    public interface service {
        @Multipart
        @POST("api/SHLGPub/PostUploadFile")
        Call<ResponseBody> upload(@QueryMap Map<String, String> map, @Part() MultipartBody.Part file);
        @Multipart
        @POST("api/SHLGPub/PostUploadFile")
        Call<ResponseObj<BaseObj>> newUpload(@QueryMap Map<String, String> map, @Part() MultipartBody.Part file);
    }

    public Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl("http://121.40.186.118:1554/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void uploadFile(String path) {
//        String path="/storage/emulated/0/非手机自带/image/346853.jpg";
        File file = new File(path);

        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);

        MultipartBody.Part body2=MultipartBody.Part.createFormData("file",file.getName(),requestBody);

// MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));

// 执行请求
//        Call<ResponseBody> call = getClient().create(service.class).upload(map, body);
        Call<ResponseObj<BaseObj>> call = NetWorkManager.getGeneralClient("http://121.40.186.118:1554/").create(service.class).newUpload(map, body);
        call.enqueue(new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                Log.i(TAG+"===","==="+obj.toString());
            }
        });
       /* call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
                try {
                    Log.i(TAG + "===", "===" + response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
*/
    }

    private void selectHome() {
        selectView = rb_home_tab1;
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            addFragment(R.id.fl_content, homeFragment);
        } else {
            showFragment(homeFragment);
        }
        hideFragment(selectFragment);
        hideFragment(myFragment);
    }

    private void selectChaXun() {
        selectView = rb_home_tab2;
        if (selectFragment == null) {
            selectFragment = new SelectFragment();
            addFragment(R.id.fl_content, selectFragment);
        } else {
            showFragment(selectFragment);
        }
        hideFragment(homeFragment);
        hideFragment(myFragment);
    }

    private void selectMy() {
        selectView = rb_home_tab3;
        if (myFragment == null) {
            myFragment = new MyFragment();
            addFragment(R.id.fl_content, myFragment);
        } else {
            showFragment(myFragment);
        }
        hideFragment(homeFragment);
        hideFragment(selectFragment);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (Config.exitAPP.equals(intent.getAction())) {
            finish();
        }
    }

    @Override
    protected void initData() {
        getPaymentURL(1);//获取支付宝回传地址
        getPaymentURL(2);//获取微信回传地址
    }

    private void getPaymentURL(int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("payment_type", type + "");
        map.put("sign", GetSign.getSign(map));
        NetApiRequest.paymentURL(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
               /* if(obj.getPayment_type()==1){
                    SPUtils.setPrefString(mContext,Config.payType_ZFB,obj.getPayment_url());
                }else{
                    SPUtils.setPrefString(mContext,Config.payType_WX,obj.getPayment_url());
                }*/
            }
        });

    }

    @Override
    protected void initRxBus() {
        super.initRxBus();

    }

    protected void onViewClick(View v) {
        switch (v.getId()) {

        }
    }

    private void setBroadcast() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        myOperationBro = new MyOperationBro(new MyOperationBro.LoginBroInter() {
            @Override
            public void loginSuccess() {
                selectMy();
            }

            @Override
            public void exitLogin() {
                selectHome();
            }
        });
        localBroadcastManager.registerReceiver(myOperationBro, new IntentFilter(Config.Bro.operation));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(myOperationBro);
        }
    }


    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            showToastS("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}
