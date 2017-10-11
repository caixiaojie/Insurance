package com.clkj.insurance.activities.comSelectedAddress;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.base.BaseActivity;
import com.lf.tempcore.tempApplication.TempApplication;
import com.lf.tempcore.tempBase.adapter.ListBaseAdapter;
import com.lf.tempcore.tempBase.adapter.SuperViewHolder;
import com.lf.tempcore.tempModule.tempDataBase.TempAreaBean;
import com.lf.tempcore.tempModule.tempDataBase.TempDbAreaHelper;
import com.lf.tempcore.tempRecyclerView.TempRVDividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 选择地址 第三步
 */
public class ActSelectedAddressThird extends BaseActivity {
    @Bind(R.id.act_selected_address_third_recycler)RecyclerView act_selected_address_third_recycler;
    private ListBaseAdapter<TempAreaBean> baseAdapter;
    private String[] names={"南岸区","巴南区","渝北区","江北区","大渡口区","綦江区"};
    private List<String> data=new ArrayList<>();
    /**
     * 获取区域编号
     */
    TempDbAreaHelper tempDbAreaHelper;
    private List<TempAreaBean> mList3;//第三个区域的列表
    private String addressId;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_selected_address_third);
        addressId = getIntent().getStringExtra("addressId");
    }

    @Override
    protected void findViews() {
        tempDbAreaHelper=new TempDbAreaHelper(this);
        mList3=tempDbAreaHelper.getCityDataByParentId(addressId);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        initRv();
    }
    /**
     * 加载数据
     */
    private void initRv() {
        for (int i = 0; i <names.length ; i++) {
            data.add(names[i]);
        }
        act_selected_address_third_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        act_selected_address_third_recycler.addItemDecoration(new TempRVDividerDecoration(Color.parseColor("#e4e4e4"),1));
        baseAdapter=new ListBaseAdapter<TempAreaBean>(this) {
            @Override
            public int getLayoutId() {
                return R.layout.act_selected_address_first_recycler_layout;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                holder.setText(R.id.address_name,mList3.get(position).getA_name());
            }
        };
        baseAdapter.setDataList(mList3);
        act_selected_address_third_recycler.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<TempAreaBean>() {
            @Override
            public void OnItemClick(View itemView, int position, TempAreaBean tempAreaBean) {
                itemView.setBackgroundColor(Color.parseColor("#f4f4f4"));
                SharedPreferences sf = TempApplication.getInstance().getSharedPreferences("address", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                editor.putInt("museAreaId",tempAreaBean.getA_id());
                editor.commit();
                finish();
            }
        });
    }

    @Override
    @OnClick({R.id.toolbar_back})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_back://返回键
                finish();
                break;
        }
    }

    @Override
    public void setToolbar(Toolbar toolbarTop) {
        TextView toolbar_title = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        toolbar_title.setText("选择地区");
    }
}
