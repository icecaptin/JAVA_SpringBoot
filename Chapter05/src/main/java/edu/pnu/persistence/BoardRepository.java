package edu.pnu.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleLikeAndCntGreaterThan(String title, Long cnt);

    List<Board> findByCntBetweenOrderBySeqAsc(Long less, Long greater);

    List<Board> findByTitleLikeOrContentLikeOrderBySeqDesc(String title, String content);

    List<Board> findByTitleLike(String title, Pageable paging);

    Page<Board> findAll(Pageable paging);

    List<Board> boardRepositoryTest(String string);

    List<Board> findByTitleContainingOrContentContaining(String string, String string2);

    @Query("select b from Board b where b.title like %:searchKeyword% order by b.seq DESC")
    List<Board> testQueryAnnotationTest2(@Param("searchKeyword") String searchKeyword);

    @Query("select b.seq, b.title, b.writer, b.createDate from Board b " +
            "where b.title like %:searchKeyword% order by b.seq DESC")
    List<Object[]> queryAnnotationTest3(@Param("searchKeyword") String searchKeyword);

    @Query(value = "select seq, title, writer, createDate from board where title like '%' || :sk || '%' order by seq DESC", nativeQuery = true)
    List<Object[]> queryAnnotationTest4(@Param("sk") String searchKeyword);

    @Query("select b from Board b order by b.seq DESC")
    List<Board> queryAnnotationTest5(Pageable paging);
}
