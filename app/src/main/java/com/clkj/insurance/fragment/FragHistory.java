package com.clkj.insurance.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.activities.comSearch.ActSearch;
import com.clkj.insurance.adapter.HistoryBillAdapter;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.TempListBaseFragment;
import com.clkj.insurance.response.ResponseHistoryBillList;
import com.lf.tempcore.tempBase.adapter.ListBaseAdapter;
import com.lf.tempcore.tempBase.view.ErrorLayout;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;
import com.lf.tempcore.tempRecyclerView.TempRVDividerDecoration;

import butterknife.OnClick;
import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 * 历史
 */
public class FragHistory extends TempListBaseFragment<ResponseHistoryBillList.ResultBean.PageRecordBean,ResponseHistoryBillList> {

    protected final int REQUEST_COUNT = 10;//每页展示多少条数据

   /*@Bind(R.id.recycler_view)LRecyclerView frag_history_recycler;
    @Bind(R.id.top_btn)Button top_btn;*/

    @Override
    public int setLayoutId() {
        return R.layout.fragment_frag_history;
    }
    @Override
    protected ListBaseAdapter<ResponseHistoryBillList.ResultBean.PageRecordBean> getListAdapter() {
        return new HistoryBillAdapter(getContext(),getActivity());
    }

    @Override
    public Observable<ResponseHistoryBillList> sendRequestData() {
        return TempRemoteApiFactory.createRemoteApi(TempAction.class).queryHistoryBillList(TempLoginConfig.sf_getSueid(),"1",TempLoginConfig.sf_getPassWord(),"",mCurrentPage+"",REQUEST_COUNT);
    }


    @Override
    protected void initLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new TempRVDividerDecoration(Color.parseColor("#f3f3f3"),15));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void OnLoadDataSuccess(ResponseHistoryBillList responseHistoryBillList) {
        totalPage=responseHistoryBillList.getResult().getPageRecord().size();
        mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        if (mCurrentPage == 1) {
            mListAdapter.setDataList(responseHistoryBillList.getResult().getPageRecord());
        } else {
            mListAdapter.addAll(responseHistoryBillList.getResult().getPageRecord());
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

    }

    @Override
    protected void setListeners() {
//        top_btn.setVisibility(View.VISIBLE);
    }

    @Override
    protected void bundleValues() {
    }
    @Override
    @OnClick({R.id.toolbar_menu/*,R.id.top_btn*/})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.toolbar_menu://搜索
                startActivity(new Intent(getActivity(), ActSearch.class));
                break;
            /*case R.id.top_btn://返回顶部
                frag_history_recycler.smoothScrollToPosition(0);
                break;*/
        }
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        ImageView toolbar_back = (ImageView) toolbar.findViewById(R.id.toolbar_back);
        toolbar_back.setVisibility(View.GONE);
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText("历史运单记录");
        toolbar.findViewById(R.id.toolbar_menu).setVisibility(View.VISIBLE);
    }
}
