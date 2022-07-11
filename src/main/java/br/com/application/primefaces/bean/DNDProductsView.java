package br.com.application.primefaces.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.DragDropEvent;

import br.com.application.model.Product;
import br.com.application.service.ProductService;

@SuppressWarnings("serial")
@Named("dndProductsView")
@ViewScoped
public class DNDProductsView implements Serializable {

    @Inject private ProductService service;

    private List<Product> products;

    private List<Product> droppedProducts;

    private Product selectedProduct;

    @PostConstruct
    public void init() {
        products = service.getProducts(9);
        droppedProducts = new ArrayList<>();
    }

    public void onProductDrop(DragDropEvent<Product> ddEvent) {
        Product product = ddEvent.getData();

        droppedProducts.add(product);
        products.remove(product);
    }

    public void setService(ProductService service) {
        this.service = service;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> getDroppedProducts() {
        return droppedProducts;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}
