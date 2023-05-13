package aiss.gitminer.repository;

import aiss.gitminer.model.Commit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommitRepository extends JpaRepository<Commit, String> {
    Page<Commit> findByAuthorEmail(String authorEmail, Pageable paging);

    Page<Commit> findByAuthorEmailAndTitleContaining(String email, Pageable paging, String title);
}
