package edu.iu.c322.moalnass.homework8.orderservice.Repository;
import edu.iu.c322.moalnass.homework8.orderservice.Model.Order;
import edu.iu.c322.moalnass.homework8.orderservice.Model.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Integer> {
}
