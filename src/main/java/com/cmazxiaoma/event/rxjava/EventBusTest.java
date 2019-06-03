package com.cmazxiaoma.event.rxjava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.context.ContextLoader;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/5/21 12:57
 */
public class EventBusTest {

    public interface Publisher {

        void publishEvent(Event event);
    }


    public interface Event {

        Object getData();
    }

    public static abstract class AbstractEvent implements Event {
        private String name;

        public AbstractEvent(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class MsgEvent extends AbstractEvent {

        public MsgEvent(String name) {
            super(name);
        }

        @Override
        public Object getData() {
            System.out.println("Msg Event");
            return null;
        }
    }

    public static class LogEvent extends AbstractEvent {

        public LogEvent(String name) {
            super(name);
        }

        @Override
        public Object getData() {
            System.out.println("Log Event");
            return null;
        }
    }


    public static class MyPublisher implements Publisher {
        private EventBus eventBus;

        public MyPublisher(EventBus eventBus) {
            this.eventBus = eventBus;
        }

        @Override
        public void publishEvent(Event event) {
            eventBus.post(event);
        }
    }

    public interface Listener {

    }

    public interface EventListener<E extends Event> extends Listener {

        void onEvent(E event);
    }


    public static class MsgEventListener implements EventListener<MsgEvent> {

        @Override
        @Subscribe
        public void onEvent(MsgEvent event) {
            event.getData();
        }
    }

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();

        // 订阅事件
        eventBus.register(new MsgEventListener());

        // 发布事件
        Publisher myPublisher = new MyPublisher(eventBus);
        myPublisher.publishEvent(new MsgEvent("myEvent"));
        myPublisher.publishEvent(new LogEvent("logEvent"));

        // 获取实现接口的泛型
        MsgEventListener listener = new MsgEventListener();
        Type[] types = listener.getClass().getGenericInterfaces();
        ParameterizedType parameterized = (ParameterizedType) types[0];
        Class aClass = (Class) parameterized.getActualTypeArguments()[0];
        System.out.println(aClass);

        // 判断class1和class2是否相等
        // class1是不是class2的接口或者是父类
        System.out.println(MsgEvent.class.isAssignableFrom(aClass));
    }
}
