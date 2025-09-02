package com.example.hotelreservation.controller;

import com.example.hotelreservation.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hotel")
@CrossOrigin(origins = "http://localhost:5173") // allow React app
public class HotelController {
    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @PostMapping("/book/{numRooms}")
    public Map<String, Object> book(@PathVariable int numRooms) {
        return service.bookRooms(numRooms);
    }

    @PostMapping("/reset")
    public String reset() {
        service.reset();
        return "Hotel reset!";
    }

    @PostMapping("/random")
    public String random() {
        service.randomOccupancy();
        return "Random occupancy set!";
    }

    @GetMapping("/availability")
    public Map<Integer, List<Integer>> availability() {
        return service.getAvailability();
    }
}
