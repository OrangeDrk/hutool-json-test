package com.example.sxh;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

    public static void main(String[] args) throws JsonProcessingException {
        ResultWrapper wrapper = new ResultWrapper();
        wrapper.setSuccess(false).setCode("301").setMessage("测试数据");
        System.out.println();
        System.out.println("Hutool: "+JSONUtil.toJsonStr(wrapper));
        System.out.println("FastJson: "+JSON.toJSONString(wrapper));

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Jackson: "+mapper.writeValueAsString(wrapper));
    }

}
