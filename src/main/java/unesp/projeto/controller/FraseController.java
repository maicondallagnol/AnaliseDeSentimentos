package unesp.projeto.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unesp.projeto.entity.Frase;
import unesp.projeto.service.FraseService;

import java.util.Optional;

@RestController
//@Api(value = "This endpoint returns manipulates all info about books")
@RequiredArgsConstructor
@RequestMapping(value = "/frases/")
public class FraseController {


    private final FraseService frase_service;

    @GetMapping("/random")
    public Optional<Frase> getFrase()
    {
        return frase_service.getRandom();
    }

}
