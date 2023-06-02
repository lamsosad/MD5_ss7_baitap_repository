package lam.ss7.repository;

import lam.ss7.model.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {
    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
    @Query("SELECT b FROM Blog AS b WHERE b.title LIKE CONCAT('%',:title,'%')")
    Optional<Blog> findByTitleBlog(@Param("title") String title);

    @Query(nativeQuery = true, value = "select * from blog limit 5")
    Iterable<Blog> showOverView();
}
