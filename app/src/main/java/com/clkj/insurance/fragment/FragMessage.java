package com.clkj.insurance.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clkj.insurance.R;
import com.clkj.insurance.api.TempAction;
import com.clkj.insurance.base.BaseFragment;
import com.clkj.insurance.response.ReponseCaseMessage;
import com.clkj.insurance.response.ReponseMessage;
import com.lf.tempcore.tempConfig.TempLoginConfig;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemoteApiFactory;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 消息
 */
public class FragMessage extends BaseFragment {

    @Bind(R.id.frag_message_case_handling_advice)TextView frag_message_case_handling_advice;//案件处理意见
    @Bind(R.id.frag_message_system_message)TextView frag_message_system_message;//系统消息
    @Bind(R.id.frag_message_system_message_num)TextView frag_message_system_message_num;//系统消息条数
    @Bind(R.id.frag_message_system_anjian_num)TextView frag_message_system_anjian_num;//案件消息条数


    private Fragment[] mFragments;
    private int mIndex=0;
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag_message, container, false);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bundleValues() {
        initFragment();
    }

    private void initFragment() {
        //系统消息
        FragSystemMessage systemMessage =new FragSystemMessage();
        //案件处理意见
        FragCaseHandlingAdvice caseHandlingAdvice =new FragCaseHandlingAdvice();

        //添加到数组
        mFragments = new Fragment[]{systemMessage,caseHandlingAdvice};

        //开启事务

        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();

        //添加外卖
        ft.replace(R.id.content1,systemMessage).commit();

        //默认设置为第0个
//        fragHome.changeStatusBar=true;
        setIndexSelected(0);


    }



    private void setIndexSelected(int index) {

        if(mIndex==index){
            return;
        }
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft              = fragmentManager.beginTransaction();


        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if(!mFragments[index].isAdded()){
            ft.add(R.id.content1,mFragments[index]).show(mFragments[index]);
        }else {
            ft.show(mFragments[index]);
        }

        ft.commit();
        //再次赋值
        mIndex=index;

    }
    @Override
    @OnClick({R.id.frag_message_system_message,R.id.frag_message_case_handling_advice})
    protected void OnViewClicked(View v) {
        switch (v.getId()){
            case R.id.frag_message_system_message://系统消息
                frag_message_system_message.setTextColor(Color.parseColor("#292929"));
                frag_message_case_handling_advice.setTextColor(Color.parseColor("#575757"));
                setIndexSelected(0);
                break;
            case R.id.frag_message_case_handling_advice://案件处理意见
                frag_message_system_message.setTextColor(Color.parseColor("#575757"));
                frag_message_case_handling_advice.setTextColor(Color.parseColor("#292929"));
                setIndexSelected(1);
                break;
        }
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        toolbar.findViewById(R.id.toolbar_back).setVisibility(View.GONE);
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText("消息中心");
    }

    @Override
    public void onResume() {
        super.onResume();
        queryUnreadMessage();
        queryUnreadAnjianMessage();
    }

    /**
     * 案件未读条数
     */
    private void queryUnreadAnjianMessage(){
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryUnreadCaseMessage(TempLoginConfig.sf_getSueid(),"1",TempLoginConfig.sf_getPassWord()), new TempRemoteApiFactory.OnCallBack<ReponseCaseMessage>() {
            @Override
            public void onSucceed(ReponseCaseMessage data) {
                if (data.getType()==1){
                    frag_message_system_anjian_num.setText(data.getResult().getCount());
                }else {
                    frag_message_system_anjian_num.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCompleted() {
            }

            @Override
                public void onError(Throwable e) {
            }
        });
    }

    /**
     * 消息未读条数
     */
    private void queryUnreadMessage(){
        TempRemoteApiFactory.executeMethod(TempRemoteApiFactory.createRemoteApi(TempAction.class).queryUnreadMallMessage(TempLoginConfig.sf_getSueid(),"1",TempLoginConfig.sf_getPassWord()), new TempRemoteApiFactory.OnCallBack<ReponseMessage>() {
            @Override
            public void onSucceed(ReponseMessage data) {
                if (data.getType()==1){
                     frag_message_system_message_num.setText(data.getResult().getCount());
                }else {
                    frag_message_system_message_num.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

}
