package com.bezkoder.spring.jpa.postgresql.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class Help {

    private final JdbcTemplate jdbcTemplate;

    public Help(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void map(Map<String, Object> urlParameters){
        urlParameters.put("SSS", "SAN");
        urlParameters.put("LocID", 234243);
        urlParameters.put("APIUser", "Apex");
        urlParameters.put("APIKey", "apiKey");
        urlParameters.put("Options", "0001");
        urlParameters.put("referenceNumber","N-3342-ere-we");
        urlParameters.put("AuthKey", "funder-23");
        urlParameters.put("FrontEndTrace", "uniqueReferenceIdentifier");
        urlParameters.put("TokenID", "");
        urlParameters.put("CustomerID", "");
        urlParameters.put("OriginatorName", "originatorName");
        urlParameters.put("CustTransType", "D");
        urlParameters.put("CustomerName", "customerName");
        urlParameters.put("CustomerRoutingNo", "234233");
        urlParameters.put("CustomerAcctNo", "2342424");
        urlParameters.put("CustomerAcctType", "C");
        urlParameters.put("TransactionCode", "CCD");
        urlParameters.put("TransAmount", "234");
        urlParameters.put("CheckOrTransDate", new Date());
        urlParameters.put("EffectiveDate", new Date());
        urlParameters.put("CheckNo", "externalMemo");
        urlParameters.put("Memo", "memo");
        urlParameters.put("AcctSet", "");
        urlParameters.put("type", "achworks");

    }
     public List<Map<String,Object>> getAllTable(){
        String sql ="SELECT json_build_object(" +
                " 'schema_name', table_schema," +
                "    'table_name', table_name," +
                "    'column_name', column_name," +
                "    'data_type', data_type" +
                "   ) AS json_obj" +
                "    FROM information_schema.columns" +
                "    WHERE table_schema = 'public'";
        return jdbcTemplate.queryForList(sql);
     }
}
