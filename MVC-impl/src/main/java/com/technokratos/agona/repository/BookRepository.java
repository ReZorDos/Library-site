package com.technokratos.agona.repository;

import com.technokratos.agona.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    @Modifying
    @Query("update BookEntity b set b.person = null where b.id = :id")
    void updatePersonIdToRelease(@Param("id") UUID id);

    @Modifying
    @Query("""
        update BookEntity b
        set b.person = (select p from PersonEntity p where p.id = :idPerson)
        where  b.id = :idBook
        """)
    void assignBookToPersonById(@Param("idBook") UUID idBook,
                                @Param("idPerson") UUID idPerson);

    @Modifying
    @Query("update BookEntity b set b.person = null where b.person.id = :personId")
    void releaseBooksByPersonId(@Param("personId") UUID personId);

    List<BookEntity> findAllByOrderByYearAsc(Pageable pageable);

    List<BookEntity> findAllByNameStartsWith(String name);

}
