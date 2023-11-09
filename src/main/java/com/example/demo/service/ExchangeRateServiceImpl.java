package com.example.demo.service;

import com.example.demo.dao.ExchangeRateRepository;
import com.example.demo.entity.ExchangeRate;
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
public class ExchangeRateServiceImpl  {

    private static final String REQUEST_URL_START = "https://api.twelvedata.com/exchange_rate?symbol=USD/";
    private static final String REQUEST_URL_END = "&apikey=0169f4d282b24bdf9fd94858ee14d583";

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Transactional
    public ExchangeRate save(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }

    public List<ExchangeRate> findAll() {
        return exchangeRateRepository.findAll();
    }

    @Transactional
    public void deleteById(Integer id) {
        exchangeRateRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        exchangeRateRepository.deleteAll();
    }

    //@Transactional
    public ExchangeRate findBySymbol(String symbol) {
        ExchangeRate exchangeRate = exchangeRateRepository.findBySymbol("USD/"+symbol);

        Timestamp timestamp = exchangeRate.getTimestamp();
        Date date = new Date(timestamp.getTime());
        Date currentDate = new Date(System.currentTimeMillis());
        if (!currentDate.equals(date)) {
            exchangeRate = update(symbol, exchangeRate.getId());
        }
        return exchangeRate;
    }

    @Transactional
    public ExchangeRate update(String symbol,int id) {
        ExchangeRate exchangeRate = getNewExchangeRate(symbol);
        exchangeRate.setId(id);
        return exchangeRateRepository.save(exchangeRate);
    }

    private ExchangeRate getNewExchangeRate(String symbol) {
        JSONObject json = connect(symbol);

        float rate = Float.parseFloat(json.get("rate").toString());
        Timestamp timestamp = new Timestamp(Long.parseLong(json.get("timestamp").toString()));
        return new ExchangeRate(json.get("symbol").toString(), rate, timestamp);
    }

    private JSONObject connect(String symbol) {
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

            connection.disconnect();
            return json;
        } catch (ParseException e) {
            throw new RuntimeException("json parser is wrong", e);
        } catch (IOException e) {
            throw new RuntimeException("connection error", e);
        }

    }
}
