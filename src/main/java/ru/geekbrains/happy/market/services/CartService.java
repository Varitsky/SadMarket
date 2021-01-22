package ru.geekbrains.happy.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.happy.market.model.Product;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

//    @PostConstruct
    private final Map<Product, Integer> cartList = new HashMap<>();

    private final ProductService productService;

    public Map<Product, Integer> showCart(){
        return cartList;
    }

    public void addProductToCart(Long id) {
        Product product = productService.findProductById(id).orElseThrow(() -> new RuntimeException());
            if (!cartList.containsKey(product)){
                cartList.put(product, 1);
            } else {
                cartList.put(product, cartList.get(product) + 1);
            }
    }

    public void deleteProductFromCart(Long id){
        Product product = productService.findProductById(id).orElseThrow(() -> new RuntimeException());
        if (cartList.containsKey(product)) {
            cartList.remove(product);
        }
    }

    public void deleteAllProductFromCart(){
        cartList.clear();
    }
}
