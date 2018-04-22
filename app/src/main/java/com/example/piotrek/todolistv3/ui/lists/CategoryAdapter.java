package com.example.piotrek.todolistv3.ui.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.piotrek.todolistv3.R;
import com.example.piotrek.todolistv3.model.Category;

import java.util.ArrayList;

import butterknife.BindView;


public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context mContext;
    public CategoryAdapter(Context context) {
        super(context,0);
        this.mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Category category = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.categoryrow, parent, false);
        }
        // Lookup view for data population
        TextView categoryTitle = convertView.findViewById(R.id.category_title);
        TextView idCategory = convertView.findViewById(R.id.id_category);

        // Populate the data into the template view using the data object
        categoryTitle.setText(category.getCategoryName());
        String idCat= String.valueOf(category.getIdCategory());
//        idCategory.setText(category.getIdCategory());
        idCategory.setText(idCat);
        // Return the completed view to render on screen
        return convertView;
    }
}