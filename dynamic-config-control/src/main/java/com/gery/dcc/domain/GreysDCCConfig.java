package com.gery.dcc.domain;

import com.gery.dcc.domain.annotaion.DCCValue;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author grey
 */

@Component
@Data
public class GreysDCCConfig {

    // value里面加上前缀，方便后续定位到具体的field反射对象。
    @DCCValue("name:eris")
    private String name;

    @DCCValue("part:xixixi")
    private String part;
}
