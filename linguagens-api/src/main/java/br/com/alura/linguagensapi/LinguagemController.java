package br.com.alura.linguagensapi;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/linguagens")
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping
    public List<Linguagem> obterLinguagens() {
        return repositorio.findAll().stream()
                .sorted(Comparator.comparingInt(Linguagem::getRanking)).toList();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Linguagem salvarLinguagem(@RequestBody Linguagem linguagem) {
        return repositorio.save(linguagem);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void atualizarLinguagem(@PathVariable String id, @RequestBody Linguagem linguagem) {
        repositorio.findById(id).ifPresent(l -> {
            repositorio.save(l.atualizar(linguagem));
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removerLinguagem(@PathVariable String id) {
        repositorio.findById(id).ifPresent(l -> {
            repositorio.delete(l);
        });
    }
}
