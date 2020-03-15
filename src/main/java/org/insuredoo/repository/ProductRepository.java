package org.insuredoo.repository;

import java.util.List;

import org.insuredoo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long>
{
		
	@Query("SELECT p FROM Product p WHERE p.brandscovered LIKE %:marke% AND p.modelfrom >= :year order by p.amount")
	public List<Product> findProducts(@Param("year") int year,@Param("marke") String marke);
	
}
