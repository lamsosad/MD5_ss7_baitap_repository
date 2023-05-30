package lam.ss7.formatter;

import lam.ss7.model.entity.Catalog;
import lam.ss7.service.catalog.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class CatalogFormatter implements Formatter<Catalog> {
    private ICatalogService catalogService;
    @Autowired
    public CatalogFormatter(ICatalogService catalogService) {
        this.catalogService = catalogService;
    };
    @Override
    public Catalog parse(String text, Locale locale) throws ParseException {
        Optional<Catalog> optionalCategory = catalogService.findById(Long.parseLong(text));
        return optionalCategory.orElse(null);
    }

    @Override
    public String print(Catalog object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
