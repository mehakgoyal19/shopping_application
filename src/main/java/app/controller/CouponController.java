package app.controller;

import app.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CouponController {

    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/fetchCoupons")
    public ResponseEntity<Map<String, Integer>> fetchCoupons() {
        Map<String, Integer> coupons = (Map<String, Integer>) couponService.fetchCoupons();
        return ResponseEntity.ok(coupons);
    }
}
