package lam.ss7.controller;

import lam.ss7.model.entity.Catalog;
import lam.ss7.service.catalog.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalogController")
public class CatalogController {
    @Autowired
    private ICatalogService catalogService;
//    @GetMapping("showAll")
//    public ModelAndView showAll(Model model){
//        Iterable<Catalog> catalogs=catalogService.findAll();
//        ModelAndView modelAndView=new ModelAndView("/catalog/catalog");
//        modelAndView.addObject("catalog",catalogs);
//        return modelAndView;
//    }

    @GetMapping
    public ResponseEntity<Iterable<Catalog>> showAll() {
        List<Catalog> catalogs = (List<Catalog>) catalogService.findAll();
        if (catalogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(catalogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalog> findCatalogById(@PathVariable Long id) {
        Optional<Catalog> catalog = catalogService.findById(id);
        if (!catalog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(catalog.get(), HttpStatus.OK);
    }

    @GetMapping("/addCatalog")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("/catalog/create");
        modelAndView.addObject("catalog", new Catalog());
        return modelAndView;
    }
//    @PostMapping("/create")
//    public String create(@ModelAttribute("catalog") Catalog catalog) {
//        catalogService.save(catalog);
//        return "redirect:/catalogController/showAll";
//    }

    @PostMapping
    public ResponseEntity<Catalog> create(@RequestBody Catalog catalog) {
        return new ResponseEntity<>(catalogService.save(catalog), HttpStatus.CREATED);
    }
//    @GetMapping("/deleteCatalog/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        catalogService.remove(id);
//        return "redirect:/catalogController/showAll";
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Catalog> delete(@PathVariable Long id) {
        Optional<Catalog> catalogOptional = catalogService.findById(id);
        if (!catalogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catalogService.remove(id);
        return new ResponseEntity<>(catalogOptional.get(), HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/edit/{id}")
//    public ModelAndView add(@PathVariable("id") Long id) {
//        return new ModelAndView("/catalog/edit", "catalog", catalogService.findById(id));
//    }
//
//    @PostMapping("/update")
//    public String update(@ModelAttribute("catalog") Catalog catalog) {
//        catalogService.save(catalog);
//        return "redirect:/catalogController/showAll";
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Catalog> update(@PathVariable Long id, @RequestBody Catalog catalog) {
        Optional<Catalog> catalogOptional = catalogService.findById(id);
        if (!catalogOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catalog.setId(catalogOptional.get().getId());
        return new ResponseEntity<>(catalogService.save(catalog), HttpStatus.OK);
    }

}
