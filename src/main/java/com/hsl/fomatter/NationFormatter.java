package com.hsl.fomatter;

import com.hsl.model.Nation;
import com.hsl.service.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class NationFormatter implements Formatter<Nation> {

    private INationService nationService;

    @Autowired
    public NationFormatter(INationService nationService) {
        this.nationService = nationService;
    }

    @Override
    public Nation parse(String text, Locale locale) throws ParseException {
        Optional<Nation> nationOptional = nationService.findById(Long.parseLong(text));
        return nationOptional.orElse(null);
    }

    @Override
    public String print(Nation object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}