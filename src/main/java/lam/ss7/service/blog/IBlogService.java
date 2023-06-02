package lam.ss7.service.blog;


import lam.ss7.model.entity.Blog;
import lam.ss7.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IBlogService extends IService<Blog> {
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
    Optional<Blog> findByTitleBlog(@Param("title") String title);

    Iterable<Blog> showOverView();
}
