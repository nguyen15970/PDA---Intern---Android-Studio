package com.example.customadapter.Product;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customadapter.DB.DbProductHelper;
import com.example.customadapter.R;

import java.util.List;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ListProductHolder> {
    private Activity activity;
    private List<Product> productList;

    public ListProductAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<Product> products) {
        productList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ListProductHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_product, parent, false);
        return new ListProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ListProductHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        if (productList == null)
            return 0;
        return productList.size();
    }

    public class ListProductHolder extends RecyclerView.ViewHolder {
        private TextView textViewID, textViewName, textViewUnit, textViewPrice;
        private Button deleteBtn;
        private Product singleProduct;

        public ListProductHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            textViewID = (TextView) itemView.findViewById(R.id.product_id);
            textViewName = (TextView) itemView.findViewById(R.id.product_name);
            textViewUnit = (TextView) itemView.findViewById(R.id.product_unit);
            textViewPrice = (TextView) itemView.findViewById(R.id.product_price);
            deleteBtn = (Button) itemView.findViewById(R.id.delete_btn);
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productList.remove(getAdapterPosition());
                    new DbProductHelper(activity).deleteProduct(singleProduct.getID());
                    ListProductAdapter.this.notifyDataSetChanged();
                }
            });
        }

        @SuppressLint("SetTextI18n")
        public void bind(Product product) {
            singleProduct = product;
            textViewID.setText(singleProduct.getID());
            textViewName.setText(singleProduct.getName());
            textViewUnit.setText(singleProduct.getUnit());
            textViewPrice.setText(Integer.toString(singleProduct.getPrice()));
        }
    }
}
