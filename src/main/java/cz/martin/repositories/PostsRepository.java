package cz.martin.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.martin.gson.deserializers.LocalDateTimeDeserializer;
import cz.martin.gson.serializers.LocalDateTimeSerializer;
import cz.martin.interfaces.repositories.IPostsRepository;
import cz.martin.models.Post;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Normal
public class PostsRepository implements IPostsRepository {

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .create();

    @Override
    public List<Post> loadPosts() {
        List<Post> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("simple-cms-jsf-data.json"));
            String json = br.readLine();
            list = new ArrayList<>(List.of(gson.fromJson(json, Post[].class)));
            br.close();
        } catch (Exception ignored) {}
        return list;
    }

    @Override
    public void savePosts(List<Post> posts) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("simple-cms-jsf-data.json", false));
            bw.write(gson.toJson(posts));
            bw.close();
        } catch (IOException ignored) {}
    }
}
