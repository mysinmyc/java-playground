package foo.bar.backend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {

    @Query("select c from Contact c where c.name like %?1%")
    List<Contact> findByNameLike(String name);
}
