package com.example.hotelreservation.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class HotelService {
    private Map<Integer, List<Integer>> hotel = new HashMap<>();

    public HotelService() {
        reset();
    }

    private void initializeRooms() {
        for (int floor = 1; floor <= 9; floor++) {
            List<Integer> rooms = new ArrayList<>();
            for (int r = 1; r <= 10; r++) {
                rooms.add(floor * 100 + r);
            }
            hotel.put(floor, rooms);
        }
        List<Integer> lastFloorRooms = new ArrayList<>();
        for (int r = 1; r <= 7; r++) {
            lastFloorRooms.add(1000 + r);
        }
        hotel.put(10, lastFloorRooms);
    }

    public Map<String, Object> bookRooms(int numRooms) {
        List<Integer> booked = new ArrayList<>();
        for (int floor : hotel.keySet()) {
            List<Integer> available = hotel.get(floor);
            while (!available.isEmpty() && booked.size() < numRooms) {
                booked.add(available.remove(0));
            }
            if (booked.size() == numRooms) break;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("rooms", booked);
        result.put("travelTime", booked.size() > 0 ? booked.size() * 1 : 0);
        return result;
    }

    public void reset() {
        hotel.clear();
        initializeRooms();
    }

    public Map<Integer, List<Integer>> getAvailability() {
        return hotel;
    }

    public void randomOccupancy() {
        reset();
        Random rand = new Random();
        for (List<Integer> rooms : hotel.values()) {
            rooms.removeIf(r -> rand.nextDouble() < 0.3); // 30% chance room unavailable
        }
    }
}
