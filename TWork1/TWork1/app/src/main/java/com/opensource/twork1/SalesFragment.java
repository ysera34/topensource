package com.opensource.twork1;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.opensource.twork1.MainActivity.EXTRA_SALES;

/**
 * Created by yoon on 2017. 1. 23..
 */

public class SalesFragment extends Fragment
        implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{

//    private static final String URL = "http://192.168.211.170:3000";
    private static final String URL = "http://192.168.43.186:3000";

    public static SalesFragment newInstance() {

        Bundle args = new Bundle();

        SalesFragment fragment = new SalesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RequestQueue mQueue;

    private EditText mUrlEditText;
    private Button mUrlRequestButton;
    private RecyclerView mSalesRecyclerView;
    private SalesAdapter mSalesAdapter;
    private ArrayList<Sales> mSalesArrayList;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQueue = Volley.newRequestQueue(getActivity());

        mSalesArrayList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, container, false);
        mUrlEditText = (EditText) view.findViewById(R.id.url_edit_text);
        mUrlEditText.setText(URL);
        mUrlRequestButton = (Button) view.findViewById(R.id.url_request_button);
        mUrlRequestButton.setOnClickListener(this);
        mSalesRecyclerView = (RecyclerView) view.findViewById(R.id.sales_recycler_view);
        mSalesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary, R.color.colorPrimaryDark);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void updateUI() {
        if (mSalesAdapter == null) {
            mSalesAdapter = new SalesAdapter(mSalesArrayList);
            mSalesRecyclerView.setAdapter(mSalesAdapter);
        } else {
            mSalesAdapter.setSalesArrayList(mSalesArrayList);
            mSalesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.url_request_button:
                requestSales();
                break;
        }
    }

    Handler mHandler = new Handler();

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXTRA_SALES, mSalesArrayList);
    }

    private class SalesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private ArrayList<Sales> mSalesArrayList;

        public SalesAdapter(ArrayList<Sales> salesArrayList) {
            mSalesArrayList = salesArrayList;
        }

        public void setSalesArrayList(ArrayList<Sales> salesArrayList) {
            mSalesArrayList = salesArrayList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view;
            if (viewType == HEADER_VIEW_TYPE) {
                view = layoutInflater.inflate(R.layout.list_header_sales, parent, false);
                return new HeaderHolder(view);
            } else if (viewType == BODY_VIEW_TYPE) {
                view = layoutInflater.inflate(R.layout.list_item_sales, parent, false);
                return new SalesHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeaderHolder) {
                ((HeaderHolder) holder).bindDataSum(mSalesArrayList.size());
            } else if (holder instanceof SalesHolder) {
                Sales sales = mSalesArrayList.get(position-1);
                ((SalesHolder) holder).bindSales(sales);
            }

        }

        @Override
        public int getItemCount() {
            return mSalesArrayList.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
//            return super.getItemViewType(position);
            if (position == 0) {
                return HEADER_VIEW_TYPE;
            } else {
                return BODY_VIEW_TYPE;
            }
        }
    }

    private static final int HEADER_VIEW_TYPE = 0;
    private static final int BODY_VIEW_TYPE = 1;

    private class HeaderHolder extends RecyclerView.ViewHolder {

        private TextView mSumTextView;

        public HeaderHolder(View itemView) {
            super(itemView);

            mSumTextView = (TextView) itemView.findViewById(R.id.sum_text_view);
        }

        public void bindDataSum(int sum) {
            mSumTextView.setText(
                    getString(R.string.format_list_sum, String.valueOf(sum)));
        }

    }

    private class SalesHolder extends RecyclerView.ViewHolder {

        private Sales mSales;

        private ImageView mImageView;
        private TextView mMonthTextView;
        private TextView mGuestCountTextView;
        private TextView mAmountSalesTextView;

        public SalesHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.image_view);
            mMonthTextView = (TextView) itemView.findViewById(R.id.month_text_view);
            mGuestCountTextView = (TextView) itemView.findViewById(R.id.guest_count_text_view);
            mAmountSalesTextView = (TextView) itemView.findViewById(R.id.amount_sales_text_view);
        }

        public void bindSales(Sales sales) {
            mSales = sales;

            ImageLoader.getInstance().displayImage(mSales.url, mImageView,
                    new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {

                        }
                    });
            mMonthTextView.setText(String.valueOf(mSales.month));
            mGuestCountTextView.setText(
                    getString(R.string.format_guest_count, String.valueOf(mSales.count)));
            mAmountSalesTextView.setText(
                    getString(R.string.format_amount_sales, String.valueOf(mSales.sales)));
        }
    }

    private void requestSales() {
//        for (int i = 1; i <= 10; i++) {
//            Sales sales = new Sales();
//            sales.month = i + "월";
//            sales.guestCount = 10 * i;
//            sales.amountSales = 100 * i;
//            mSalesArrayList.add(sales);
//        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, mUrlEditText.getText().toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("onResponse", response.toString());
                        mSalesArrayList = parseResponse(response.toString());
                        updateUI();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.w("onErrorResponse", error.getMessage());
                    }
                }
        );
//        jsonObjectRequest.setShouldCache(false);
        mQueue.add(jsonObjectRequest);
    }

    private ArrayList<Sales> parseResponse(String response) {
        ArrayList<Sales> salesArrayList = new ArrayList<>();
        Gson gson = new Gson();

        SalesList list = gson.fromJson(response, SalesList.class);

        for (int i = 0; i < list.data.size(); i++) {
            Sales sales = list.data.get(i);
            salesArrayList.add(sales);
        }
        return salesArrayList;
    }

    OnPassArrayListListener mOnPassArrayListListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnPassArrayListListener = (OnPassArrayListListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnPassArrayListListener");
        }
    }
}
