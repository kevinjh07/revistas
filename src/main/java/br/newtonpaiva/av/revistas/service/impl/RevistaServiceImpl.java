package br.newtonpaiva.av.revistas.service.impl;

import br.newtonpaiva.av.revistas.domain.Revista;
import br.newtonpaiva.av.revistas.exception.NotFoundException;
import br.newtonpaiva.av.revistas.repository.RevistaRepository;
import br.newtonpaiva.av.revistas.service.RevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RevistaServiceImpl implements RevistaService {
    private final RevistaRepository revistaRepository;

    @Autowired
    public RevistaServiceImpl(RevistaRepository revistaRepository) {
        this.revistaRepository = revistaRepository;
    }

    @Override
    public List<Revista> getRevistas() {
        return this.revistaRepository.findAll();
    }

    @Override
    public Revista getRevista(String id) throws NotFoundException {
        Optional<Revista> revista = this.revistaRepository.findById(id);
        return revista.orElseThrow(NotFoundException::new);
    }

    @Override
    public void salvar(Revista revista) {
        this.revistaRepository.save(revista);
    }

    @Override
    public void atualizar(Revista revista) throws NotFoundException {
        this.getRevista(revista.getId());
        this.revistaRepository.save(revista);
    }

    @Override
    public void remover(String id) throws NotFoundException {
        Revista revista = this.getRevista(id);
        this.revistaRepository.delete(revista);
    }
}
