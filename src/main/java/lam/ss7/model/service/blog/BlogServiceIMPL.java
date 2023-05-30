package lam.ss7.model.service.blog;

import lam.ss7.model.entity.Blog;
import lam.ss7.model.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class BlogServiceIMPL implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Iterable findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }


    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }
}
