package bg.zemyanadlan.domain.listing;

import bg.zemyanadlan.domain.location.Location;
import bg.zemyanadlan.domain.product.Product;
import bg.zemyanadlan.user.User;

import java.math.BigDecimal;

public class ProductListing extends Listing{

    private Product product;

    public ProductListing(String title,
                             User owner,
                             Location location,
                             BigDecimal price,
                             Product product) {
        super(title, owner, location, price);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
