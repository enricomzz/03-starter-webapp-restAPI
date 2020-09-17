package liveproject.m2k8s.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import liveproject.m2k8s.model.Profile;
import liveproject.m2k8s.service.ProfileService;

@RestController
@RequestMapping(path = "/profile")
public class ProfileController {

    private ProfileService profileService;

    // @Value("${images.directory:/tmp}")
    @Value("${TMP}/images")
    private String uploadFolder;

    @Value("classpath:ghost.jpg")
    private Resource defaultImage;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // REST methods

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> processRegistration(@Valid @RequestBody Profile profile) {
        profileService.save(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Profile> showProfile(@PathVariable String username) {
        Profile profile = profileService.getProfile(username);
        if (profile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }

    @PutMapping("/{username}")
    @Transactional
    public ResponseEntity<Profile> updateProfile(@Valid @RequestBody Profile profile) {
        profileService.update(profile);
        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }

}
