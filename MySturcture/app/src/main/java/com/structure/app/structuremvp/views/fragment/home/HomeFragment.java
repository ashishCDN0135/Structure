package com.structure.app.structuremvp.views.fragment.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.structure.app.structuremvp.R;
import com.structure.app.structuremvp.application.MyApp;
import com.structure.app.structuremvp.databinding.HomeFragmentLayoutBinding;
import com.structure.app.structuremvp.db.DbHalper;
import com.structure.app.structuremvp.interfaces.DialogClickListener;
import com.structure.app.structuremvp.model.bean.DatabaseModelBean;
import com.structure.app.structuremvp.model.bean.DatabaseModelBeanDao;
import com.structure.app.structuremvp.model.bean.JoinBean;
import com.structure.app.structuremvp.model.bean.JoinBeanDao;
import com.structure.app.structuremvp.model.bean.OrmBeanList;
import com.structure.app.structuremvp.utils.AppUtils;
import com.structure.app.structuremvp.utils.Utils;
import com.structure.app.structuremvp.views.base.BaseFragment;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements DialogClickListener {

    private Context context;
    private HomeFragmentLayoutBinding binding;
    HomeFragmentViewModel viewModel;
    ArrayList<DatabaseModelBean> databaseModelBeans;
    ArrayList<DatabaseModelBean> databaseModelBeansFromDb;
    ArrayList<JoinBean> joinBeans,joinBeanArrayList;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment_layout, container, false);
        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        initializeViews(binding.getRoot());
        //testDatabaseQuery();
        //testOrmLite();
        return binding.getRoot();
    }

    /*
    *
    * Implemenation for OrmLite
    *
    * */

   /* public void testOrmLite(){
        ArrayList<OrmBeanList>ormBeanLists=new ArrayList<>();
        ormBeanLists.add(new OrmBeanList("101","bhavesh",
                "997789","indore","mp"));
        ormBeanLists.add(new OrmBeanList("102","vikram",
                "997789","gwalior","mp"));
        ormBeanLists.add(new OrmBeanList("103","naman",
                "997789","luknaow","up"));

        DbHalper.getInstance(context).insertCategoryDataList(ormBeanLists);

        ArrayList<OrmBeanList>ormBeanListsFromDB=new ArrayList<>();

        ormBeanListsFromDB=DbHalper.getInstance(context).getCategoryListData();

        for (int i=0;i<ormBeanListsFromDB.size();i++){
            Log.e("data",""+ormBeanListsFromDB.get(i).getName()+
                    " "+ormBeanListsFromDB.get(i).getContactNo()+" "+
                    ormBeanListsFromDB.get(i).getCity());
        }
    }*/


   /*
   *
   *
   * implementation for GreenDao
   *
   * */

/*
    public void testDatabaseQuery(){
        databaseModelBeans=new ArrayList<>();
        joinBeans=new ArrayList<>();
        databaseModelBeans.add(new DatabaseModelBean("567","text1","ashu"));
        */
/*databaseModelBeans.add(new DatabaseModelBean("454","text2","shiv"));
        databaseModelBeans.add(new DatabaseModelBean("675","text3","ankur"));
        databaseModelBeans.add(new DatabaseModelBean("345","text4","akash"));*//*




        joinBeans.add(new JoinBean("567","ashu description listed here"));
        joinBeans.add(new JoinBean("454","shiv description listed here"));
        joinBeans.add(new JoinBean("675","ankur description listed here"));

        MyApp.getInstance().getDaoSession().getDatabaseModelBeanDao().deleteAll();
        MyApp.getInstance().getDaoSession().getJoinBeanDao().deleteAll();

        MyApp.getInstance().getDaoSession().getDatabaseModelBeanDao().insertOrReplaceInTx(databaseModelBeans);
        MyApp.getInstance().getDaoSession().getJoinBeanDao().insertOrReplaceInTx(joinBeans);


        databaseModelBeansFromDb=new ArrayList<>();

        databaseModelBeansFromDb=(ArrayList<DatabaseModelBean>)MyApp.getInstance().getDaoSession().getDatabaseModelBeanDao().queryBuilder().list();
        for (int i=0;i<databaseModelBeansFromDb.size();i++){
            Log.e("data",""+databaseModelBeansFromDb.get(i).getComment()+" "+
                    databaseModelBeansFromDb.get(i).getText());
        }

        joinBeanArrayList=new ArrayList<>();
        joinBeanArrayList=(ArrayList<JoinBean>)MyApp.getInstance().getDaoSession().getJoinBeanDao().queryBuilder().list();
        for (int i=0;i<joinBeanArrayList.size();i++){
            Log.e("joinBeanArrayList",""+joinBeanArrayList.get(i).getDescription());
        }

        JoinBean modelBean1 = MyApp.getInstance().getDaoSession().getJoinBeanDao().queryBuilder()
                .where(JoinBeanDao.Properties.Id.eq("675")).unique();
        MyApp.getInstance().getDaoSession().getJoinBeanDao().delete(modelBean1);

        QueryBuilder<DatabaseModelBean> queryBuilder = MyApp.getInstance().getDaoSession().getDatabaseModelBeanDao().queryBuilder();
        queryBuilder.join(JoinBean.class, JoinBeanDao.Properties.Id)
                .where(JoinBeanDao.Properties.Id.eq("675"));
        List<DatabaseModelBean> users = queryBuilder.list();

        QueryBuilder<DatabaseModelBean> queryBuilder = MyApp.getInstance().getDaoSession().getDatabaseModelBeanDao().queryBuilder();
        queryBuilder.join(DatabaseModelBeanDao.Properties.Id, JoinBean.class, JoinBeanDao.Properties.Id);
        List<DatabaseModelBean> users = queryBuilder.list();

        for (int i=0;i<users.size();i++){
            Log.e("join",""+users.get(i).getComment()+" "+
                    users.get(i).getText()+" "+
                    users.get(i).getJoinBean().getDescription());
        }
        DatabaseModelBean modelBean1 = MyApp.getInstance().getDaoSession().getDatabaseModelBeanDao().queryBuilder()
                .where(DatabaseModelBeanDao.Properties.Id.eq("345")).unique();
        modelBean1.setComment("Vijay Vishw Trianga");
        MyApp.getInstance().getDaoSession().getDatabaseModelBeanDao().update(modelBean1);

        DatabaseModelBean modelBean2 = MyApp.getInstance().getDaoSession().getDatabaseModelBeanDao().queryBuilder()
                .where(DatabaseModelBeanDao.Properties.Id.eq("345")).unique();
        Log.d("update", "" + modelBean2.getComment());

        databaseModelBeansFromDb=(ArrayList<DatabaseModelBean>)MyApp.getInstance().getDaoSession().getDatabaseModelBeanDao().queryBuilder().list();
        for (int i=0;i<databaseModelBeansFromDb.size();i++){
            Log.e("dataAfterUpdate",""+databaseModelBeansFromDb.get(i).getComment()+" "+
                    databaseModelBeansFromDb.get(i).getText());
        }
    }
*/

    @Override
    public void initializeViews(View rootView) {
        context = getActivity();
        binding.assetRl.setOnClickListener(this);
        binding.scanRl.setOnClickListener(this);
        binding.historyRl.setOnClickListener(this);
        binding.handOverRl.setOnClickListener(this);
        binding.takeoutRl.setOnClickListener(this);
        binding.chatRl.setOnClickListener(this);
//        viewModel.validator.observe(this, observer);
//        viewModel.respose_validator.observe(this, response_observer);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.asset_rl:
              //  startActivity(new Intent(context, AssetListActivity.class));
                break;

            case R.id.scan_rl:
                if (Utils.checkPermissions(getActivity(), AppUtils.STORAGE_CAMERA_PERMISSIONS)) {
                 //   startActivityForResult(new Intent(context, QrCodeActivity.class), AppUtils.REQUEST_CODE_QR_SCAN);
                } else {
                    requestPermissions(AppUtils.STORAGE_CAMERA_PERMISSIONS, AppUtils.REQUEST_CODE_CAMERA);
                }
                break;

            case R.id.history_rl:
                break;

            case R.id.hand_over_rl:
                break;

            case R.id.takeout_rl:
                break;

            case R.id.chat_rl:
                break;
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AppUtils.REQUEST_CODE_CAMERA || Utils.onRequestPermissionsResult(permissions, grantResults)) {
           // startActivityForResult(new Intent(context, QrCodeActivity.class), AppUtils.REQUEST_CODE_QR_SCAN);
        } else {
            Utils.showToast(context, getString(R.string.allow_camera_permission));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }


    @Override
    public void onDialogClick(int which, int requestCode) {

    }
}