package com.lambdaschool.backend;

import com.lambdaschool.backend.models.*;
import com.lambdaschool.backend.services.IncidentService;
import com.lambdaschool.backend.services.RoleService;
import com.lambdaschool.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    IncidentService incidentService;

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "ILuvM4th!", "admin@lambdaschool.local", admins);
        u1.getUseremails()
          .add(new Useremail(u1, "admin@email.local"));
        u1.getUseremails()
          .add(new Useremail(u1, "admin@mymail.local"));
        u1 = userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", "cinnamon@lambdaschool.local", datas);
        u2.getUseremails()
          .add(new Useremail(u2, "cinnamon@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2, "hops@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2, "bunny@email.local"));
        u2 = userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r1));
        User u3 = new User("testbarn", "ILuvM4th!", "testbarn@school.lambda", users);
        u3.getUseremails()
          .add(new Useremail(u3, "barnbarn@email.local"));
        u3 = userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("testcat", "password", "testcat@school.lambda", users);
        u4 = userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("testdog", "password", "testdog@school.lambda", users);
        u5 = userService.save(u5);

        System.out.println("\n*** Seed Data ***");
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
        System.out.println(u4);
        System.out.println(u5);
        System.out.println("*** Seed Data ***\n");


//        Incident Seed Data //
        ArrayList<Incident> incidents = new ArrayList<>();

        Incident i1 = new Incident("(30.3427, -187.3258)", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13, 14, 15 , 16,
                                   17, 18, 19, 20, 21, 22, 23, 33.3427, 66.3427);
        incidentService.saveIncident(i1);


        Incident i2 = new Incident("(54.3427, -1837.3258)", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , 0, 0, 0, 0, 0 , 0,
                                   0, 0, 0, 0, 0, 0, 0, 33.3427, 66.3427);
        incidentService.saveIncident(i2);

        Incident i3 = new Incident("(2.3427, -2.3258)", 1, 0, 1, 1, 1, 0, 0, 0, 1, 1 , 0, 0, 0, 0, 0 , 0,
                                   0, 0, 0, 0, 0, 0, 0, 33.3427, 66.3427);
        incidentService.saveIncident(i3);

        Incident i4 = new Incident("(4.3427, -4.3258)", 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 , 20, 0, 1, 0, 0 , 0,
                                   0, 0, 0, 0, 1, 0, 0, 33.3427, 66.3427);
        incidentService.saveIncident(i4);

        System.out.println("*** INCIDENT SEED DATA ****");
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        System.out.println("*** INCIDENT SEED DATA ***");
    }
}