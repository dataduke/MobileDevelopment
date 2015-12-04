package com.epages.epagesshopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

public class ProductsPagerViewFragment extends Fragment {

    public static final String PRODUCT_NAME = "PRODUCT_NAME";

    public static final ProductsPagerViewFragment newInstance(JSONObject ePagesJSONProduct)
    {
        ProductsPagerViewFragment f = new ProductsPagerViewFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(PRODUCT_NAME, ePagesJSONProduct.getString("name"));
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String message = getArguments().getString(PRODUCT_NAME);
        View v = inflater.inflate(R.layout.products_pager_view_fragment, container, false);
        TextView messageTextView = (TextView)v.findViewById(R.id.product_pager_view_fragment_product_name);
        messageTextView.setText(message);

        return v;
    }
}
