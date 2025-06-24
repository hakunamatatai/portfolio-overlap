package com.example.portfolio.repository;

import com.example.portfolio.exception.DataLoadException;
import com.example.portfolio.model.Fund;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description: Fund Repository: parse available funds
 * @Date 17/06/2025
 * @author evie
 */
public class FundRepository {

    private Map<String/*fund name*/, Fund> fundMap = new HashMap<>();

    public FundRepository(URL resourceUrl) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = resourceUrl.openStream()) {
            JsonNode root = mapper.readTree(is);
            loadFundsNode(root);
        } catch (IOException e) {
            throw new DataLoadException("Can not load Json data: " + resourceUrl, e);
        }
    }

    private void loadFundsNode(JsonNode root) throws IOException {
        JsonNode fundsNode = root.get("funds");
        if (fundsNode == null || !fundsNode.isArray()) {
            throw new DataLoadException("Invalid 'funds' content, Json type incorrect", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        for (JsonNode fundNode : fundsNode) {
            try {
                String name = fundNode.get("name").asText();
                List<String> stocks = mapper.convertValue(
                        fundNode.get("stocks"), new TypeReference<List<String>>() {
                        });
                Set<String> stockSet = stocks.stream()
                        .filter(Objects::nonNull)
                        .map(String::trim)
                        .collect(Collectors.toSet());
                fundMap.put(name, new Fund(name, stockSet));
            } catch (Exception e) {
                throw new DataLoadException("Parse funds and stocks fail!, fund =" + fundNode, e);
            }
        }
    }


    public Fund getFundByName(String name) {
        return fundMap.get(name);
    }

    public boolean checkIfFundExist(String fundName) {
        return fundMap.containsKey(fundName);
    }

    public Set<String> getAllFundNames() {
        return new HashSet<>(fundMap.keySet());
    }

    /**
     * Add stock to existing fund
     */
    public boolean addStockToFund(String fundName, String stockName) {
        Fund fund = fundMap.get(fundName);
        if (Objects.nonNull(fund)) {
            fund.addStock(stockName);
            return true;
        }
        return false;
    }
}
