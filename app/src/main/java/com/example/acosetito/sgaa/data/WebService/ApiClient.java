package com.example.acosetito.sgaa.data.WebService;
import com.example.acosetito.sgaa.data.Utilitario.DateDeserializer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String baseUrl = "http://172.16.32.15:8085/SGAA_WCF/";
    //public static final String baseUrl = "http://10.0.2.2/SGAA_WCF/";
    public static Retrofit retrofit;
    public static Gson gson;

    public static Retrofit getApiClient(String service)
    {
        if (retrofit == null)
        {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new DateDeserializer()) // New line for parse datatype date returned from Web service
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl + service + "/rest/")
                    //.addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))// create(gson))
                    .build();
        }else{
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl + service + "/rest/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
