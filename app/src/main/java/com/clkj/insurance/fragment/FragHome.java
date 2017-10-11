package com.clkj.insurance.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.adapter.UserSwayBillAdapter;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.TempListBaseFragment;
import com.clkj.insurance.response.ResponseUserSwayBillList;
import com.lf.tempcore.tempBase.adapter.ListBaseAdapter;
import com.lf.tempcore.tempBase.interfaces.OnItemClickListener;
import com.lf.tempcore.tempBase.interfaces.OnItemLongClickListener;
import com.lf.tempcore.tempBase.view.ErrorLayout;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempRecyclerView.TempRVDividerDecoration;

import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 * 首页  运单通知
 * 加刷新的时候就继承TempListBaseFragment
 */
public class FragHome extends TempListBaseFragment<ResponseUserSwayBillList.ResultBean.PageRecordBean,ResponseUserSwayBillList> {

    protected final int REQUEST_COUNT = 10;//每页展示多少条数据


    @Override
    public int setLayoutId() {
        return R.layout.fragment_frag_home;
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void bundleValues() {
    }



    @Override
    protected void OnViewClicked(View v) {

    }


    /**
     * 获取适配器
     * @return
     */
    @Override
    protected ListBaseAdapter<ResponseUserSwayBillList.ResultBean.PageRecordBean> getListAdapter() {
        return new UserSwayBillAdapter(getContext(),getActivity());
    }

    /**
     * 请求接口
     * @return
     */
    @Override
    public Observable<ResponseUserSwayBillList> sendRequestData() {
        return TempRemoteApiFactory.createRemoteApi(TempAction.class).queryUserSwayBillList(TempLoginConfig.sf_getSueid(),"1",TempLoginConfig.sf_getPassWord(),mCurrentPage+"", REQUEST_COUNT);
    }
    /**
     * 初始化RecyclerViewLayoutManager
     */
    @Override
    protected void initLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new TempRVDividerDecoration(Color.parseColor("#f3f3f3"),15));
        mRecyclerView.setHasFixedSize(true);
    }


    /**
     * 数据请求成功之后
     * @param
     */
    @Override
    public void OnLoadDataSuccess(ResponseUserSwayBillList responseUserSwayBillList) {
        totalPage=responseUserSwayBillList.getResult().getPageRecord().size();
        mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        if (mCurrentPage == 1) {
            mListAdapter.setDataList(responseUserSwayBillList.getResult().getPageRecord());
        } else {
            mListAdapter.addAll(responseUserSwayBillList.getResult().getPageRecord());
        }
        // 判断等于是因为最后有一项是listview的状态
        if (mListAdapter.getItemCount() == 0) {
            if (needShowEmptyNoData()) {
                mErrorLayout.setNoDataContent(getNoDataTip());
                mErrorLayout.setErrorType(ErrorLayout.NODATA);
            }
        }
    }

    @Override
    protected void initOnItemClickManager() {

        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            /*    Intent itent= new Intent(ActHottestRecommended.this, ActNewVideoDetails.class);
                itent.putExtra("roomid",mListAdapter.getDataList().get(position).getMgooId());
                startActivity(itent);*/

            }

        });
        mRecyclerViewAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


    }


    @Override
    public void setToolbar(Toolbar toolbar) {
        ImageView toolbar_back = (ImageView) toolbar.findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.GONE);
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText("运单通知");
    }
}
