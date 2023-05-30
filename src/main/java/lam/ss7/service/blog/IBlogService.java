package lam.ss7.service.blog;


import lam.ss7.model.entity.Blog;
import lam.ss7.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IBlogService extends IService<Blog> {
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
    Page<Blog> findByTitleBlog(@Param("title") String title, Pageable pageable);
}
