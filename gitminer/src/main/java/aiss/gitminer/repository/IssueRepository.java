package aiss.gitminer.repository;

import aiss.gitminer.model.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, String> {
    Page<Issue> findByAuthorId(Pageable paging, String authorId);

    Page<Issue> findByState(Pageable paging, String state);

    Page<Issue> findByAuthorIdAndState(Pageable paging, String authorId, String state);

    Page<Issue> findByTitleContaining(Pageable paging, String title);

    Page<Issue> findByStateAndTitleContaining(Pageable paging, String state, String title);

    Page<Issue> findByAuthorIdAndTitleContaining(Pageable paging, String authorId, String title);

    Page<Issue> findByAuthorIdAndStateAndTitleContaining(Pageable paging, String authorId, String state, String title);
}
