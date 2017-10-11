package com.clkj.insurance.activities.comSelectedAddress;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.base.BaseActivity;
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
 * 选择所在地区 第一步
 */
public class ActSelectedAddressFirst extends BaseActivity {
    @Bind(R.id.act_selected_address_first_recycler)RecyclerView act_selected_address_first_recycler;
    private ListBaseAdapter<TempAreaBean> baseAdapter;
    private String[] names={"北京","天津","河北省","辽宁省","重庆市","陕西省"};
    private List<String> data=new ArrayList<>();

    /**
     * 获取区域编号
     */
    TempDbAreaHelper tempDbAreaHelper;
    private List<TempAreaBean> mList1;//第一个所有地区的列表
    private String area0 = "2";//中国

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_selected_address_first);
    }

    @Override
    protected void findViews() {
        tempDbAreaHelper = new TempDbAreaHelper(this);
        mList1 = tempDbAreaHelper.getCityDataByParentId(area0);//获取中国所有的地区
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
        act_selected_address_first_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        act_selected_address_first_recycler.addItemDecoration(new TempRVDividerDecoration(Color.parseColor("#e4e4e4"),1));
        baseAdapter=new ListBaseAdapter<TempAreaBean>(this) {
            @Override
            public int getLayoutId() {
                return R.layout.act_selected_address_first_recycler_layout;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                holder.setText(R.id.address_name,mList1.get(position).getA_name());
            }
        };
        baseAdapter.setDataList(mList1);
        act_selected_address_first_recycler.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<TempAreaBean>() {
            @Override
            public void OnItemClick(View itemView, int position, TempAreaBean tempAreaBean) {
                itemView.setBackgroundColor(Color.parseColor("#f4f4f4"));
                Intent intent=new Intent(ActSelectedAddressFirst.this,ActSelectedAddressSecond.class);
                TextView address_name = (TextView) itemView.findViewById(R.id.address_name);
                String name = address_name.getText().toString();
                intent.putExtra("address_name",name);
                intent.putExtra("address_id",tempAreaBean.getA_id()+"");
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        act_selected_address_first_recycler.setBackgroundColor(Color.parseColor("#ffffff"));
        if (baseAdapter!=null){
            baseAdapter.notifyDataSetChanged();
        }
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
