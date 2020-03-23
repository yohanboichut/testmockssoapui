package services;

import org.junit.*;
import org.junit.runners.MethodSorters;
import services.DTO.UtilisateurDTO;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGestionUtilisateursProxyImpl {


    GestionUtilisateursProxy gestionUtilisateursProxy;


    @Before
    public void setGestionUtilisateursProxy(){
        gestionUtilisateursProxy = new GestionUtilisateursProxyImpl();
    }

    @After
    public void resetWS(){
        gestionUtilisateursProxy.reset();
    }


    @Test(expected = ExceptionUtilisateurDejaEnregistre.class)
    public void testCreationKO() throws ExceptionUtilisateurDejaEnregistre {
        try {
            this.gestionUtilisateursProxy.creerUtilisateur("Boichut", "Yohan");
        } catch (ExceptionUtilisateurDejaEnregistre exceptionUtilisateurDejaEnregistre) {
            Assert.fail();
        }

        this.gestionUtilisateursProxy.creerUtilisateur("Boichut", "Yohan");
    }

    @Test
    public void testCreationOK() throws ExceptionUtilisateurDejaEnregistre {
        int id = this.gestionUtilisateursProxy.creerUtilisateur("Boichut", "Yohan");
        Assert.assertEquals(id,1);
    }


    @Test
    public void testGetAll() {
        try {
            this.gestionUtilisateursProxy.creerUtilisateur("Boichut", "Yohan");
            this.gestionUtilisateursProxy.creerUtilisateur("Moal", "Frederic");
        } catch (ExceptionUtilisateurDejaEnregistre exceptionUtilisateurDejaEnregistre) {
            Assert.fail();
        }


        List<UtilisateurDTO> utilisateurDTOList = this.gestionUtilisateursProxy.getUtilisateurs();

        Assert.assertEquals(utilisateurDTOList.size(),2);

    }

}
