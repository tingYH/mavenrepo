package com.cubepayment.utilstest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cubepayment.utilstest.R;
import com.cubepayment.utilstest.ViewTypeInterface;
import com.cubepayment.utilstest.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChengKai YH
 * @version $
 * <p/>
 * <p/> $
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private static final int ITEM_TYPE_USER = 1;
    private List<ViewTypeInterface> mDataList;
    private View view;
    private MyViewHolder holder;

    public UserAdapter() {
        mDataList = new ArrayList<>();
    }


    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_USER:
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_user, parent, false);
                MyViewHolder vh = new MyViewHolder(v);
                return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_TYPE_USER) {
            MyViewHolder viewHolder = holder;
            User user = (User) mDataList.get(position);

            viewHolder.tv_name.setText(user.getFirstName());
            viewHolder.tv_email.setText(user.getEmail());
        }
    }

    @Override
    public int getItemViewType(int position) {
        ViewTypeInterface type = mDataList.get(position);
        if (type instanceof User) {
            return ITEM_TYPE_USER;
        }
        return 0;
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_email = itemView.findViewById(R.id.tv_email);
        }
    }

    public void setData(@NonNull List<? extends ViewTypeInterface> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
    }
}
