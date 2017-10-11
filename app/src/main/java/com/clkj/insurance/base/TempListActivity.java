package com.clkj.insurance.base;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.lf.tempcore.tempBase.adapter.ListBaseAdapter;
import com.lf.tempcore.tempBase.interfaces.OnLoadMoreListener;
import com.lf.tempcore.tempBase.interfaces.OnRefreshListener;
import com.lf.tempcore.tempBase.recyclerview.LRecyclerView;
import com.lf.tempcore.tempBase.recyclerview.LRecyclerViewAdapter;
import com.lf.tempcore.tempBase.view.CommonHeader;
import com.lf.tempcore.tempBase.view.ErrorLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者:caoyang
 * 创建时间:2017/6/13 19:03
 * 描述: DATA 必须包含有 T 的集合
 */

public abstract class TempListActivity<T ,DATA> extends BaseActivity {
    /**每一页展示多少条数据*/
    protected int mCurrentPage = 0;
    protected int totalPage = 0;
    protected final int REQUEST_COUNT = 10;

    @Bind(R.id.recycler_view)
    protected LRecyclerView mRecyclerView;

    @Bind(R.id.error_layout)
    protected ErrorLayout mErrorLayout;

    @Bind(R.id.top_btn)
    protected Button toTopBtn;

    @Bind(R.id.line_one)
    protected LinearLayout line_one;

    @Bind(R.id.line_other)
    protected LinearLayout line_other;


   /* @Bind(R.id.search_ly)
    protected LinearLayout search_ly;

    @Bind(R.id.pop_ly)
    protected LinearLayout pop_ly;

    @Bind(R.id.searc_view)
    protected View searc_view;*/

    @Bind(R.id.toolbar_top)
    protected Toolbar toolbarTop;


  /*  @Bind(R.id.iv_clear_search)
    protected ImageView mIvClearSearch;


    @Bind(R.id.search_et)
    protected EditText mSearchEt;
*/
    protected ListBaseAdapter<T> mListAdapter;
    protected LRecyclerViewAdapter mRecyclerViewAdapter;

    protected boolean isRequestInProcess = false;
    protected boolean mIsStart = false;

    protected CommonHeader headerView;
    private String title;
    private int titleColor=0;
    private TextView mTitle;

   /* @Bind(R.id.dialog_search_send)
    protected Button mSearchSend;*/

    @Override
    protected int getLayoutId() {
        return /*R.layout.act_list_index*/0;
    }

    protected void setToolbarTop(String title,int titleColor){
        this.title = title;
        this.titleColor = titleColor;
        if (mTitle!=null){
            mTitle.setText(title==null?getResources().getString(R.string.app_name):title);
            mTitle.setTextColor(titleColor==0?getResources().getColor(R.color.black):titleColor);
        }
    }


    @Override
    protected void findViews() {
        ButterKnife.bind(this);
        onRefresh();
        if (mListAdapter != null) {
            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        } else {
            mListAdapter = getListAdapter();
            if (requestDataIfViewCreated()) {
                mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
//                TLog.log("requestDataIfViewCreated  requestData");
                requestData();
            } else {
                mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
            }

        }
        initLayoutManager();
        mRecyclerViewAdapter = new LRecyclerViewAdapter(mListAdapter);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        initOnItemClickManager();
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshView();
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mListAdapter.getDataList().size() < totalPage) {
                    // loading more
                    requestData();
                } else {
                    mRecyclerView.setNoMore(true);
                }
            }
        });

        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {

            @Override
            public void onScrollUp() {
                // 滑动时隐藏float button
                if (toTopBtn.getVisibility() == View.VISIBLE) {
                    toTopBtn.setVisibility(View.GONE);
                    animate(toTopBtn, R.anim.floating_action_button_hide);
                }
            }

            @Override
            public void onScrollDown() {
                if (toTopBtn.getVisibility() != View.VISIBLE) {
                    toTopBtn.setVisibility(View.VISIBLE);
                    animate(toTopBtn, R.anim.floating_action_button_show);
                }
            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {

                if (null != headerView) {
                    if (distanceY == 0 || distanceY < headerView.getHeight()) {
                        toTopBtn.setVisibility(View.GONE);
                    }
                } else {
                    if (distanceY == 0) {
                        toTopBtn.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(int state) {

            }

        });

        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCurrentPage = 0;
                mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
                requestData();
            }
        });

        toTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.scrollToPosition(0);
                toTopBtn.setVisibility(View.GONE);
            }
        });

        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.gray_text, R.color.gray_text, R.color.app_bg);
        //设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.gray_text, R.color.gray_text, R.color.app_bg);
    }


    protected boolean requestDataIfViewCreated() {
        return true;
    }

    private void animate(View view, int anim) {
        if (anim != 0) {
            Animation a = AnimationUtils.loadAnimation(view.getContext(), anim);
            view.startAnimation(a);
        }
    }

    /** 设置顶部正在加载的状态 */
    protected void setSwipeRefreshLoadingState() {
//        TLog.log("setSwipeRefreshLoadingState ");
    }

    /**
     * 设置顶部加载完毕的状态
     */
    protected void setSwipeRefreshLoadedState() {
        if(null != mRecyclerView) {
            mRecyclerView.refreshComplete(REQUEST_COUNT);
        }

    }

    // 完成刷新
    protected void executeOnLoadFinish() {
        setSwipeRefreshLoadedState();
        isRequestInProcess = false;
        mIsStart = false;
    }
    //设置Adapter
    protected abstract ListBaseAdapter<T> getListAdapter();

//    Observable<ListResponse<T>> mObservable;
    Observable<DATA> mObservable;

    Subscription mSubscription;
    protected void requestData() {
        mCurrentPage++;
        if(mSubscription !=null){
        }
        mObservable = sendRequestData();
        if(null != mObservable){
            toSubscribe(mObservable);
        }
        isRequestInProcess = true;

        if(mCurrentPage == 1){
            onRefresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        TLog.log("onDestroyView");
        if(mSubscription !=null){
//            TLog.log("onDestroyView mSubscription.isUnsubscribed() = " + mSubscription.isUnsubscribed());
        }
    }

    protected void onRefreshView() {
        if (isRequestInProcess) {
            return;
        }
        // 设置顶部正在刷新
        setSwipeRefreshLoadingState();
        mCurrentPage = 0;
        requestData();

    }

    private void toSubscribe(Observable<DATA> observable) {
        mSubscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(mObserver);

    }


    public abstract  Observable<DATA> sendRequestData();

    protected List<T> parseList(Response<T> response) {
        return null;
    }


    protected void hearparseList(List<T> response) {
    }

    protected abstract void initLayoutManager();

    protected void onRefresh() {
    }

    protected int getPageSize() {
        return 10;
    }
    protected int getTotalPage(byte[] data) {
        return 100;
    }

    public abstract void  OnLoadDataSuccess(DATA data);

    protected void executeOnLoadDataSuccess(DATA data) {


        if(mErrorLayout!=null){
            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        }

        OnLoadDataSuccess(data);
        // 判断等于是因为最后有一项是listview的状态
        if (mListAdapter.getItemCount() == 0) {

            if (needShowEmptyNoData()) {
                mErrorLayout.setNoDataContent(getNoDataTip());
                mErrorLayout.setErrorType(ErrorLayout.NODATA);
            }
        }


    }

    protected boolean needShowEmptyNoData() {
        return true;
    }

    protected void executeOnLoadDataError(String error) {
        executeOnLoadFinish();
        if (mCurrentPage == 1) {
            if(mErrorLayout!=null){
                mErrorLayout.setErrorType(ErrorLayout.NETWORK_ERROR);
            }
        } else {

            //在无网络时，滚动到底部时，mCurrentPage先自加了，然而在失败时却
            //没有减回来，如果刻意在无网络的情况下上拉，可以出现漏页问题
            //find by TopJohn
            mCurrentPage--;

            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
            mListAdapter.notifyDataSetChanged();
        }
    }

    protected  Observer<DATA> mObserver = new Observer<DATA>() {
        @Override
        public void onCompleted() {
            executeOnLoadFinish();
        }

        @Override
        public void onError(Throwable e) {
//            TLog.error("onError " + e.toString());
            executeOnLoadDataError(null);
        }

        @Override
        public void onNext(DATA data) {
//            TLog.log("onNext " );
            executeOnLoadDataSuccess(data);

//            TLog.log("onSuccess totalPage " + totalPage);
        }
    };

    protected abstract void initOnItemClickManager();
    protected String getNoDataTip() {
        return "";
    }
}
