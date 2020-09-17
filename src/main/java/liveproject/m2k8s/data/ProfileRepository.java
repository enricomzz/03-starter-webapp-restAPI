package liveproject.m2k8s.data;

import org.springframework.data.jpa.repository.JpaRepository;

import liveproject.m2k8s.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
  Profile findByUsername(String username);
}
