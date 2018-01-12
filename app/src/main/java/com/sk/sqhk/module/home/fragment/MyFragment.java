package com.sk.sqhk.module.home.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.SPUtils;
import com.github.baseclass.rx.IOCallBack;
import com.github.customview.CircleImageView;
import com.library.base.BaseObj;
import com.sk.sqhk.base.MyCallBack;
import com.library.base.tools.has.BitmapUtils;
import com.sk.sqhk.AppXml;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseFragment;
import com.sk.sqhk.module.home.activity.YaoQingActivity;
import com.sk.sqhk.module.my.activity.HelpCenterActivity;
import com.sk.sqhk.module.my.activity.MyAccountActivity;
import com.sk.sqhk.module.my.activity.MyMessageActivity;
import com.sk.sqhk.module.my.activity.SettingActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.LoginObj;
import com.sk.sqhk.network.NetApiRequest;
import com.sk.sqhk.network.request.UploadImgBody;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import top.zibin.luban.Luban;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2017/12/4.
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.civ_my)
    CircleImageView civ_my;
    @BindView(R.id.tv_my_name)
    TextView tv_my_name;
    @BindView(R.id.tv_my_yue)
    TextView tv_my_yue;
    @BindView(R.id.tv_my_shouyi)
    TextView tv_my_shouyi;

    @Override
    protected int getContentView() {

        return R.layout.frag_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);

    }

    @Override
    protected void onMyReStart() {
        super.onMyReStart();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        ApiRequest.getUserInfo(map, new MyCallBack<LoginObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(LoginObj obj) {
                Glide.with(mContext).load(obj.getAvatar()).error(R.drawable.my_people).into(civ_my);
                tv_my_name.setText(obj.getUser_name());
                tv_my_yue.setText("账户余额:" + obj.getAmount());
                loginResult(obj);
            }
        });

    }

    private void loginResult(LoginObj obj) {
        tv_my_shouyi.setText("累计奖励: ¥"+obj.getCommission()+"元");

        SPUtils.setPrefString(mContext, AppXml.user_id, obj.getUser_id());
        SPUtils.setPrefString(mContext, AppXml.mobile, obj.getMobile());
        SPUtils.setPrefString(mContext, AppXml.sex, obj.getSex());
        SPUtils.setPrefString(mContext, AppXml.avatar, obj.getAvatar());
        SPUtils.setPrefString(mContext, AppXml.birthday, obj.getBirthday());
        SPUtils.setPrefString(mContext, AppXml.user_name, obj.getUser_name());
        SPUtils.setPrefString(mContext, AppXml.nick_name, obj.getNick_name());
        SPUtils.setPrefFloat(mContext, AppXml.amount, obj.getAmount());
        SPUtils.setPrefFloat(mContext, AppXml.commission, obj.getCommission());
        SPUtils.setPrefInt(mContext, AppXml.message_sink, obj.getMessage_sink());
        SPUtils.setPrefInt(mContext, AppXml.is_validation, obj.getIs_validation());
        SPUtils.setPrefInt(mContext, AppXml.cumulative_reward, obj.getCumulative_reward());

        SPUtils.setPrefString(mContext, AppXml.name, obj.getName());
        SPUtils.setPrefString(mContext, AppXml.email, obj.getEmail());
        SPUtils.setPrefString(mContext, AppXml.major, obj.getMajor());
    }

    @OnClick({R.id.civ_my,R.id.iv_my_msg, R.id.tv_my_setting,R.id.tv_my_yinhangka, R.id.tv_my_zhangdan, R.id.tv_my_zhanghu, R.id.tv_my_bangzhu,R.id.tv_my_yaoqing})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.civ_my:
                showSelectPhotoDialog();
                break;
            case R.id.iv_my_msg:
                STActivity(MyMessageActivity.class);
                break;
            case R.id.tv_my_setting:
                STActivity(SettingActivity.class);
                break;
            case R.id.tv_my_yinhangka:
                break;
            case R.id.tv_my_zhangdan:
                break;
            case R.id.tv_my_zhanghu:
                STActivity(MyAccountActivity.class);
                break;
            case R.id.tv_my_yaoqing:
                STActivity(YaoQingActivity.class);
                break;
            case R.id.tv_my_bangzhu:
                STActivity(HelpCenterActivity.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case result_select_photo:
                    String photoPath = getSelectPhotoPath(data);
                    uploadImg(photoPath);
                    break;
                case result_take_photo:
                    uploadImg(takePhotoImgSavePath);
                break;
            }
        }
    }

    private void uploadImg(String imgPath) {
        showLoading();
        RXStart(new IOCallBack<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    List<File> files = Luban.with(mContext).ignoreBy(700).load(imgPath).get();
                    String imgStr = BitmapUtils.fileToString(files.get(0));
                    subscriber.onNext(imgStr);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
            @Override
            public void onMyNext(String baseImg) {
                UploadImgBody body=new UploadImgBody();
                body.setFile(baseImg);
                String rnd = getRnd();
                Map<String,String> map=new HashMap<String,String>();
                map.put("rnd",rnd);
                map.put("sign",getSign(map));
                NetApiRequest.uploadImg(map,body, new MyCallBack<BaseObj>(mContext,true) {
                    @Override
                    public void onSuccess(BaseObj obj) {
                        String imgUrl = obj.getImg();
                        updateUserImg(imgUrl);
                    }
                });
            }
            @Override
            public void onMyError(Throwable e) {
                super.onMyError(e);
                dismissLoading();
                showToastS("图片处理失败");
            }
        });
    }

    private void updateUserImg(String imgUrl) {
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("avatar",imgUrl);
        map.put("sign",getSign(map));
        ApiRequest.updateUserImg(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                SPUtils.setPrefString(mContext,AppXml.avatar,imgUrl);
                Glide.with(mContext).load(imgUrl).error(R.drawable.my_people).into(civ_my);
            }
        });

    }
}
