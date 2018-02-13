package com.wso.ordem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wso.ordem.model.Ordem;

public interface Ordens extends JpaRepository<Ordem,Long > {

}
