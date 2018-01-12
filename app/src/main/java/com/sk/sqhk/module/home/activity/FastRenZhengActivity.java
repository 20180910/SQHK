package com.sk.sqhk.module.home.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.SPUtils;
import com.github.baseclass.rx.IOCallBack;
import com.github.customview.MyEditText;
import com.github.customview.MyImageView;
import com.github.customview.MyRadioButton;
import com.library.base.BaseObj;
import com.sk.sqhk.base.MyCallBack;
import com.library.base.tools.has.BitmapUtils;
import com.sk.sqhk.AppXml;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.my.network.ApiRequest;
import com.sk.sqhk.module.my.network.response.RenZhengDataObj;
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

/**
 * Created by Administrator on 2017/12/20.
 */

public class FastRenZhengActivity extends BaseActivity {

    @BindView(R.id.iv_renzheng_img)
    MyImageView iv_renzheng_img;
    @BindView(R.id.iv_renzheng_second_img)
    MyImageView iv_renzheng_second_img;
    @BindView(R.id.et_renzheng_name)
    MyEditText et_renzheng_name;
    @BindView(R.id.rb_renzheng_nan)
    MyRadioButton rb_renzheng_nan;
    @BindView(R.id.rb_renzheng_nv)
    MyRadioButton rb_renzheng_nv;
    @BindView(R.id.et_renzheng_eid)
    MyEditText et_renzheng_eid;
    @BindView(R.id.et_renzheng_phone)
    MyEditText et_renzheng_phone;
    @BindView(R.id.et_renzheng_address)
    MyEditText et_renzheng_address;
    @BindView(R.id.tx_renzheng_commit)
    TextView tx_renzheng_commit;
    private boolean isSecondImg;
    private String firstImg, secondImg;
    private int renZhengStatus;
    private String name;
    private String eid;
    private String phone;
    private String address;

    @Override
    protected int getContentView() {
        setAppTitle("快速认证");
        return R.layout.act_fast_ren_zheng;
    }

    @Override
    protected void initView() {
        //身份认证状态(0未认证 1待审核 2审核通过 3审核未通过)
        renZhengStatus = SPUtils.getInt(mContext, AppXml.is_validation, 0);
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        ApiRequest.getRenZhengData(map, new MyCallBack<RenZhengDataObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(RenZhengDataObj obj) {
                name = obj.getReal_name();
                eid = obj.getCard_id();
                phone = obj.getTelphone();
                address = obj.getAddress();
                firstImg = obj.getCard_back_img();
                secondImg = obj.getCard_front_img();

                Glide.with(mContext).load(obj.getCard_back_img()).error(R.color.c_press).into(iv_renzheng_img);
                iv_renzheng_img.setVisibility(View.VISIBLE);

                Glide.with(mContext).load(obj.getCard_front_img()).error(R.color.c_press).into(iv_renzheng_second_img);
                iv_renzheng_second_img.setVisibility(View.VISIBLE);

                et_renzheng_name.setText(obj.getReal_name());
                if (obj.getSex().indexOf("男") != -1) {
                    rb_renzheng_nan.setChecked(true);
                } else {
                    rb_renzheng_nv.setChecked(true);
                }
                et_renzheng_eid.setText(obj.getCard_id());
                et_renzheng_phone.setText(obj.getTelphone());
                et_renzheng_address.setText(obj.getAddress());
            }

        });

    }

    @OnClick({R.id.ll_renzheng, R.id.ll_renzheng_second, R.id.tx_renzheng_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_renzheng:
                PhoneUtils.hiddenKeyBoard(mContext);
                isSecondImg = false;
                showSelectPhotoDialog();
                break;
            case R.id.ll_renzheng_second:
                PhoneUtils.hiddenKeyBoard(mContext);
                isSecondImg = true;
                showSelectPhotoDialog();
                break;
            case R.id.tx_renzheng_commit:
                //身份认证状态(0未认证 1待审核 2审核通过 3审核未通过)
                /*if(renZhengStatus==1){
                    showMsg("待审核状态中请稍后再试");
                    return;
                }else if(renZhengStatus==2){
                    showMsg("审核通过无需再次认证");
                    return;
                }else*/
                if (TextUtils.isEmpty(firstImg)) {
                    showMsg("请上传身份证人像面");
                    return;
                } else if (TextUtils.isEmpty(secondImg)) {
                    showMsg("请上传身份证国徽面");
                    return;
                }
                name = getSStr(et_renzheng_name);
                eid = getSStr(et_renzheng_eid);
                phone = getSStr(et_renzheng_phone);
                address = getSStr(et_renzheng_address);
                if (TextUtils.isEmpty(name)) {
                    showMsg("请输入真实姓名");
                    return;
                } else if (TextUtils.isEmpty(eid)) {
                    showMsg("请输入身份证号");
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    showMsg("请输入联系方式");
                    return;
                } else if (TextUtils.isEmpty(address)) {
                    showMsg("请输入家庭地址");
                    return;
                }
                commitData();
                break;
        }
    }

    private void commitData() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("real_name", name);
        map.put("sex", rb_renzheng_nan.isChecked() ? "男" : "女");
        map.put("id_number", eid);
        map.put("address", address);
        map.put("card_front_img", secondImg);
        map.put("card_back_img", firstImg);
        map.put("telphone", phone);
        map.put("sign", getSign(map));
        ApiRequest.renZheng(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    private void uploadImg(String imgPath) {
        showLoading();
        RXStart(new IOCallBack<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
//                String newPath= PathUtils.getImgCompressionPath(mContext);
//                FileUtils.makeFolder(newPath,true);
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
                UploadImgBody body = new UploadImgBody();
                body.setFile(baseImg);
                String rnd = getRnd();
                Map<String, String> map = new HashMap<String, String>();
                map.put("rnd", rnd);
                map.put("sign", getSign(map));
                NetApiRequest.uploadImg(map, body, new MyCallBack<BaseObj>(mContext) {
                    @Override
                    public void onSuccess(BaseObj obj) {
                        String imgUrl = obj.getImg();
                        if (isSecondImg) {
                            secondImg = imgUrl;
                            Glide.with(mContext).load(imgUrl).error(R.color.c_press).into(iv_renzheng_second_img);
                            iv_renzheng_second_img.setVisibility(View.VISIBLE);
                        } else {
                            firstImg = imgUrl;
                            Glide.with(mContext).load(imgUrl).error(R.color.c_press).into(iv_renzheng_img);
                            iv_renzheng_img.setVisibility(View.VISIBLE);
                        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case result_select_photo:
                    String imgPath = getSelectPhotoPath(data);
                    uploadImg(imgPath);
                    break;
                case result_take_photo:
                    uploadImg(takePhotoImgSavePath);
                    break;
            }
        }
    }
}
