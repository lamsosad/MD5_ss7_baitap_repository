package lam.ss7.controller;

import lam.ss7.model.entity.Catalog;
import lam.ss7.service.catalog.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("catalogController")
public class CatalogController {
    @Autowired
    private ICatalogService catalogService;
    @GetMapping("showAll")
    public ModelAndView showAll(Model model){
        Iterable<Catalog> catalogs=catalogService.findAll();
        ModelAndView modelAndView=new ModelAndView("/catalog/catalog");
        modelAndView.addObject("catalog",catalogs);
        return modelAndView;
    }
    @GetMapping("/addCatalog")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("/catalog/create");
        modelAndView.addObject("catalog", new Catalog());
        return modelAndView;
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("catalog") Catalog catalog) {
        catalogService.save(catalog);
        return "redirect:/catalogController/showAll";
    }
    @GetMapping("/deleteCatalog/{id}")
    public String delete(@PathVariable("id") Long id) {
        catalogService.remove(id);
        return "redirect:/catalogController/showAll";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView add(@PathVariable("id") Long id) {
        return new ModelAndView("/catalog/edit", "catalog", catalogService.findById(id));
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("catalog") Catalog catalog) {
        catalogService.save(catalog);
        return "redirect:/catalogController/showAll";
    }
}
