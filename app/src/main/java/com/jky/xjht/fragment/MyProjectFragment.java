package com.jky.xjht.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jky.xjht.R;
import com.jky.xjht.activity.LoginActivity;
import com.jky.xjht.activity.ProjectDetailActivity;
import com.jky.xjht.adapter.ProjectListAdapter;
import com.jky.xjht.adapter.ProjectListAdapter.ItemViewCallback;
import com.jky.xjht.bean.ProjectInfo;
import com.jky.xjht.bean.ProjectInfo.RecordInfoBean;
import com.jky.xjht.net.MobileEduService;
import com.jky.xjht.net.RequestCallBackModel;
import com.jky.xjht.net.RequestListener;
import com.jky.xjht.pulltorefresh.PullToRefreshBase;
import com.jky.xjht.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.jky.xjht.pulltorefresh.PullToRefreshListView;
import com.jky.xjht.utils.AppObserverUtils;
import com.jky.xjht.utils.Constants;
import com.jky.xjht.utils.ObserverListener;
import com.jky.xjht.utils.Preferences;
import com.jky.xjht.utils.SingleToast;
import com.jky.xjht.volley.VolleyError;

import java.util.ArrayList;
/**
 * <p>工程列表Fragment</p>
 * @author zlw
 * */
public class MyProjectFragment extends BaseFragment implements ItemViewCallback {
	
	private PullToRefreshListView mPlvList;
	private View mNoDataView;
	private TextView mTvNoData;
	private ProjectListAdapter mAdapter;
	private int pageNum = 1;
	private int pageSize = 20;
	private ArrayList<RecordInfoBean> lists = new ArrayList<RecordInfoBean>();
	private String mName;

	private View mMainView;
	private View mDialog_view;
	private ImageView mImageView;
	private AnimationDrawable mAnimation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_project, container, false);
		initView(view);
		mName = Preferences.getInstance(getActivity()).getString(Constants.KEY_USER_NAME);
		initView(view);
		if(!TextUtils.isEmpty(mName)){
			getData();
		}

		AppObserverUtils.registerObserver(AppObserverUtils.LOGIN_LOG_OUT, new ObserverListener() {

			@Override
			public void notifyChange(Bundle bundle, Object object) {
				mNoDataView.setVisibility(View.VISIBLE);
				mPlvList.setVisibility(View.GONE);
				SpannableString spannableString = new SpannableString("您当前未登录，请您先登录");
				spannableString.setSpan(new ForegroundColorSpan(Color.BLUE),10,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				mTvNoData.setText(spannableString);
				mTvNoData.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(getActivity(), LoginActivity.class));
					}
				});
			}
		});
		return view;
	}

	private void initView(View view) {

		mMainView = view.findViewById(R.id.main_view);
		mDialog_view = view.findViewById(R.id.dialog_view);
		mImageView = (ImageView) view.findViewById(R.id.loadingIv);
		mMainView.setVisibility(View.GONE);
		mDialog_view.setVisibility(View.VISIBLE);
		// 通过ImageView对象拿到背景显示的AnimationDrawable
		mAnimation = (AnimationDrawable) mImageView.getBackground();
		// 为了防止在onCreate方法中只显示第一帧的解决方案之一
		mImageView.post(new Runnable() {

			@Override
			public void run() {
				mAnimation.start();
			}
		});

		mPlvList = (PullToRefreshListView) view.findViewById(R.id.plv_list);
		mNoDataView = view.findViewById(R.id.no_data_view);
		mTvNoData = (TextView) view.findViewById(R.id.no_data_tv);
		mTvNoData.setText(getResources().getString(R.string.empty));
		if (!TextUtils.isEmpty(mName)) {
			mTvNoData.setText(getResources().getText(R.string.empty));
			mPlvList.setVisibility(View.VISIBLE);
		} else {
			mNoDataView.setVisibility(View.VISIBLE);
			mPlvList.setVisibility(View.GONE);
			SpannableString spannableString = new SpannableString("您当前未登录，请您先登录");
			spannableString.setSpan(new ForegroundColorSpan(Color.BLUE),10,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			mTvNoData.setText(spannableString);
			mTvNoData.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					startActivity(new Intent(getActivity(), LoginActivity.class));
				}
			});
		}


		mPlvList.getRefreshableView().setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView,
							View view, int i, long l) {
						String id = lists.get(i).getID();
						Intent intent = new Intent(getActivity(),ProjectDetailActivity.class);
						intent.putExtra("projectId", id);
						startActivity(intent);
					}
				});
		mAdapter = new ProjectListAdapter(getActivity(),lists, this);
	  	mPlvList.getRefreshableView().setAdapter(mAdapter);
		mPlvList.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh(int mode) {
				if (mode == PullToRefreshBase.MODE_PULL_DOWN_TO_REFRESH) {
					pageNum = 1;
					getData();
				} else if (mode == PullToRefreshBase.MODE_PULL_UP_TO_REFRESH) {
					if (lists != null && lists.size() == pageNum * pageSize) {
						pageNum++;
						getData();
					} else {
						mPlvList.onRefreshComplete();
					}
				}
			}
		});
	}


	public void getData() {
		String userId = Preferences.getInstance(getActivity()).getString(Constants.KEY_USER_ID);
		String roleId = Preferences.getInstance(getActivity()).getString(Constants.KEY_USER_ROLE);
		String AreaId = Preferences.getInstance(getActivity()).getString(Constants.KEY_AREA_ID);
		MobileEduService.getInstance(getActivity()).getProjectList(userId,roleId,"",AreaId,"",pageNum, pageSize,"getProjectList", listener);
	}
	public void dismissMainDialog(){
		mMainView.setVisibility(View.VISIBLE);
		mDialog_view.setVisibility(View.GONE);
	}
	private RequestListener listener = new RequestCallBackModel() {

		@Override
		public void onSuccess(String result, String tag) {
			super.onSuccess(result, tag);
			dismissMainDialog();
				if ("getProjectList".equals(tag)) {
					if (code == 0) {
						Gson gson = new Gson();
						ProjectInfo projectInfo = gson.fromJson(data, ProjectInfo.class);
						lists = (ArrayList<RecordInfoBean>) projectInfo.getRecords();
						mAdapter.setData(lists);
						mPlvList.setVisibility(View.VISIBLE);
						mNoDataView.setVisibility(View.GONE);

					} else {
						mNoDataView.setVisibility(View.VISIBLE);
						mPlvList.setVisibility(View.GONE);
						SingleToast.show(getActivity(), "数据请求失败");
					}
				} 
		}
	
		@Override
		public void onFailed(VolleyError error) {
			super.onFailed(error);
			dismissMainDialog();
			mNoDataView.setVisibility(View.VISIBLE);
			mPlvList.setVisibility(View.GONE);
		}
	};
	
	@Override
	public void click(View v) {
		int position = (Integer) v.getTag();
	}
	
}