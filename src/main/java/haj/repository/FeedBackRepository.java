package haj.repository;

import haj.model.FeedBack;
import haj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {

    List<FeedBack> findByUsername(String username);
    List<FeedBack> findByWebFileNumber(String webFileNumber);

}