package com.fib.springbootjavers.configuration;

import com.fib.springbootjavers.domain.Employee;
import org.javers.spring.auditable.CommitPropertiesProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JaversConfiguration {

    @Bean
    public CommitPropertiesProvider commitPropertiesProvider(){
        return new CommitPropertiesProvider() {
            @Override
            public Map<String, String> provideForCommittedObject(Object domainObject) {
                if(domainObject instanceof Employee){
                    Map map = new HashMap();
                    map.put("id", String.valueOf(((Employee)domainObject).getId()));
                    map.put("name", ((Employee)domainObject).getFirstName());
                    map.put("sex", String.valueOf(((Employee)domainObject).getSex()));
                    return map;
                }
                return Collections.emptyMap();
            }
        };
    }
}
