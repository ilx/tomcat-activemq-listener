package net.ilx.tomcat.listener.activemq;

import java.util.Map;
import java.util.Set;

import org.apache.activemq.broker.BrokerRegistry;
import org.apache.activemq.broker.BrokerService;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

public class ActiveMQLifecycleListener implements LifecycleListener {

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        if (event.getType().equals(Lifecycle.AFTER_STOP_EVENT)) {
            BrokerRegistry registry = BrokerRegistry.getInstance();
            Map<String, BrokerService> brokerMap = registry.getBrokers();
            Set<String> brokers = brokerMap.keySet();
            for (String broker : brokers) {
                BrokerService brokerService = brokerMap.get(broker);
                try {
                    brokerService.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
