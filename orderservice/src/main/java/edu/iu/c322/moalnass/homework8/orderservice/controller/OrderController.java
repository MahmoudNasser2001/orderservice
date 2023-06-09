package edu.iu.c322.moalnass.homework8.orderservice.controller;

import edu.iu.c322.moalnass.homework8.orderservice.Model.Order;
import edu.iu.c322.moalnass.homework8.orderservice.Model.OrderItem;
import edu.iu.c322.moalnass.homework8.orderservice.Repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import edu.iu.c322.moalnass.homework8.orderservice.Model.Return;
import edu.iu.c322.moalnass.homework8.orderservice.Repository.ReturnRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository repository;
    private ReturnRepository returnRepository;

    public OrderController(OrderRepository repository, ReturnRepository returnRepository) {
        this.repository = repository;
        this.returnRepository = returnRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@Valid  @RequestBody Order order){


        for(int i = 0; i < order.getItems().size(); i++){
            OrderItem item = order.getItems().get(i);
            item.setOrder(order);
        }
        Order addedOrder = repository.save(order);
        return addedOrder.getId();
    }

    @GetMapping("/{customerId}")
    public List<Order> findByCustomer(@PathVariable int customerId){
        return repository.findByCustomerId(customerId);
    }

    @GetMapping("/order/{orderId}")
    public Optional<Order> findByOrderId(@PathVariable int orderId){
        return repository.findById(orderId);


    }

    @PutMapping("/return")
    public void submitReturnRequest(@RequestBody Return returnRequest){
        Optional<Order> order = repository.findById(returnRequest.getOrderId());
        if(!order.isPresent()) {
            throw new IllegalStateException("order with this order id was not found.");
        }

        returnRepository.save(returnRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        Optional<Order> order = repository.findById(id);
        if (!order.isPresent()) {
            throw new IllegalStateException("Order with id " + id + " does not exist.");
        }
        repository.deleteById(id);
    }

}