package com.dchb.config;

import com.alibaba.fastjson.JSONObject;
import com.dchb.util.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class ParamsRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(ParamsRequestWrapper.class);

    private String allParamStr;//一次请求一个整体参数

    public String getAllParamStr() {
        return allParamStr;
    }

    ParamsRequestWrapper(HttpServletRequest request, boolean b) {
        super(request);
        String data = "";
        if (b) {
            try {
                // 获取 request的 payload（整个加密参）
                data = getBody(super.getInputStream());
            } catch (Exception e) {
                logger.info("获取request的 payload错误：" + e.getMessage());
            }
            if (super.getMethod().equals("GET")) {
                Map map = super.getParameterMap();
                Set keySet = map.entrySet();
                for (Iterator itr = keySet.iterator(); itr.hasNext(); ) {
                    Map.Entry me = (Map.Entry) itr.next();
                    Object ok = me.getKey();
                    Object ov = me.getValue();
                    String[] value = new String[1];
                    if (ov instanceof String[]) {
                        value = (String[]) ov;
                    } else {
                        value[0] = ov.toString();
                    }
                    for (int k = 0; k < value.length; k++) {
                        String content = ok + value[k];
                        data = content.replaceAll(" ", "+");
                        data = data.replaceAll("}", "=}");
                    }
                }
            }
            // 获取加密参数字符串
            int closeLen = data.length() - 1;
            int openLen = "{\"request\":".length();
            String substring = StringUtils.substring(data, openLen, closeLen);
            // 解密
            data = RSAUtils.decryptDataOnJava(substring);
        }
        this.allParamStr = data;
    }

    // 解密请求参数
    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (!StringUtils.isBlank(allParamStr)) {
            JSONObject jsStr = null;
            try {
                jsStr = JSONObject.parseObject(getAllParamStr());
            } catch (Exception e) {
                logger.info("参数json解析错误：" + e.getMessage());
            }
            // 匹配参数并返回
            for (String str : jsStr.keySet()) {
                if (name.equals(str)) {
                    return new String[]{jsStr.get(str).toString().trim()};
                }
            }
        }
        if (null == values) {
            return values;
        }
        for (int i = 0, len = values.length; i < len; i++) {
            String value = values[i].trim();
            values[i] = value;
        }
        return values;
    }

    //获取 request的 payload
    public String getBody(InputStream inputStream) throws IOException {
        String body;
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuffer.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        body = stringBuffer.toString();
        return body;
    }
}
