package com.example.demo.controller;

import com.example.demo.repository.Trie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {
    @GetMapping(path = {"/"})
    public String search() {
        return "index";
    }
    @GetMapping(path = {"/get"})
    public String get(ModelMap map, String keyword){
        List<String> words = List.of("Samsung", "Sony", "Xiaomi", "iPhone", "Apple", "Oppo", "Motorola","Nokia","Nothing","Pixel");
        Trie getSuggestion = new Trie(words);
        if(keyword!=null) {
            if(getSuggestion.suggest(keyword)!=null){
                map.addAttribute("list", getSuggestion.suggest(keyword));
                map.addAttribute("icon", "");
            }else{
                map.addAttribute("list", Arrays.asList("No results found.."));
                map.addAttribute("icon", "");
            }
        }else{
            map.addAttribute("list", Arrays.asList(".."));
            map.addAttribute("icon", "");
        }
        return "fragments :: search-results";
    }
}
