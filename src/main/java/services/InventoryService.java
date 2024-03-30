package services;

import model.Inventory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InventoryService {
    private Inventory inventory;

    // Constructor
    public InventoryService() {
        initializeInventory();
    }
    public void initializeInventory() {
        inventory = new Inventory();
        inventory.setOrdered(0);
        inventory.setPrice(100);
        inventory.setAvailable(100);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void updateInventory(int ordered) {
        int available = inventory.getAvailable() - ordered;
        if (available >= 0) {
            inventory.setOrdered(ordered);
            inventory.setAvailable(available);
        } else {
            // Handle insufficient inventory error
            System.out.println("Insufficient inventory");
        }
    }
}
