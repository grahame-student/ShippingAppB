package com.example.demo.controller;

import com.example.demo.dto.Demo;
import com.example.demo.dto.Result;
import com.example.demo.repository.DemoRepository;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.Optional;

@RestController
@Log
public class DemoController {

    private final DemoRepository demoRepository;

    /**
     * Alternative to autowiring, use an autowire constructor.
     * @param demoRepository demo repository for spring boot to autowire
     */
    public DemoController(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @GetMapping("/api/demo/")
    public Demo getDemo(@RequestParam(name="id") String id) {
        log.info("Demo endpoint accessed.");
        Optional<Demo> result = this.demoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            log.warning("ID Could not be found in database");
            return null;
        }
    }

    @PostMapping("/api/demo")
    public Result postDemo(@RequestBody Demo demo) {
        this.demoRepository.save(demo);
        log.info("Demo with ID " + demo.getId() + " added to database");
        return new Result( "You POSTed a Demo object with an id of: " + demo.getId());
    }

    @GetMapping("/book")
    public String bookingTest(@RequestParam(value="details") String details){
        try{
            var wibble = details.split(":");
            return String.format("weight: " + wibble[0] + ", volume: " + wibble[1]);
        }
        catch(Exception e){
            return "error 400, bad request";
        }
    }

    @GetMapping("/BookPilot")
    public String bookPilot()
    {
        var book = new BookPilot();
        return book.bookPilot();
    }
}
