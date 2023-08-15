package com.group.candoit.repository;


import com.group.candoit.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * " +
            "FROM candoit.location loc1 " +
            "INNER JOIN (" +
            "    SELECT name , MAX(modified_date) AS ultima_fecha " +
            "    FROM candoit.location " +
            "    GROUP BY name) loc2 ON loc1.name = loc2.name AND loc1.modified_date = loc2.ultima_fecha " +
            "ORDER BY loc1.name;", nativeQuery = true)
    List<Location> getLastLocations();
}
