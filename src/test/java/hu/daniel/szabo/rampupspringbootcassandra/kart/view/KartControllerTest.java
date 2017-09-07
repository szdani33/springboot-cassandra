package hu.daniel.szabo.rampupspringbootcassandra.kart.view;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import hu.daniel.szabo.rampupspringbootcassandra.kart.domain.Kart;
import hu.daniel.szabo.rampupspringbootcassandra.kart.domain.KartRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class KartControllerTest {

    @Mock
    private KartRepository kartRepository;

    private MockMvc mockMvc;
    private Gson gson = new Gson();

    @Before
    public void setUp() {
        KartController underTest = new KartController(kartRepository);
        mockMvc = standaloneSetup(underTest).build();
    }

    @Test
    public void kartViewHasValidationErrorHttpStatusCode400ShouldBeReturned() throws Exception {
        KartView invalidKartView = new KartView();

        mockMvc.perform(put("/kart")
                .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(invalidKartView))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        Mockito.verifyNoMoreInteractions(kartRepository);
    }

    @Test
    public void kartViewHasNoValidationErrorHttpStatusCode201ShouldBeReturned() throws Exception {
        KartView validKartView = new KartView();
        validKartView.setNumber(1);
        validKartView.setEngineSize(50);

        mockMvc.perform(put("/kart")
            .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(validKartView))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
        Mockito.verify(kartRepository).save(Mockito.any(Kart.class));
    }
}