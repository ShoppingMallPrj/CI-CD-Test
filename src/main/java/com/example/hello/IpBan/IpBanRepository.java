package com.example.hello.IpBan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IpBanRepository extends JpaRepository<IpBan,Integer > {

    boolean existsByIp(String ip);
}
