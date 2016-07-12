package git.dzc.okhttputils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import git.dzc.okhttputilslib.CacheType;
import git.dzc.okhttputilslib.Callback;
import git.dzc.okhttputilslib.JsonCallback;
import git.dzc.okhttputilslib.OKHttpUtils;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.this.getClass().getSimpleName();
    private Button tv1;
    private Button tv2;
    private Button tv3;
    private Button tv4;
    TextView result;
    private OKHttpUtils okHttpUtils;
    String url = "http://218.17.158.113:13001/servlet/json?funcNo=407222&match_id=2&user_id=69";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okHttpUtils = new OKHttpUtils.Builder(this).build();
        result = (TextView) findViewById(R.id.result);
        tv1 = (Button) findViewById(R.id.tv1);
        tv2 = (Button) findViewById(R.id.tv2);
        tv3 = (Button) findViewById(R.id.tv3);
        tv4 = (Button) findViewById(R.id.tv4);



/*
* 对OkHttp进行封装，实现了只查询缓存，网络请求失败自动查询本地缓存等功能,结果用Gson解析 支持文件上传进度回调
支持gzip,可通过gzip(isOpen)来开启或移除，也可通过在Builder自定义的时候开启(由于okhttp默认开启了gzip,
所以此选项是对发送到服务器的数据进行gzip,如果服务器不支持,请勿开启) 支持4种不同的查询方式

*ONLY_NETWORK 只查询网络数据

*ONLY_CACHED 只查询本地缓存

*CACHED_ELSE_NETWORK 先查询本地缓存，如果本地没有，再查询网络数据

*NETWORK_ELSE_CACHED 先查询网络数据，如果没有，再查询本地缓存*/
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(CacheType.ONLY_NETWORK);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(CacheType.ONLY_CACHED);

            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(CacheType.NETWORK_ELSE_CACHED);
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(CacheType.CACHED_ELSE_NETWORK);
            }
        });


    }


    private void getData(@CacheType int cacheType) {
        Request request = new Request.Builder().url(url).build();
        okHttpUtils.request(request, cacheType, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("WY", "errr：" + call);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful())
                    try {
                        final String message = response.body().string();
                        Log.i("WY", "打印GET响应的数据：" + message);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                result.setText(message);
                            }
                        });
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

            }
        });
    }



}
