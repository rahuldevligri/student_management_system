package com.StudentManagementSystem.com.repository;

import com.StudentManagementSystem.com.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
//1. Custom(Finder) Methods
    //Type 1
    Product findByProductName(String productName); //if we want to find by productName
    //Type 2
    Product findByPId(int pid); //if we want to find by pId
    //Type 3
    Product findByProductNameIs(String productName); //alternative of Type 1
    //Type 4
    Product findByProductNameEquals(String productName); //alternative of Type 1
    //Type 5 -- opposite
    Product findByProductNameIsNot(String productName);// opposite of Type 1
    //Type 6 -- if we want to check null
    List<Product> findByProductNameIsNull(String productName);
    //Type 7 -- if we want to check not null
    List<Product> findByProductNotNull(String productName);

    //Type-8 -- if we want to find live products
    List<Product> findByActiveTrue();//findByActive is field name and True is value.
    List<Product> findByActiveFalse();//findByActive is field name and False is value.

    //Type-9 --if we want all products that are start with specific prefix
    List<Product> findByProductNameStartingWith(String prefix);
    //Type - 10 --if we want all products that are end with specific suffix
    List<Product> findByProductNameEndingWith(String suffix);
    //Type - 11 --if we want all products that are end with specific infix
    List<Product> findByProductNameContaining(String infix);
    //Type-12 -- we can use like operator instant of prefix, suffix or infix
    String pattern = "Samsung%";
//    String pattern = "%samsung";
//    String pattern = "%sam%";
    List<Product> findByProductNameLike(String pattern);

    //Type -13 -- if we want all products less than price
    List<Product> findByPriceLessThan(double price);// less than
    //Type -14 -- if we want all products less than or Equal price
    List<Product> findByPriceLessThanEqual(double price);// less than or Equal

    //Type -15 -- if we want all products Greater than price
    List<Product> findByPriceGreaterThan(double price);// Greater than
    //Type -16 -- if we want all products less than or Equal price
    List<Product> findByPriceGreaterThanEqual(double price);// Greater than or Equal

    //Type -17 -- if we want all the products related to names
    List<Product> findByProductNameIn(Collection<String> names);

    //Type -18 --if we want to use AND, OR on multiple conditions
    //we will get products if both conditions are true
    List<Product> findByProductNameAndPrice(String name, double price);
    //we will get products if one conditions is true
    List<Product> findByProductNameOrPrice(String name, double price);

    //Type-19 -- if we want to sort data in Ascending or Descending
    Product findByProductNameAsc(String productName);
    Product findByProductNameOrderByProductNameDesc(String productName);

    //Type-20 -- Optional - we use for handling null value
    Optional<Product> findByProductNameUsingOptional(String productName);

//2. Query Methods
    //Type1 -- select all products with query
    //JPQL:
    @Query(value = "SELECT p FROM Product p")
    List<Product> getAllProductWhileLEarningJpa();

    //Type2 -- find all active products
    @Query("SELECT p form Product p WHERE p.active=1")
    List<Product> getAllActiveProducts();
    //we can use AND also
    @Query("SELECT p form Product p WHERE p.productName = 'Samsung s22' AND p.active=1")
    List<Product> getAllActiveProductss();

    //Type3 -- we can pass dynamic query also
    //position query
    @Query("SELECT p from Product p WHERE p.pId = ?1 AND p.productName =?2")
    Product getSingleProductByIdAndName1(int pId, String productName);

    //Type4 -- NamedParameter
    @Query("SELECT p from Product p WHERE p.pId = :productId AND p.productName = :productName")
    Product getSingleProductByIdAndName2(@Param("productId") int productId, @Param("productName") String productName);

    //Type5 --     @Modifying //for update query
    @Modifying
    @Query("UPDATE Product p SET p.active = false WHERE p.pId = :productId")
    void deactivateProduct(@Param("productId") int productId);

    //Type6 -- Native Queries -->
    @Query(value = "SELECT * FROM product WHERE product_name = :productName", nativeQuery = true)
    Product getProductByNativeQuery(@Param("productName") String productName);

    //Type7 -- Sorting: Use Sort to sort query results dynamically.
    @Query("SELECT p FROM Product p WHERE p.active = 1")
    List<Product> getActiveProducts(Sort sort);

    //Type8 -- Pagination: Use Pageable to paginate query results.
    @Query("SELECT p FROM Product p WHERE p.active = 1")
    Page<Product> getPagedActiveProducts(Pageable pageable);

   //Type9 --  For native queries with pagination, include the countQuery parameter:
    @Query(value = "SELECT * FROM product WHERE active = 1",
            countQuery = "SELECT COUNT(*) FROM product WHERE active = 1",
            nativeQuery = true)
    Page<Product> getNativePagedActiveProducts(Pageable pageable);
}
