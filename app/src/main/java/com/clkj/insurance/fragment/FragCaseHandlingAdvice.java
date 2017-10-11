package com.clkj.insurance.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.clkj.insurance.activities.comMessage.ActMessageDetail;
import com.clkj.insurance.adapter.CaseMessageAdapter;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.TempListBaseFragment;
import com.clkj.insurance.response.ResponseCaseMessageList;
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
 * 案件处理意见
 */
public class FragCaseHandlingAdvice extends TempListBaseFragment<ResponseCaseMessageList.ResultBean.PageRecordBean,ResponseCaseMessageList> {

    protected final int REQUEST_COUNT = 10;

    @Override
    protected ListBaseAdapter<ResponseCaseMessageList.ResultBean.PageRecordBean> getListAdapter() {
        return new CaseMessageAdapter(getContext());
    }

    @Override
    public Observable<ResponseCaseMessageList> sendRequestData() {
        return TempRemoteApiFactory.createRemoteApi(TempAction.class).getCaseMessageList(TempLoginConfig.sf_getSueid(),"1",TempLoginConfig.sf_getPassWord(),mCurrentPage+"", REQUEST_COUNT+"");
    }

    @Override
    protected void initLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new TempRVDividerDecoration(Color.parseColor("#f3f3f3"),15));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void OnLoadDataSuccess(ResponseCaseMessageList responseUserSwayBillList) {
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
                Intent itent= new Intent(getActivity(), ActMessageDetail.class);
                itent.putExtra("roomid",mListAdapter.getDataList().get(position).getMffoId());
                startActivity(itent);

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







    /*@Bind(R.id.frag_case_handing_advice_recycler)RecyclerView frag_case_handing_advice_recycler;
    private String[] titles={"你好,我是人保专员,请为这次案件补传信息!","你好,我是人保专员,请为这次案件补传信息!"};
    private ListBaseAdapter<String> baseAdapter;
    private List<String> data=new ArrayList<>();
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag_case_handling_advice, container, false);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bundleValues() {
        initRv();
    }
    *//**
     * 加载数据
     *//*
    private void initRv() {
        for (int i = 0; i <titles.length ; i++) {
            data.add(titles[i]);
        }
        frag_case_handing_advice_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        baseAdapter=new ListBaseAdapter<String>(getContext()) {
            @Override
            public int getLayoutId() {
                return R.layout.frag_case_handing_advice_recycler_layout;
            }

            @Override
            public void onBindItemHolder(SuperViewHolder holder, int position) {
                holder.setText(R.id.message_title,data.get(position));
            }
        };
        baseAdapter.setDataList(data);
        frag_case_handing_advice_recycler.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<String>() {
            @Override
            public void OnItemClick(View itemView, int position, String s) {
                startActivity(new Intent(getActivity(), ActMessageDetail.class));
            }
        });

    }

    @Override
    protected void OnViewClicked(View v) {

    }

    @Override
    public void setToolbar(Toolbar toolbar) {

    }*/
}
