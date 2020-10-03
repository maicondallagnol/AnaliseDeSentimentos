package unesp.projeto.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unesp.projeto.entity.Frase;
import unesp.projeto.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FraseService{

    @Autowired
    FraseRepository repo;

    public Optional<Frase> findByidFrase(long idFrase)
    {
        return repo.findById(idFrase);
    }

    public Optional<Frase> getRandom()
    {
        Random rnd = new Random();

        long rnd_number = (long) rnd.nextInt(5000);

        return findByidFrase(rnd_number);
    }
}
