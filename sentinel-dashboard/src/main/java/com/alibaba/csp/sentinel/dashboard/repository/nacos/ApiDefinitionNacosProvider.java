package com.alibaba.csp.sentinel.dashboard.repository.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.nacos.api.config.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * api definition nacos privider
 *
 * @author zhangxinlei
 * @date 2021-04-29
 */
@Component("apiDefiniationNacosProvider")
public class ApiDefinitionNacosProvider {

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<String, List<ApiDefinitionEntity>> converter;

    public List<ApiDefinitionEntity> getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + NacosConfigUtil.API_DEFINITION_POSTFIX,
                NacosConfigUtil.GROUP_ID, 3000);
        if (StringUtils.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return converter.convert(rules);
    }
}
