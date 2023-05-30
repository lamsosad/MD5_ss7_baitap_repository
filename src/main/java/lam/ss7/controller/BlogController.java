package lam.ss7.controller;

import lam.ss7.model.entity.Blog;
import lam.ss7.model.entity.Catalog;
import lam.ss7.service.blog.IBlogService;
import lam.ss7.service.catalog.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"blogController", "/"})
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICatalogService catalogService;

    @ModelAttribute("catalog")
    public Iterable<Catalog> provinces(){
        return catalogService.findAll();
    }

    @GetMapping({"showList", ""})
    public ModelAndView getList(Model model,@PageableDefault(sort = "title",size = 3) Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/blog/blog");
        modelAndView.addObject("blog", blogs);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        blogService.remove(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView add(@PathVariable("id") Long id) {
        return new ModelAndView("/blog/edit", "blog", blogService.findById(id));
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/";
    }

    @GetMapping("/show/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id) {
        return new ModelAndView("/blog/blogDetail", "blog", blogService.findById(id).get());
    }
    @GetMapping("/search")
    public ModelAndView searchProduct(@RequestParam("search") String search, Pageable pageable){
        Page<Blog> blogPage;
        if(!search.trim().equals("")){
            blogPage = blogService.findByTitleBlog(search,pageable);
        } else {
            blogPage = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/blog");
        modelAndView.addObject("blog",blogPage);
        return modelAndView;
    }


}
