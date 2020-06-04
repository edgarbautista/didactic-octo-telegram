package com.edgar.bautista.samplecode.listeners;

import com.edgar.bautista.samplecode.models.CustomJsonNodeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppEventListener implements ApplicationListener<CustomJsonNodeEvent> {
    @Override
    public void onApplicationEvent(CustomJsonNodeEvent customJsonNodeEvent) {
        System.out.println(customJsonNodeEvent.getNode());
    }
}
