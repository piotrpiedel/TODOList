package com.example.piotrek.todolist.ui.lists;

import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.piotrek.todolist.R;
import com.example.piotrek.todolist.model.Category.Category;
import com.example.piotrek.todolist.ui.tasks.views.TasksView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList;

    private CategoryPresenter categoryPresenter;

    CategoryAdapter(List<Category> categories, CategoryPresenter categoryPresenter) {
        this.categoryList = categories;
        this.categoryPresenter = categoryPresenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.categoryTitle.setText(category.getCategoryTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity(category, view);
            }
        });
        holder.categoryButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryPresenter.onDeleteButtonClicked(category);
            }
        });
    }

    private void startNewActivity(Category category, View view) {
        Intent intent = new Intent(view.getContext(), TasksView.class);
        intent.putExtra("idCategory", category.getId());
        view.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_title)
        TextView categoryTitle;

        @BindView(R.id.item_button_delete)
        Button categoryButtonDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}