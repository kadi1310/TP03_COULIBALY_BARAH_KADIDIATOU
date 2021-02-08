/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilisateurs.gestionnaire.GestionnaireUtilisateur;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author coulibaly barah
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {
    @EJB
    private GestionnaireUtilisateur gestionnaireUtilisateur;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String login = request.getParameter("login");
        String forwardTo = "";
        String message = "";
        
        if(action != null){
            if(action.equals("ListerLesUtilisateurs")){
                Collection<Utilisateur> liste = gestionnaireUtilisateur.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=ListerLesUtilisateurs";
                message = "Liste des utilisateurs";
                
            }
            else if (action.equals("creerUtilisateursDeTest")){
                gestionnaireUtilisateur.creerUtilisateursDeTest();
                Collection<Utilisateur> liste = gestionnaireUtilisateur.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            }
            else if (action.equals("creerUnUtilisateur")) {
                gestionnaireUtilisateur.creerUtilisateur(login,nom,prenom);
                Collection<Utilisateur> liste = gestionnaireUtilisateur.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            }    
            
            else{
                forwardTo = "index.jsp?action=todo";
                message = "La fonctionnalité pour les paramètre" + action + "est à implémenter !";
                
                
            }
        }
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message);
        dp.forward(request, response);
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletUsers</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletUsers at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
