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

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.clkj.insurance.R.id.address_name;

/**
 * 选择地址 第二步
 */
public class ActSelectedAddressSecond extends BaseActivity {
    @Bind(R.id.act_selected_address_secend_recycler)RecyclerView act_selected_address_secend_recycler;
    private String address_id;

    /**
     * 获取区域编号
     */
    TempDbAreaHelper tempDbAreaHelper;
    private List<TempAreaBean> mList2;//第一个所有地区的列表
    private ListBaseAdapter<TempAreaBean> baseAdapter;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_selected_address_second);
    }

    @Override
    protected void findViews() {
        Intent intent = getIntent();
        address_id = intent.getStringExtra("address_id");

        tempDbAreaHelper = new TempDbAreaHelper(this);
        mList2 = tempDbAreaHelper.getCityDataByParentId(address_id);//获取中国所有的地区
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        initRv();
    }

    private void initRv() {
        act_selected_address_secend_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        act_selected_address_secend_recycler.addItemDecoration(new TempRVDividerDecoration(Color.parseColor("#e4e4e4"),1));
        baseAdapter=new ListBaseAdapter<TempAreaBean>(this) {
            @Override
            public int getLayoutId() {
                return R.layout.act_selected_address_first_recycler_layout;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                holder.setText(address_name,mList2.get(position).getA_name());
            }
        };
        baseAdapter.setDataList(mList2);
        act_selected_address_secend_recycler.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<TempAreaBean>() {
            @Override
            public void OnItemClick(View itemView, int position, TempAreaBean tempAreaBean) {
                itemView.setBackgroundColor(Color.parseColor("#f4f4f4"));
                Intent intent1=new Intent(ActSelectedAddressSecond.this,ActSelectedAddressThird.class);
                intent1.putExtra("addressId",tempAreaBean.getA_id()+"");
                startActivity(intent1);
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
