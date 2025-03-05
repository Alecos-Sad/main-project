package by.sadovnick.socksapihhru.repository;

import by.sadovnick.socksapihhru.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SockRepository extends JpaRepository<Sock, Long> {

    Optional<Sock> findByColorAndCottonPercentage(String color, int cottonPercentage);

    @Query(
            """
                    SELECT SUM (s.quantity) FROM Sock s
                    WHERE (:color IS NULL OR s.color = :color)
                        AND ((:cottonPercentageMin IS NULL OR :cottonPercentageMax IS NULL)
                            OR
                            (:cottonPercentageMin IS NOT NULL AND :cottonPercentageMax IS not NULL AND
                                s.cottonPercentage BETWEEN :cottonPercentageMin AND :cottonPercentageMax)
                            OR
                            (
                                (:cottonPercentage IS NOT NULL AND :operator IS NOT NULL AND (
                                    (:operator = 'moreThan' AND s.cottonPercentage > :cottonPercentage) OR
                                    (:operator = 'lessThan' AND s.cottonPercentage < :cottonPercentage) OR
                                     (:operator = 'equal' AND s.cottonPercentage = :cottonPercentage)
                                )
                           )
                       )
                    )
                    """
    )
    Optional<Long> sumQuantityByFilter(
            @Param("color") String color,
            @Param("cottonPercentage")Integer cottonPercentage,
            @Param("operator")String operator,
            @Param("cottonPercentageMin")Integer cottonPercentageMin,
            @Param("cottonPercentageMax")Integer cottonPercentageMax
    );
}