package br.newtonpaiva.av.revistas.service;

import br.newtonpaiva.av.revistas.domain.Revista;
import br.newtonpaiva.av.revistas.exception.NotFoundException;

import java.util.List;

public interface RevistaService {

    List<Revista> getRevistas();

    Revista getRevista(String id) throws NotFoundException;

    void salvar(Revista revista);

    void atualizar(Revista revista) throws NotFoundException;

    void remover(String id) throws NotFoundException;
}
