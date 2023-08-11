package edu.pnu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.entity.BusanComment;

@Repository
public interface BusanCommentRepository extends JpaRepository<BusanComment, Integer> {

	BusanComment findByTypeAndTypeId(String type, String typeid);
//    BusanComment findByTypeAndPostid(String type, String postid);
}
