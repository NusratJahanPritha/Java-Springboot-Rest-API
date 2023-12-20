package demo;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {

    public void checkout(String status){
        //logging
        //Authentication and authorization
        //sanitize the data
        //these are the cross cutting concerns and we can create different aspects from these,
        //AOP will seperate out these cross cutting concerns
        System.out.println("shopping cart");
    }
    public int quantity(Integer number){
        return 2;
    }
}
