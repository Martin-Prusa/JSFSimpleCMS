package cz.martin.repositories;

import com.google.gson.Gson;
import cz.martin.models.User;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Normal
public class UsersRepository {
    private Gson gson = new Gson();

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("simple-cms-jsf-users.json"));
            String json = br.readLine();
            users = new ArrayList<>(List.of(gson.fromJson(json, User[].class)));
            br.close();
        } catch (IOException ignored) {}
        return users;
    }

    public void save(List<User> users) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("simple-cms-jsf-users.json", false));
            String json = gson.toJson(users);
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
