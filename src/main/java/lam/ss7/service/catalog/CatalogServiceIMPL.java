package lam.ss7.service.catalog;

import lam.ss7.model.entity.Blog;
import lam.ss7.model.entity.Catalog;
import lam.ss7.repository.ICatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Catalog save(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Override
    public void remove(Long id) {
        catalogRepository.deleteById(id);
    }

}
