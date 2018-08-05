package com.example.acosetito.sgaa.data.Utilitario;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateDeserializer implements JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {

        try {
            String date = element.getAsString();
            date = date.substring(6,date.indexOf('-'));
            Date fecha = new Date(Long.parseLong(date));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            return fecha;
        }catch (Exception e){
            return  null;
        }

        /**try {
         return formatter.parse(date); //Original
         } catch (ParseException e) {
         //System.err.println("Failed to parse Date due to:", e);
         Log.e("Failed date", e.getMessage());
         return null;
         }**/
    }
}
