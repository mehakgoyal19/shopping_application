package app.services;

import app.model.Coupon;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
    public Coupon fetchCoupons() {
        Coupon coupon = new Coupon();
        coupon.setCode("OFF5");
        coupon.setDiscount(5);
        return coupon;
    }
}
