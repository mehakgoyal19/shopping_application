package app.controller;

import app.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import app.services.InventoryService;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory")
    public ResponseEntity<Inventory> getInventory() {
        Inventory inventory = inventoryService.getInventory();
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/order")
    public ResponseEntity<String> orderItems() {
        inventoryService.updateInventory(50); // Example: order 50 items
        return ResponseEntity.ok("Items ordered successfully");
    }
}