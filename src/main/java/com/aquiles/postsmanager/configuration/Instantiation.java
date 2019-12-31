package com.aquiles.postsmanager.configuration;

import com.aquiles.postsmanager.domain.Post;
import com.aquiles.postsmanager.domain.User;
import com.aquiles.postsmanager.dto.AuthorDTO;
import com.aquiles.postsmanager.repository.PostRepository;
import com.aquiles.postsmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User aquiles = new User(null, "Aquiles", "aquiles@gmail.com");
        User joao = new User(null, "João", "joao@gmail.com");
        User maria = new User(null, "Maria", "maria@gmail.com");
        userRepository.saveAll(Arrays.asList(aquiles, joao, maria));

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        date.setTimeZone(TimeZone.getTimeZone("GMT"));

        Post postAquiles = new Post(null, date.parse("15/10/2019"), "Férias!", "Finalmente! #Partiu viagem!!!", new AuthorDTO(aquiles));
        Post postJoao = new Post(null, date.parse("19/11/2019"), "Aprovado!", "Passei no vestibular!!", new AuthorDTO(joao));

        postRepository.saveAll(Arrays.asList(postAquiles, postJoao));
    }
}
