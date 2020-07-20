package com.utrechtfour.supermarket.controller.rest;
import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.model.Product;
import com.utrechtfour.supermarket.service.ProductService;
import com.utrechtfour.supermarket.views.RestViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.ProductView.class})
    public ResponseEntity getProductById (@PathVariable Long id, HttpServletResponse response){

        if (productService.getProductById(id).isPresent()){
            return new ResponseEntity(productService.getProductById(id), HttpStatus.OK);
        }
          else
              throw new ValidationException("Cannot find Product with an id of " + id);
    }

    @Transactional
    @PostMapping("/product")
    @JsonView({RestViews.ProductView.class})
    public ResponseEntity<Product> createProduct (@RequestBody @Valid Product product) throws Throwable {

        if (product.getId() != null){
            throw new ValidationException("Id is automatically created by the database, please do the request again without providing an id");
        }
            return new ResponseEntity(productService.createProduct(product),HttpStatus.CREATED);
    }



    @PutMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.ProductView.class})
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product, @PathVariable Long id){

            return new ResponseEntity(productService.updateProduct(product, id),HttpStatus.OK);
    }

    @PatchMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView({RestViews.ProductView.class})
    public ResponseEntity<Product> updateProduct(@RequestBody Map<String,Object> updates, @PathVariable Long id){

        return new ResponseEntity(productService.patchProduct(updates, id),HttpStatus.OK);
    }


}
