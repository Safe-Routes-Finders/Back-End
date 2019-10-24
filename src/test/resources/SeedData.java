//package com.lambdaschool.backend;
//
//import com.github.javafaker.Faker;
//import com.github.javafaker.service.FakeValuesService;
//import com.github.javafaker.service.RandomService;
//import com.lambdaschool.backend.models.*;
//import com.lambdaschool.backend.services.IncidentService;
//import com.lambdaschool.backend.services.RoleService;
//import com.lambdaschool.backend.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Locale;
//
////@Transactional
////@Component
//public class SeedData implements CommandLineRunner
//{
//    @Autowired
//    RoleService roleService;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    IncidentService incidentService;
//
//
//
//    @Override
//    public void run(String[] args) throws Exception
//    {
//        Role r1 = new Role("admin");
//        Role r2 = new Role("user");
//        Role r3 = new Role("data");
//
//        roleService.save(r1);
//        roleService.save(r2);
//        roleService.save(r3);
//
//        // admin, data, user
//        ArrayList<UserRoles> admins = new ArrayList<>();
//        admins.add(new UserRoles(new User(),
//                                 r1));
//        admins.add(new UserRoles(new User(),
//                                 r2));
//        admins.add(new UserRoles(new User(),
//                                 r3));
//        User u1 = new User("admin",
//                           "password",
//                           "admin@lambdaschool.local",
//                           admins);
//        u1.getUseremails()
//          .add(new Useremail(u1,
//                             "admin@email.local"));
//        u1.getUseremails()
//          .add(new Useremail(u1,
//                             "admin@mymail.local"));
//
//        userService.save(u1);
//
//        // data, user
//        ArrayList<UserRoles> datas = new ArrayList<>();
//        datas.add(new UserRoles(new User(),
//                                r3));
//        datas.add(new UserRoles(new User(),
//                                r2));
//        User u2 = new User("cinnamon",
//                           "1234567",
//                           "cinnamon@lambdaschool.local",
//                           datas);
//        u2.getUseremails()
//          .add(new Useremail(u2,
//                             "cinnamon@mymail.local"));
//        u2.getUseremails()
//          .add(new Useremail(u2,
//                             "hops@mymail.local"));
//        u2.getUseremails()
//          .add(new Useremail(u2,
//                             "bunny@email.local"));
//        userService.save(u2);
//
//        // user
//        ArrayList<UserRoles> users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                                r2));
//        User u3 = new User("barnbarn",
//                           "ILuvM4th!",
//                           "barnbarn@lambdaschool.local",
//                           users);
//        u3.getUseremails()
//          .add(new Useremail(u3,
//                             "barnbarn@email.local"));
//        userService.save(u3);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                                r2));
//        User u4 = new User("puttat",
//                           "password",
//                           "puttat@school.lambda",
//                           users);
//        userService.save(u4);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                                r2));
//        User u5 = new User("misskitty",
//                           "password",
//                           "misskitty@school.lambda",
//                           users);
//        userService.save(u5);
//
//
////    incident log
//        ArrayList<Incident> incidents = new ArrayList<>();
//
//        Incident i1 = new Incident("(30.3427, -187.3258)", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13, 14, 15 , 16,
//                                   17, 18, 19, 20, 21, 22, 23, 33.3427, 66.3427);
//        incidentService.saveIncident(i1);
//
//
//        Incident i2 = new Incident("(54.3427, -1837.3258)", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , 0, 0, 0, 0, 0 , 0,
//                                   0, 0, 0, 0, 0, 0, 0, 33.3427, 66.3427);
//        incidentService.saveIncident(i2);
//
//        Incident i3 = new Incident("(2.3427, -2.3258)", 1, 0, 1, 1, 1, 0, 0, 0, 1, 1 , 0, 0, 0, 0, 0 , 0,
//                                   0, 0, 0, 0, 0, 0, 0, 33.3427, 66.3427);
//        incidentService.saveIncident(i3);
//
//        Incident i4 = new Incident("(4.3427, -4.3258)", 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 , 20, 0, 1, 0, 0 , 0,
//                                   0, 0, 0, 0, 1, 0, 0, 33.3427, 66.3427);
//        incidentService.saveIncident(i4);
//
//
//        // using JavaFaker create a bunch of regular users
//        // https://www.baeldung.com/java-faker
//        // https://www.baeldung.com/regular-expressions-java
//
//        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
//                                                                    new RandomService());
//        Faker nameFaker = new Faker(new Locale("en-US"));
//
//        for (int i = 0; i < 100; i++)
//        {
//            new User();
//            User fakeUser;
//
//            users = new ArrayList<>();
//            users.add(new UserRoles(new User(),
//                                    r2));
//            fakeUser = new User(nameFaker.name()
//                                         .username(),
//                                "password",
//                                nameFaker.internet()
//                                         .emailAddress(),
//                                users);
//            fakeUser.getUseremails()
//                    .add(new Useremail(fakeUser,
//                                       fakeValuesService.bothify("????##@gmail.com")));
//            userService.save(fakeUser);
//        }
//    }
//
//
//
//}