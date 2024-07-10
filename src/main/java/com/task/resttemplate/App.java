package com.task.resttemplate;

import com.task.resttemplate.config.MyConfig;
import com.task.resttemplate.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = applicationContext.getBean(Communication.class);
        List<User> users = communication.getUsers();
        System.out.println(users);
        communication.createUser(new User(3L,"James", "Brown", (byte) 23));
        communication.updateUser(new User(3L,"Thomas", "Shelby", (byte) 23));
        communication.deleteUser(3L);
    }
}
