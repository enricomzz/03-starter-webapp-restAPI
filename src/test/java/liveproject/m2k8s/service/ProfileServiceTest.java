package liveproject.m2k8s.service;

import liveproject.m2k8s.model.Profile;
import liveproject.m2k8s.data.ProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {
    private ProfileRepository profileRepository = Mockito.mock(ProfileRepository.class);
    private ProfileService profileService;

    @Before
    public void setup() {
        profileService = new ProfileService(profileRepository);
        Profile zasu = new Profile(1L, "Zasu", "Pitts", "zasupitts", "changeme", "zasu@hollywood.com");
        profileService.save(zasu);
        when(profileRepository.findByUsername("zasupitts")).thenReturn(zasu);
    }

    @Test
    public void test_getProfile() {
        Profile zasupitts = profileService.getProfile("zasupitts");
        assertEquals("Zasu", zasupitts.getFirstName());
    }
}
