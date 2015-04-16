package com.tp702_04.apps.project702;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jamesbutler on 16/04/15.
 */
public class ExpandableLogListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<LogItem> logItems;

    public ExpandableLogListAdapter(Context newContext, ArrayList<LogItem> newLogItems) {
        this.context = newContext;
        this.logItems = newLogItems;
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
        // A log entry will always have 1 header and 4 child fields
        return 4;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return logItems.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Object return_value;
        LogItem logItem = logItems.get(groupPosition);

        switch(childPosition) {
            case 1:
                return_value = logItem.getName();
                break;
            case 2:
                return_value = logItem.getDate();
                break;
            case 3:
                return_value = logItem.getTime();
                break;
            case 4:
                return_value = logItem.getTagMessage();
                break;
            default:
                return_value = logItem.getID();

        }
        return return_value;
    }

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
        textView.setText(Integer.toString(logItem.getID()));

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

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
