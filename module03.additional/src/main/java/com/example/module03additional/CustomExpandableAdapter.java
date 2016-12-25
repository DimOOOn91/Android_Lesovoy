package com.example.module03additional;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.Date;
import java.util.List;


public class CustomExpandableAdapter extends ExpandableRecyclerAdapter<CustomExpandableAdapter.InputViewHolder, CustomExpandableAdapter.InputChildViewHolder> {

    private LayoutInflater mInflater;

    public CustomExpandableAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public InputViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = mInflater.inflate(R.layout.parent_item_layout, parentViewGroup, false);
        return new InputViewHolder(view);
    }

    @Override
    public InputChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = mInflater.inflate(android.R.layout.simple_list_item_1, childViewGroup, false);
        return new InputChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(InputViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        Input input = (Input) parentListItem;
        parentViewHolder.bind(input);
    }

    @Override
    public void onBindChildViewHolder(InputChildViewHolder childViewHolder, int position, Object childListItem) {
        Date date = (Date) childListItem;
        childViewHolder.bind(date);
    }

    public class InputViewHolder extends ParentViewHolder {

        private TextView mCounter;
        private TextView mTitle;

        public InputViewHolder(View itemView) {
            super(itemView);
            mCounter = (TextView) itemView.findViewById(R.id.counter);
            mTitle = (TextView) itemView.findViewById(R.id.title);
        }

        public void bind(Input input) {
            mCounter.setText(String.valueOf(input.getChildItemList().size()));
            mTitle.setText(input.getTitle().name());
        }
    }

    public class InputChildViewHolder extends ChildViewHolder {

        private TextView mText;

        public InputChildViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(android.R.id.text1);
        }

        public void bind(Date date) {
            mText.setText(String.format("%1$tD  %1$tH:%1$tM:%1$tS", date));
        }
    }

}
