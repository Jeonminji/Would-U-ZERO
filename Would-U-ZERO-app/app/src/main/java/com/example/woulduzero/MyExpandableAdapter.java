package com.example.woulduzero;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.Map;

public class MyExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> mainCategory;
    private Map<String, List<String>> categoryList;

    public MyExpandableAdapter(Context context, List<String> mainCategory, Map<String, List<String>> categoryList) {
        this.context = context;
        this.mainCategory = mainCategory;
        this.categoryList = categoryList;
    }

    @Override
    public int getGroupCount() {
        return categoryList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categoryList.get(mainCategory.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mainCategory.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categoryList.get(mainCategory.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String mainName = getGroup(groupPosition).toString();
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_parent, null);
        }

        TextView item = convertView.findViewById(R.id.parenttext);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(mainName);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String subName = getChild(groupPosition, childPosition).toString();
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_child, null);
        }

        TextView item = convertView.findViewById(R.id.childtext);
        item.setText(subName);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

// onClick 구현