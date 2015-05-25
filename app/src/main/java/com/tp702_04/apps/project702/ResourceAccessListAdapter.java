package com.tp702_04.apps.project702;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jamesbutler on 16/04/15.
 */
public class ResourceAccessListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ResourceAccessItem> resourceAccessItems;

    public ResourceAccessListAdapter(Context newContext) {
        this.context = newContext;
        this.resourceAccessItems = new ArrayList<>();
    }

    public void addAll(ArrayList<ResourceAccessItem> newResourceAccessItems) {
        this.resourceAccessItems.addAll(newResourceAccessItems);
    }

    public void clear() {
        this.resourceAccessItems.clear();
    }

    @Override
    public int getGroupCount() {
        return resourceAccessItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // Children refers to the number of Children Layouts
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return resourceAccessItems.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) { return resourceAccessItems.get(groupPosition); }

    @Override
    public long getGroupId(int groupPosition) {
        return resourceAccessItems.get(groupPosition).getID();
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
        ResourceAccessItem resourceAccessItem = resourceAccessItems.get(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.header_layout, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.list_group_header);
        textView.setText(resourceAccessItem.getApp());

        if (resourceAccessItem.getIsMachineAccess() == "true") {
            textView.setBackgroundColor(Color.RED);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ResourceAccessItem resourceAccessItem = resourceAccessItems.get(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_layout, null);
        }

        TextView textViewName = (TextView) convertView.findViewById(R.id.list_child_1);
        textViewName.setText(resourceAccessItem.getName());

        TextView textViewDate = (TextView) convertView.findViewById(R.id.list_child_2);
        textViewDate.setText(resourceAccessItem.getDate());

        TextView textViewTime = (TextView) convertView.findViewById(R.id.list_child_3);
        textViewTime.setText(resourceAccessItem.getTime());

        TextView textViewTagMessage = (TextView) convertView.findViewById(R.id.list_child_4);
        textViewTagMessage.setText(resourceAccessItem.getTagMessage());

        if (resourceAccessItem.getIsMachineAccess() == "true") {
            textViewName.setBackgroundColor(Color.parseColor("#FF9999"));
            textViewDate.setBackgroundColor(Color.parseColor("#FF9999"));
            textViewTime.setBackgroundColor(Color.parseColor("#FF9999"));
            textViewTagMessage.setBackgroundColor(Color.parseColor("#FF9999"));
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
