/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.gestionnaire;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author coulibaly barah
 */
@Stateless
public class GestionnaireUtilisateur {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName= "GestionBDPU")
    private EntityManager em;
    
    public void creerUtilisateursDeTest(){
        creerUtilisateur("cb","coulibaly","barah");
        creerUtilisateur("ya","yeo","arielle");
        creerUtilisateur("yg","yao","grace");
        creerUtilisateur("ne","N'guetta","emmanuel");
        
    }
    
    public Utilisateur creerUtilisateur(String login, String nom,String prenom){
        Utilisateur u = new Utilisateur(login, nom, prenom);
        em.persist(u);
        return u;
    }
   
    
     
    public Collection<Utilisateur> getAllUsers(){
        Query q = em.createQuery("select u from Utilisateur u");
        return q.getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
