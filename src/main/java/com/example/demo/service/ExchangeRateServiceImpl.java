package com.example.demo.service;

import com.example.demo.dao.ExchangeRateRepository;
import com.example.demo.entity.ExchangeRate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

@Service
public class ExchangeRateServiceImpl implements TempoService<ExchangeRate> {

    private static final String REQUEST_URL_START = "https://api.twelvedata.com/exchange_rate?symbol=USD/";
    private static final String REQUEST_URL_END = "&apikey=0169f4d282b24bdf9fd94858ee14d583";

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Override
    @Transactional
    public ExchangeRate save(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }

    public List<ExchangeRate> findAll() {
        return exchangeRateRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        exchangeRateRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        exchangeRateRepository.deleteAll();
    }

    //@Transactional
    public ExchangeRate findBySymbol(String symbol) {
        ExchangeRate exchangeRate = exchangeRateRepository.findBySymbol(symbol);

        Timestamp timestamp = exchangeRate.getTimestamp();
        Date date = new Date(timestamp.getTime());
        Date currentDate = new Date(System.currentTimeMillis());
        if (!currentDate.equals(date)) {
            exchangeRate = update(symbol);
        }
        return exchangeRate;
    }

    @Transactional
    public ExchangeRate update(String symbol) {
        ExchangeRate exchangeRate = getNewExchangeRate(symbol);
        return exchangeRateRepository.save(exchangeRate);
    }

    private ExchangeRate getNewExchangeRate(String symbol) {
        JSONArray jsonArray = connect(symbol);
        float rate = Float.parseFloat(jsonArray.get(1).toString());
        Timestamp timestamp = new Timestamp(Long.parseLong(jsonArray.get(2).toString()));
        return new ExchangeRate(jsonArray.get(0).toString(), rate, timestamp);
    }

    private JSONArray connect(String symbol) {
        JSONParser parser = new JSONParser();
        StringBuffer responseData = new StringBuffer();
        try {
            URL requestURL = new URL(REQUEST_URL_START + symbol + REQUEST_URL_END);
            HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "twelve_java/1.0");
            connection.connect();

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Request failed. Error: " + connection.getResponseMessage());
            }

            Scanner scanner = new Scanner(requestURL.openStream());
            while (scanner.hasNextLine()) {
                responseData.append(scanner.nextLine());
            }

            JSONObject json = (JSONObject) parser.parse(responseData.toString());
            JSONObject meta = (JSONObject) json.get("meta");
            JSONArray values = (JSONArray) json.get("values");

            connection.disconnect();
            return values;
        } catch (ParseException e) {
            throw new RuntimeException("json parser is wrong", e);
        } catch (IOException e) {
            throw new RuntimeException("connection error", e);
        }

    }
}
