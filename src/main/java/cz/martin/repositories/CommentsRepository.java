package cz.martin.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.martin.gson.deserializers.LocalDateTimeDeserializer;
import cz.martin.gson.serializers.LocalDateTimeSerializer;
import cz.martin.interfaces.repositories.ICommentsRepository;
import cz.martin.models.Comment;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Normal
public class CommentsRepository implements ICommentsRepository {

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .create();

    @Override
    public void save(List<Comment> comments) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("simple-cms-jsf-comments.json", false));
            bw.write(gson.toJson(comments));
            bw.close();
        } catch (IOException ignored) {}
    }

    @Override
    public List<Comment> load() {
        List<Comment> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("simple-cms-jsf-comments.json"));
            String json = br.readLine();
            list = new ArrayList<>(List.of(gson.fromJson(json, Comment[].class)));
            br.close();
        } catch (Exception ignored) {}
        return list;
    }
}
