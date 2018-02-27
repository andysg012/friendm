package com.example.friendm.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRelationshipRepository extends CrudRepository<UserRelationship, UserRelationshipPK> {

    public List<UserRelationship> findByUserId(Long userId);

    public List<UserRelationship> findByFriendId(Long friendId);
}
