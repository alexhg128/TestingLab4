package mx.tec.lab.testdatabase;

import mx.tec.lab.testdatabase.entity.Dragon;
import mx.tec.lab.testdatabase.repository.DragonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DragonRepositoryTest {

    @Resource
    private DragonRepository dragonRepository;

    @BeforeEach
    public void init() {
        Dragon retrieveDragon = dragonRepository.findById(2L).orElse(null);
        if(retrieveDragon != null) {
            dragonRepository.deleteById(2L);
        }
        Dragon dragon = new Dragon(2, "Meraxes");
        dragonRepository.save(dragon);
        retrieveDragon = dragonRepository.findById(2L).orElse(null);
        assertEquals("Meraxes", retrieveDragon.getName());
    }

    @Test
    public void givenDragon_whenSave_thenRetrieveSame() {
        Dragon dragon = new Dragon(1, "Meraxes");
        dragonRepository.save(dragon);

        Dragon retrieveDragon = dragonRepository.findById(1L).orElse(null);
        assertEquals("Meraxes", retrieveDragon.getName());
    }

    @Test
    public void givenDragon_whenDelete_thenRetrieveNull()
    {
        dragonRepository.deleteById(2L);
        Dragon retrieveDeletedDragon = dragonRepository.findById(2L).orElse(null);
        assertNull(retrieveDeletedDragon, "The dragon wasn't deleted");
    }

    @Test
    public void givenDragon_whenUpdate_thenRetrieveUpdated() {
        Dragon retrieveDragon = dragonRepository.findById(2L).orElse(null);
        retrieveDragon.setName("Drogon");
        dragonRepository.save(retrieveDragon);
        Dragon retrieveUpdatedDragon = dragonRepository.findById(2L).orElse(null);
        assertEquals(retrieveUpdatedDragon.getName(), "Drogon");
    }

}
