package com.alibaba.csp.sentinel.dashboard.repository.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * api definition nacos publisher
 *
 * @author zhangxinlei
 * @date 2021-04-29
 */
@Component("systemRuleNacosPublisher")
public class SystemRuleNacosPublisher {
    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<List<SystemRuleEntity>, String> converter;

    public void publish(String app, List<SystemRuleEntity> apis) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot empty");
        if (apis == null) {
            return;
        }
        configService.publishConfig(app + NacosConfigUtil.SYSTEM_RULE_POSTFIX, NacosConfigUtil.GROUP_ID,
                converter.convert(apis));
    }
}
