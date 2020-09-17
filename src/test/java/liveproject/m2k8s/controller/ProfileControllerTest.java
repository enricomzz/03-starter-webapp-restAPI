package liveproject.m2k8s.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import liveproject.m2k8s.model.Profile;
import liveproject.m2k8s.service.ProfileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProfileControllerTest {

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProfileService profileService;

  Profile unsaved;
  Profile saved;

  @Before
  public void setup() throws Exception {
    unsaved = new Profile("cheese", "stilton", "Chuck", "Cheese", "cecheese@example.com");
    saved = new Profile(24L, "cheese", "stilton", "Chuck", "Cheese", "cecheese@example.com");
  }


  @Test
  public void getProfile_whenAppContainsProfile_returnProfileSuccessfully() throws Exception {

    when(profileService.getProfile(any())).thenReturn(saved);

    String savedJson = objectMapper.writeValueAsString(saved);

    mockMvc.perform(get("/profile/cheese"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(savedJson, true));
  }


}
