package com.ericlau.hkdconverter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends BaseActivity {
    private static EditText et_hkd,et_rmb,et_usd,et_eur,et_gbp,et_jpy;
    private static Button btn_convert,btn_clear,btn_update;
    private static TextView hkd_tv,rmb_tv,eur_tv,gbp_tv,jpy_tv,usd_tv;
    private final double HKD_RMB = 0.8452;
    private final double HKD_USD = 0.1282;
    private final double HKD_GBP = 0.0889;
    private final double HKD_EUR = 0.1174;
    private final double HKD_JPY = 15.3407;
    private static final String URL_ADDRESS = "http://api.fixer.io/latest?base=HKD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Converter");


        btn_convert = (Button)findViewById(R.id.btn_convert);
        btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_update = (Button)findViewById(R.id.btn_update);
        et_hkd = (EditText)findViewById(R.id.num_hkd);
        et_rmb = (EditText)findViewById(R.id.num_rmb);
        et_usd = (EditText)findViewById(R.id.num_usd);
        et_gbp = (EditText)findViewById(R.id.num_gbp);
        et_eur = (EditText)findViewById(R.id.num_eur);
        et_jpy = (EditText)findViewById(R.id.num_jpy);
        hkd_tv = (TextView)findViewById(R.id.hkd);
        rmb_tv = (TextView)findViewById(R.id.rmb);
        eur_tv = (TextView)findViewById(R.id.eur);
        usd_tv = (TextView)findViewById(R.id.usd);
        gbp_tv = (TextView)findViewById(R.id.gbp);
        jpy_tv = (TextView)findViewById(R.id.jpy);

        onClickListener();
        clearAllNumber();
        updateExchangeRate();
        setFont();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main_menu, menu);

        return true;
    }

    public void setFont() {
        Typeface my = Typeface.createFromAsset(getAssets(), "new_font2.TTF");
        hkd_tv.setTypeface(my);
        jpy_tv.setTypeface(my);
        usd_tv.setTypeface(my);
        gbp_tv.setTypeface(my);
        rmb_tv.setTypeface(my);
        eur_tv.setTypeface(my);
        btn_update.setTypeface(my);
        btn_clear.setTypeface(my);
        btn_convert.setTypeface(my);
        et_hkd.setTypeface(my);
        et_jpy.setTypeface(my);
        et_rmb.setTypeface(my);
        et_eur.setTypeface(my);
        et_usd.setTypeface(my);
        et_gbp.setTypeface(my);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.flanguage_id:
                    switchLanguage("en");
                    Intent it = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(it);
                    finish();
                    break;
                case R.id.slanguage_id:
                    switchLanguage("zh");
                    Intent itn = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(itn);
                    finish();
                    break;
                case R.id.tlanguage_id:
                    switchLanguage("hk");
                    Intent itt = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(itt);
                    finish();
                    break;
                case R.id.about_us_id:
                    Toast.makeText(MainActivity.this,R.string.author,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.contact_us_id:
                    Toast.makeText(MainActivity.this,R.string.tel,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.share_id:
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                    intent.putExtra(Intent.EXTRA_TEXT, "I would like to share this with you...");
                    startActivity(Intent.createChooser(intent, getTitle()));
                    break;
                default:
                    break;
            }
        return super.onOptionsItemSelected(item);
    }

    public void updateExchangeRate() {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncHttpClient client = new AsyncHttpClient();
                client.get(URL_ADDRESS, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        try {
                            JSONObject jsonObj = new JSONObject(responseString);
                            JSONObject ratesObj = jsonObj.getJSONObject("rates");
                            Double rmbrate = ratesObj.getDouble("CNY");
                            Double usdrate = ratesObj.getDouble("USD");
                            Double gbprate = ratesObj.getDouble("GBP");
                            Double eurrate = ratesObj.getDouble("EUR");
                            Double jpyrate = ratesObj.getDouble("JPY");
                            setValues(rmbrate,gbprate,eurrate,usdrate,jpyrate);
                        }catch(JSONException e) {
                            e.printStackTrace();
                        }catch(Exception e) {
                            Toast.makeText(MainActivity.this, "Invalid Data - Please enter HK dollars again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    /*public void callAuthor(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:59342135"));
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }*/

    public void setValues(double rmbrate,double gbprate,double eurrate,double usdrate,double jpyrate) {
        double hkd = Double.parseDouble(et_hkd.getText().toString());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String rmb = decimalFormat.format(hkd*rmbrate);
        String gbp = decimalFormat.format(hkd * gbprate);
        String eur = decimalFormat.format(hkd * eurrate);
        String usd = decimalFormat.format(hkd * usdrate);
        String jpy = decimalFormat.format(hkd*jpyrate);
        et_rmb.setText(rmb);
        et_usd.setText(usd);
        et_gbp.setText(gbp);
        et_eur.setText(eur);
        et_jpy.setText(jpy);
    }

    public void onClickListener() {
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setValues(HKD_RMB,HKD_GBP,HKD_EUR,HKD_USD,HKD_JPY);
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "Invalid Data - Please enter HK dollars again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void clearAllNumber() {
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_hkd.setText("");
                et_gbp.setText("");
                et_rmb.setText("");
                et_eur.setText("");
                et_jpy.setText("");
                et_usd.setText("");
            }
        });
    }


    /*private class HttpGetTask extends AsyncTask<Void,Void,String> {
        private static final String TAG = "HttpGetTask";
        private static final String URL_ADDRESS = "http://api.fixer.io/latest?base=HKD";

        @Override
        protected String doInBackground(Void... params) {
            String data = "";
            HttpURLConnection httpUrlConnection = null;
            try{
                httpUrlConnection = (HttpURLConnection) new URL(URL_ADDRESS).openConnection();
                InputStream in = new BufferedInputStream(httpUrlConnection.getInputStream());
                data = readStream(in);
            }catch(MalformedURLException exception) {
                Log.e(TAG,"MalformedURLException");
            }catch(IOException e) {
                Log.e(TAG,"IOException");
            }finally {
                if(null != httpUrlConnection) {
                    httpUrlConnection.disconnect();
                }
            }
            return data;
        }

        private String readStream(InputStream in) {
            BufferedReader br = null;
            StringBuffer data = new StringBuffer("");

            try{
                br = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while((line = br.readLine()) != null) {
                    data.append(line);
                }
            }catch(IOException e) {
                Log.e(TAG,"IOException");
            }finally {
                if(null != br) {
                    try {
                        br.close();
                    }catch (IOException e) {
                        Log.e(TAG,"IOException");
                    }
                }
            }
            return data.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            et_hkd.setText(s);
        }
    }*/
}


