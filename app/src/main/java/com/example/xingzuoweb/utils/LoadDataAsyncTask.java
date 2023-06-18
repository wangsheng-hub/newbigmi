package com.example.xingzuoweb.utils;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
public class LoadDataAsyncTask extends AsyncTask<String,Void,String> {
    Context context;
    onGetNetDataListener listener;
    ProgressDialog dialog;
    boolean isShowDialog = false;
    private  void  initDialog(){
        dialog = new ProgressDialog(context);
        dialog.setTitle("提示信息");
        dialog.setMessage("正在加载中...");
    }

    public LoadDataAsyncTask(Context context, onGetNetDataListener listener,boolean isShowDialog) {
        this.context = context;
        this.listener = listener;
        this.isShowDialog = isShowDialog;
        initDialog();
    }

    public interface onGetNetDataListener{
        public void onSuccess(String json);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (isShowDialog){
            dialog.show();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String json = HttpUtils.getJSONFromNet(params[0]);
        return json;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (isShowDialog){
            dialog.dismiss();
        }
        listener.onSuccess(s);
    }
}
