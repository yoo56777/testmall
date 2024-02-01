package com.testmall.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.testmall.Service.ShoppingCart;
import com.testmall.Model.Carts;

import java.util.List;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private ShoppingCart shoppingCart;

    // 2024-01-22修改-B 直接return List<Carts>物件會自動轉json格式
    @GetMapping("/getCartAll")
    @ResponseBody
    public List<Carts> getCartAll(){
//        Gson gson = new Gson();
//        String jsonList = gson.toJson(shoppingCart.queryAll());
//        return jsonList;
        return shoppingCart.queryAll();
    }
    // 2024-01-22修改-E

    @PostMapping("/add")
    public ResponseEntity<String> addCartItem(@RequestBody Carts item) {
        boolean added = shoppingCart.addCartItem(item);
        if (added){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found or invalid in the cart");
        }
    }

    @DeleteMapping("/remove")
  //public ResponseEntity<String> removeCartItem(@RequestParam Long productId) {
    public ResponseEntity<String> removeCartItem(@RequestBody List<Integer> cartSeq) {
        // 2024-01-22 這邊應該是要購物車流水號
        boolean removed = shoppingCart.removeCartItem(cartSeq);
        if (removed){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found or invalid in the cart");
        }
    }

    @PutMapping("/updateQty")
    public ResponseEntity<String> updateCartItemQuantity(
            // 2024-01-22 這邊應該是要傳入流水號參數,因為可能有很多人買同一商品,只有商品id不知道是誰的
          //@RequestParam Long productId,
            @RequestParam int cartSeq,
            @RequestParam int quantity) {
        boolean updated = shoppingCart.updateCartItemQuantity(cartSeq, quantity);
        if (updated){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in the cart");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCart(@RequestBody Carts carts) {
        boolean updated = shoppingCart.updateCart(carts);
        if (updated){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("更新失敗!");
        }
    }

    @GetMapping("/view")
    public ResponseEntity<ShoppingCart> viewCart() {
        return ResponseEntity.ok(shoppingCart);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
    // 增加全域異常處理，處理未預期的異常情況(處理網頁異常)
}
