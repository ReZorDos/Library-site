package com.technokratos.agona.repository;

import com.technokratos.agona.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {

    @Query("select p from PersonEntity p left join fetch p.books where p.id = :id")
    Optional<PersonEntity> findPersonWithBooks(@Param("id") UUID id);

}
