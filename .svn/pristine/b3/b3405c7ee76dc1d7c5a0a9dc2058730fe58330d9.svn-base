package com.jky.xjht.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.jky.xjht.R;
import com.jky.xjht.adapter.VideoFragmetAdapter;
import com.jky.xjht.pulltorefresh.PullToRefreshListView;
import com.jky.xjht.utils.Constants;
import com.jky.xjht.utils.Preferences;
import com.jky.xjht.utils.UIUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * <p>监控点资源展示Activity</p>
 * @author zhangliwei
 * @version V1.0.0
 */
public class ResourceListActivity extends BaseActivity {
    /**
     * 关闭加载进度条
     */
    public static final int CANCEL_LOADING_PROGRESS = 1;
    /**
     * list显示数据适配器
     */
    private VideoFragmetAdapter mAdapter;
    /**
     * list显示数据
     */
    private ArrayList<String> mData = new ArrayList<String>();
    /**
     * 发送消息的对象
     */
    private Handler mHandler = null;
    /***
     * UI处理Handler
     */
    private View mNoDataView;
    private TextView mNoDataTv;
    private String mName;
    private PullToRefreshListView mPlvList;
    private String mCameraList;


    private static class ViewHandler extends Handler {

        private final WeakReference<ResourceListActivity> mActivity;

        ViewHandler(ResourceListActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case CANCEL_LOADING_PROGRESS:
                    UIUtils.cancelProgressDialog();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_list);
        mName = Preferences.getInstance(ResourceListActivity.this).getString(Constants.KEY_USER_NAME);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mHandler = new ViewHandler(this);
        Intent intent = getIntent();
        mCameraList = intent.getStringExtra("cameraListStr");

        int len = mCameraList.split(",").length;
        for (int i = 0; i < len; i++) {
                    mData.add(mCameraList.split(",")[i].split("#")[2]);
        }
        if (mData != null && mData.size() > 0) {
            mPlvList.setVisibility(View.VISIBLE);
            mNoDataView.setVisibility(View.GONE);
        } else {
            mNoDataView.setVisibility(View.VISIBLE);
            mPlvList.setVisibility(View.GONE);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化视图
     */
    private void initView() {

        setTitle("工程摄像头");
        mNoDataView =findViewById(R.id.no_data_view);
        mNoDataTv = (TextView)findViewById(R.id.no_data_tv);
        mPlvList = (PullToRefreshListView)findViewById(R.id.plv_list);
        if (!TextUtils.isEmpty(mName)) {
            mNoDataTv.setText(getResources().getText(R.string.empty));
            mPlvList.setVisibility(View.VISIBLE);
        } else {
            mNoDataView.setVisibility(View.VISIBLE);
            mPlvList.setVisibility(View.GONE);
            SpannableString spannableString = new SpannableString("您当前未登录，请您先登录");
            spannableString.setSpan(new ForegroundColorSpan(Color.BLUE),10,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mNoDataTv.setText(spannableString);
            mNoDataTv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ResourceListActivity.this, LoginActivity.class));
                }
            });
        }

        mAdapter = new VideoFragmetAdapter(ResourceListActivity.this, mData);
        mPlvList.getRefreshableView().setAdapter(mAdapter);
        mPlvList.getRefreshableView().setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        goLive(position);
                    }
                });
    }

    /**
     * 预览监控点
     */
    private void goLive(int position) {
        String mCarmeId = mCameraList.split(",")[position].split("#")[0];
        String mCarmeCode =  mCameraList.split(",")[position].split("#")[1];
        Intent intent = new Intent(ResourceListActivity.this,LiveActivity.class);
        intent.putExtra("tag", "2");
        intent.putExtra("carmeId", mCarmeId);
        intent.putExtra("carmeCode", mCarmeCode);
        startActivity(intent);
    }
//
//    /**
//     * 回放监控点
//     *
//     * @param subResourceNodeBean 监控点资源
//     */
//    private void goPlayBack(SubResourceNodeBean subResourceNodeBean) {
//        if (subResourceNodeBean == null) {
//            UIUtils.showToast(this, R.string.empty);
//        } else {
//            Intent it = new Intent(this, PlayBackActivity.class);
//            it.putExtra(Constants.IntentKey.CAMERA, subResourceNodeBean);
//            startActivity(it);
//        }
//    }

}
