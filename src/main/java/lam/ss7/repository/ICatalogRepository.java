package lam.ss7.repository;

import lam.ss7.model.entity.Catalog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatalogRepository extends PagingAndSortingRepository<Catalog,Long> {
}
