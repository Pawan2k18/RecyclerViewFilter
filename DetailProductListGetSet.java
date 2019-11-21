package com.nst.airvegs;

public class DetailProductListGetSet {
    private String id;
    private String enable_product;
    private String enable_pincode;
    private String product_name;
    private String delivery_type;
    private String label_option;
     private String  quantity;
     private String  price;
     private String  special_price;
     private String  sku;
     private String  stock_status;
     private String  production_time;
     private String  product_type;
     private String  parent_prod_id;

    public DetailProductListGetSet(String id, String enable_product, String enable_pincode, String product_name, String delivery_type, String label_option, String quantity, String price, String special_price, String sku, String stock_status, String production_time, String product_type, String parent_prod_id) {
        this.id = id;
        this.enable_product = enable_product;
        this.enable_pincode = enable_pincode;
        this.product_name = product_name;
        this.delivery_type = delivery_type;
        this.label_option = label_option;
        this.quantity = quantity;
        this.price = price;
        this.special_price = special_price;
        this.sku = sku;
        this.stock_status = stock_status;
        this.production_time = production_time;
        this.product_type = product_type;
        this.parent_prod_id = parent_prod_id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnable_product() {
        return enable_product;
    }

    public void setEnable_product(String enable_product) {
        this.enable_product = enable_product;
    }

    public String getEnable_pincode() {
        return enable_pincode;
    }

    public void setEnable_pincode(String enable_pincode) {
        this.enable_pincode = enable_pincode;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public String getLabel_option() {
        return label_option;
    }

    public void setLabel_option(String label_option) {
        this.label_option = label_option;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecial_price() {
        return special_price;
    }

    public void setSpecial_price(String special_price) {
        this.special_price = special_price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getStock_status() {
        return stock_status;
    }

    public void setStock_status(String stock_status) {
        this.stock_status = stock_status;
    }

    public String getProduction_time() {
        return production_time;
    }

    public void setProduction_time(String production_time) {
        this.production_time = production_time;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getParent_prod_id() {
        return parent_prod_id;
    }

    public void setParent_prod_id(String parent_prod_id) {
        this.parent_prod_id = parent_prod_id;
    }
}


