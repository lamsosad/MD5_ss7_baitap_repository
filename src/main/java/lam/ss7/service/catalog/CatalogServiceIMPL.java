package lam.ss7.service.catalog;

import lam.ss7.model.entity.Catalog;
import lam.ss7.repository.ICatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogServiceIMPL implements ICatalogService {
    @Autowired
    private ICatalogRepository catalogRepository;

    @Override
    public Iterable<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public Optional<Catalog> findById(Long id) {
        return catalogRepository.findById(id);
    }

    @Override
    public void save(Catalog catalog) {
        catalogRepository.save(catalog);
    }

    @Override
    public void remove(Long id) {
        catalogRepository.deleteById(id);
    }
}
