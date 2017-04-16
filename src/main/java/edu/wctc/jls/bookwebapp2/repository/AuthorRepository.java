package edu.wctc.jls.bookwebapp2.repository;

import edu.wctc.jls.bookwebapp2.entity.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jlombardo
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable {
      @Query("SELECT a FROM Author a LEFT JOIN FETCH a.bookSet WHERE a.authorId = (:id)")
    public Author findByIdAndFetchBooksEagerly(@Param("id") Integer id);
    
    
    
    
    @Query("SELECT a.authorName FROM Author a")
    public Object[] findAllWithNameOnly();
}
