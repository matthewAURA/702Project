package com.tp702_04.apps.project702;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jamesbutler on 16/04/15.
 */
public class ExpandableLogListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<LogItem> logItems;

    public ExpandableLogListAdapter(Context newContext) {
        this.context = newContext;
        this.logItems = new ArrayList<>();
    }

    public void addAll(ArrayList<LogItem> newLogItems) {
        this.logItems.addAll(newLogItems);
    }

    public void clear() {
        this.logItems.clear();
    }

    @Override
    public int getGroupCount() {
        return logItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // Children refers to the number of Children Layouts
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return logItems.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) { return logItems.get(groupPosition); }

    @Override
    public long getGroupId(int groupPosition) {
        return logItems.get(groupPosition).getID();
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
        LogItem logItem = logItems.get(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.header_layout, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.list_group_header);
        textView.setText(logItem.getApp());
        if (logItem.getIsMachineAccess().contentEquals("true")) {
            textView.setBackgroundColor(Color.RED);
        } else {
            textView.setBackgroundColor(Color.GREEN);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LogItem logItem = logItems.get(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_layout, null);
        }

        TextView textViewName = (TextView) convertView.findViewById(R.id.list_child_1);
        textViewName.setText(logItem.getName());

        TextView textViewDate = (TextView) convertView.findViewById(R.id.list_child_2);
        textViewDate.setText(logItem.getDate());

        TextView textViewTime = (TextView) convertView.findViewById(R.id.list_child_3);
        textViewTime.setText(logItem.getTime());

        TextView textViewTagMessage = (TextView) convertView.findViewById(R.id.list_child_4);
        textViewTagMessage.setText(logItem.getTagMessage());

        TextView textViewIsMachineAccess = (TextView) convertView.findViewById(R.id.list_child_5);
        textViewIsMachineAccess.setText(logItem.getIsMachineAccess());

        RelativeLayout childRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.child_relative_layout);

        if (logItem.getIsMachineAccess().contentEquals("true")) {
            childRelativeLayout.setBackgroundResource(R.color.lightRed);
        } else {
            childRelativeLayout.setBackgroundResource(R.color.lightGreen);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
