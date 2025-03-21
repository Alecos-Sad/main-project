package by.sadovnick.dataanalyserkafka.repository;

import by.sadovnick.dataanalyserkafka.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
}
