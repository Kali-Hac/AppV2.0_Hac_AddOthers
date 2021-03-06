package com.haocong.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.georgeyang.doublescroll.BaseListFragment;

/**
 * Created by yangsp on 2016/11/14.
 */
public class Fragment2_new extends BaseListFragment {
    public ListView listView;
    private mem_DBServer db;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(cn.georgeyang.doublescroll.R.layout.layout_listview,null);
        //获取所有通讯录成员信息
        db=new mem_DBServer(this.getContext());
        List<mem>arr=new ArrayList<mem>();
        try {
            arr=db.get_all_mem();
        }
        catch (Exception e){

        }
        listView = (ListView) view.findViewById(cn.georgeyang.doublescroll.R.id.listView);
        String[] data = new String[101];
        for(int i=0;i<101;i++)
            data[i]=" ";
        if(arr.size()>0) {
            for (int i = 0; i< arr.size(); i++) {
                data[i*2] = arr.get(i).getName() + " √ " + arr.get(i).getPhone_type() + "：" + arr.get(i).getPhone_num();
                data[i*2+1]="成员编号："+arr.get(i).getMem_id()+"   是否设置为SOS联系人："+arr.get(i).getSet_or_not();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public View getSlidableView() {
        return listView;
    }
}
