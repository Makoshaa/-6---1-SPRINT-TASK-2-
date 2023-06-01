package com.sprinttask.magzhan.repository;

import com.sprinttask.magzhan.db.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<ApplicationRequest,Long> {

    List<ApplicationRequest> findAllByHandled(boolean handled);
}
