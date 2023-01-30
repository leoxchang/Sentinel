package com.alibaba.csp.sentinel.dashboard.repository.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.nacos.api.config.ConfigService;
import org.apache.commons.lang.StringUtils;
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
@Component("systemRuleNacosProvider")
public class SystemRuleNacosProvider {

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<String, List<SystemRuleEntity>> converter;

    public List<SystemRuleEntity> getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + NacosConfigUtil.SYSTEM_RULE_POSTFIX,
                NacosConfigUtil.GROUP_ID, 3000);
        if (StringUtils.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return converter.convert(rules);
    }
}
