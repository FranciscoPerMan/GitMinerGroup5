package aiss.gitminer.repository;

import aiss.gitminer.model.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommitRepository extends JpaRepository<Commit, String> {
    List<Commit> findByAuthorEmail(String authorEmail);
}
