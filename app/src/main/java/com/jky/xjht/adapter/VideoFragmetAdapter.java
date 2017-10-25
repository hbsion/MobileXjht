package com.jky.xjht.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jky.xjht.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zlw
 * @des 视频控制中心适配器
 */
public class VideoFragmetAdapter extends BaseAdapter{

	private ArrayList<String> mList = new ArrayList<>();
	private Context mContext;

	public VideoFragmetAdapter(Context context, List<String> lists) {
		this.mContext = context;
		this.mList = (ArrayList<String>) lists;
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video_fragment, parent, false);
			holder.project_name_tv = (TextView) convertView.findViewById(R.id.project_name_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String  name = mList.get(position);
		holder.project_name_tv.setText(name);
		return convertView;
	}
	
	class ViewHolder {
		TextView project_name_tv;
	}
}
