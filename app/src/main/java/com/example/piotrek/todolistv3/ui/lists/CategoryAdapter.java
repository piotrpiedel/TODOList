package com.example.piotrek.todolistv3.ui.lists;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.piotrek.todolistv3.R;
import com.example.piotrek.todolistv3.model.Category;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryAdapter extends ArrayAdapter<Category> {

    private Context mContext;
    private CategoryPresenter categoryPresenter;

    public CategoryAdapter(Context context, CategoryPresenter categoryPresenter) {
        super(context, 0);
        this.mContext = context;
        this.categoryPresenter = categoryPresenter;
    }

    @Nullable
    @Override
    public Category getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.category_item_view, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.categoryTitle.setText(Objects.requireNonNull(getItem(position), "Category must not be null - category Adapter").getCategoryTitle());
        holder.categoryButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryPresenter.onDeleteButtonClicked(getItem(position));
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.category_title)
        TextView categoryTitle;

        @BindView(R.id.category_button_delete)
        Button categoryButtonDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}