package storage;

import models.User;
import java.util.List;

public class Users {
    private static final List<User> USERS = List.of(
            new User("Dobi06", "Ivan", "jfLFJ84LJDF4jl4**-2fkfj"),
            new User("Maquait", "Tom", "0Lp2fjbfffZFJ4328hLfo20))"),
            new User("Humster34", "Jessica", "^Hiu4vj177SJ(#&glfyg7hQ")
    );

    public static List<User> getUsers(){
        return USERS;
    }
}
