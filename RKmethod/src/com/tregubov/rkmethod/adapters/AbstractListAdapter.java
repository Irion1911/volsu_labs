package com.tregubov.rkmethod.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AbstractListAdapter<T> extends BaseAdapter {
	
	private List<T> mObjects;
	
	private Context mContext;
	private LayoutInflater mInflater;

	public AbstractListAdapter(Context context) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mObjects = new ArrayList<T>();
	}

	public void setObjects (List<T> objects) {
		if (objects == null) {
			objects = new ArrayList<T>();
		}
		
		mObjects = objects;
		notifyDataSetChanged();
	}
	
	public boolean addObjects (List<T> objects) {
		boolean result = false;
		
		if ( ! mObjects.containsAll(objects)){
			mObjects.addAll(objects);
			result = true;
			notifyDataSetChanged();
		}
		
		return result;
	}
	
	public void removeObject (int index) {
		mObjects.remove(index);
		notifyDataSetChanged();
	}
	
	public void clearObjects () {
		mObjects.clear();
		notifyDataSetChanged();
	}

	public int getCount() {
		return mObjects.size();
	}
	
	//для переопределения в потомке
	@Override
	public long getItemId (int position) {
		return position;
	}

	public T getItem(int position) {
		return mObjects.get(position);
	}
	
	@Override
	public final View getView(int position, View convertView, ViewGroup parent) {
		T bean = getItem(position);
		View view = getView(position, convertView, bean);
//		view.setBackgroundResource(R.drawable.bg_list_base);
		return view;
	}
	
	public abstract View getView(int position, View convertView, T bean);

	public Context getContext() {
		return mContext;
	}
	
	public LayoutInflater getInflater() {
		return mInflater;
	}
	
	public List<T> getObjects() {
		return mObjects;
	}
}
