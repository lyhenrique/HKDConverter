package com.ericlau.hkdconverter;

/**
 * Created by Eric.Lau on 16/2/2016.
 */

/*public class ShowUpdate extends ListActivity {
    private static Button btn_update;
    private static EditText et_hkd,et_rmb,et_usd,et_eur,et_gbp,et_jpy;
    private static final String URL_ADDRESS = "http://api.fixer.io/latest?base=HKD";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new HttpGetTask().execute();
        btn_update = (Button)findViewById(R.id.btn_update);
        et_hkd = (EditText)findViewById(R.id.num_hkd);
        et_rmb = (EditText)findViewById(R.id.num_rmb);
        et_usd = (EditText)findViewById(R.id.num_usd);
        et_gbp = (EditText)findViewById(R.id.num_gbp);
        et_eur = (EditText)findViewById(R.id.num_eur);
        et_jpy = (EditText)findViewById(R.id.num_jpy);
    }

    private class HttpGetTask extends AsyncTask<Void, Void, List<String>> {

        AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

        @Override
        protected List<String> doInBackground(Void... params) {
            HttpGet request = new HttpGet(URL_ADDRESS);
            JSONResponseHandler responseHandler = new JSONResponseHandler();
            try {
                mClient.execute(request, responseHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    private class JSONResponseHandler implements ResponseHandler<List<String>> {

        @Override
        public List<String> handleResponse(HttpResponse response)
                throws IOException {
            List<String> result = new ArrayList<String>();
            String JSONResponse = new BasicResponseHandler()
                    .handleResponse(response);

            try {
                JSONObject responseObj = (JSONObject) new JSONTokener(JSONResponse).nextValue();
                JSONObject ratesObj = responseObj.getJSONObject("rates");
                final Double rmb = ratesObj.getDouble("CNY");
                final Double usd = ratesObj.getDouble("USD");
                final Double gbp = ratesObj.getDouble("GBP");
                final Double eur = ratesObj.getDouble("EUR");
                final Double jpy = ratesObj.getDouble("JPY");
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Double hkd = Double.valueOf(et_hkd.getText().toString());
                        String rmbrate = String.valueOf(hkd * rmb);
                        String gbprate = String.valueOf(hkd * gbp);
                        String eurrate = String.valueOf(hkd * eur);
                        String usdrate = String.valueOf(hkd * usd);
                        String jpyrate = String.valueOf(hkd * jpy);
                        et_rmb.setText(rmbrate);
                        et_usd.setText(usdrate);
                        et_gbp.setText(gbprate);
                        et_eur.setText(eurrate);
                        et_jpy.setText(jpyrate);
                    }
                });
            }catch(JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
*/
