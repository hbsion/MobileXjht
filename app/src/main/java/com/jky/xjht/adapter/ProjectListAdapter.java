package com.jky.xjht.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jky.xjht.R;
import com.jky.xjht.bean.ProjectInfo.RecordInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zlw
 * @des 工程列表适配器
 */
public class ProjectListAdapter extends BaseAdapter implements OnClickListener{

	private ItemViewCallback mCallback;
	private List<RecordInfoBean> mList = new ArrayList<RecordInfoBean>();
	private Context mContext;

	public ProjectListAdapter(Context context, List<RecordInfoBean> lists,ItemViewCallback callback) {
		this.mCallback = callback;
		this.mContext = context;
		this.mList = lists;
	}

	public void  setData(List<RecordInfoBean> lists){
		this.mList = lists;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_project_list, parent, false);
			holder.project_name_tv = (TextView) convertView.findViewById(R.id.project_name_tv);
			holder.project_category_tv = (TextView) convertView.findViewById(R.id.project_category_tv);
			holder.project_location_tv = (TextView) convertView.findViewById(R.id.project_location_tv);
			holder.project_company_name = (TextView) convertView.findViewById(R.id.project_company_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		RecordInfoBean  bean = mList.get(position);
		holder.project_name_tv.setText(bean.getProjectName());
		holder.project_category_tv.setText(bean.getTypeName());
		holder.project_location_tv.setText(bean.getAreaName());
		holder.project_company_name.setText(bean.getJSDWName());
		return convertView;
	}
	/**
	 * 自定义接口，用于回调按钮点击事件到Activity
	 */
	public interface ItemViewCallback {
		public void click(View v);
	}
	// 响应按钮点击事件,调用子定义接口，并传入View
	@Override
	public void onClick(View v) {
		mCallback.click(v);
	}
	class ViewHolder {
		TextView project_name_tv;
		TextView project_category_tv;
		TextView project_location_tv;
		TextView project_company_name;
	}
}
