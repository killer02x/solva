package solvademo.solva.apiserv;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwelveDataResponse {

    private Map<String, CurrencyData> currencyDataMap = new HashMap<>();

    @JsonAnySetter
    public void setCurrencyData(String key, CurrencyData value) {
        currencyDataMap.put(key, value);
    }

    public CurrencyData getCurrencyData(String currency) {
        return currencyDataMap.get(currency);
    }

    public static class CurrencyData {
        private Integer code;
        private String message;
        private List<Value> values;


        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Value> getValues() {
            return values;
        }

        public void setValues(List<Value> values) {
            this.values = values;
        }
    }

    public static class Value {
        private String datetime;
        private String close;


        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getClose() {
            return close;
        }

        public void setClose(String close) {
            this.close = close;
        }
    }
}
