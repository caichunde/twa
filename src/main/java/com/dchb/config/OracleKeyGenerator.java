package com.dchb.config;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;

public class OracleKeyGenerator implements IKeyGenerator {
    @Override
    public String executeSql(String incrementerName) {
        StringBuilder sql = new StringBuilder();
        sql.append("select (to_char(sysdate,'yyyymmdd')||to_char( ").append(incrementerName);
        sql.append(".nextval )) from dual ");
        return sql.toString();
    }
}
