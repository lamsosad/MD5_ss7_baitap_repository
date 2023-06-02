package lam.ss7.controller;

import lam.ss7.model.entity.Blog;
import lam.ss7.model.entity.Catalog;
import lam.ss7.service.blog.IBlogService;
import lam.ss7.service.catalog.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogController")
@CrossOrigin("*")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICatalogService catalogService;

    @ModelAttribute("catalog")
    public Iterable<Catalog> provinces() {
        return catalogService.findAll();
    }

    @GetMapping
    public ResponseEntity<Iterable<Blog>> getList(@RequestParam("action") String action) {
        List<Blog> blogs;
        if (action.equals("all")){
            blogs = (List<Blog>) blogService.findAll();
        }else {
            blogs = (List<Blog>) blogService.showOverView();
        }
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping({"/show","/"})
    public ModelAndView showBlog() {
        ModelAndView modelAndView = new ModelAndView("/blog/blog");
        modelAndView.addObject("blog", blogService.findAll());
        return modelAndView;
    }

    @GetMapping("{id}")
    public ResponseEntity<Blog> findBlogById(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogOptional.get(), HttpStatus.OK);
    }


//    @GetMapping({"showList", ""})
//    public ModelAndView getList(Model model,@PageableDefault(sort = "title",size = 3) Pageable pageable) {
//        Page<Blog> blogs = blogService.findAll(pageable);
//        ModelAndView modelAndView = new ModelAndView("/blog/blog");
//        modelAndView.addObject("blog", blogs);
//        return modelAndView;
//    }

//    @GetMapping("/add")
//    public ModelAndView add() {
//        ModelAndView modelAndView = new ModelAndView("/blog/create");
//        modelAndView.addObject("blog", new Blog());
//        return modelAndView;
//    }

    //    @PostMapping("/create")
//    public String create(@ModelAttribute("blog") Blog blog) {
//        blogService.save(blog);
//        return "redirect:/";
//    }
    @PostMapping
    public ResponseEntity<Blog> create(@RequestBody Blog blog) {
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.CREATED);
    }

    //    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        blogService.remove(id);
//        return "redirect:/";
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> delete(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blogService.remove(id);
        return new ResponseEntity<>(blogOptional.get(), HttpStatus.NO_CONTENT);
    }

    //    @GetMapping("/edit/{id}")
//    public ModelAndView add(@PathVariable("id") Long id) {
//        return new ModelAndView("/blog/edit", "blog", blogService.findById(id));
//    }
//
//    @PostMapping("/update")
//    public String update(@ModelAttribute("blog") Blog blog) {
//        blogService.save(blog);
//        return "redirect:/";
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateCustomer(@PathVariable Long id, @RequestBody Blog blog) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blog.setId(blogOptional.get().getId());
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.OK);
    }

    //    @GetMapping("/show/{id}")
//    public ModelAndView showDetail(@PathVariable("id") Long id) {
//        return new ModelAndView("/blog/blogDetail", "blog", blogService.findById(id).get());
//    }
//
//    @GetMapping("/search")
//    public ModelAndView searchProduct(@RequestParam("search") String search, Pageable pageable) {
//        Page<Blog> blogPage;
//        if (!search.trim().equals("")) {
//            blogPage = blogService.findByTitleBlog(search, pageable);
//        } else {
//            blogPage = blogService.findAll(pageable);
//        }
//        ModelAndView modelAndView = new ModelAndView("/blog/blog");
//        modelAndView.addObject("blog", blogPage);
//        return modelAndView;
//    }
    @GetMapping("/search")
    public ResponseEntity<Blog> searchProduct(@RequestParam("search") String search) {
        Optional<Blog> blogOptional = blogService.findByTitleBlog(search);
        if (!blogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogOptional.get(), HttpStatus.OK);
    }

}
