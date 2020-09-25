package br.newtonpaiva.av.revistas.controller;

import br.newtonpaiva.av.revistas.domain.Revista;
import br.newtonpaiva.av.revistas.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import br.newtonpaiva.av.revistas.service.RevistaService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/revistas")
public class RevistaController {

    private final RevistaService revistaService;

    @Autowired
    public RevistaController(RevistaService revistaService) {
        this.revistaService = revistaService;
    }

    @GetMapping
    private ResponseEntity<List<Revista>> getRevistas() {
        List<Revista> revistas = this.revistaService.getRevistas();
        if (!CollectionUtils.isEmpty(revistas)) {
            return ResponseEntity.ok(revistas);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Revista> getRevista(@PathVariable String id) {
        try {
            Revista revista = this.revistaService.getRevista(id);
            return ResponseEntity.ok(revista);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Void> postRevista(@Valid @RequestBody Revista revista) {
        this.revistaService.salvar(revista);
        return ResponseEntity.created(URI.create("/revistas/" + revista.getId())).build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> putRevista(@Valid @RequestBody Revista revista) {
        try {
            this.revistaService.atualizar(revista);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteRevista(@PathVariable String id) {
        try {
            this.revistaService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
