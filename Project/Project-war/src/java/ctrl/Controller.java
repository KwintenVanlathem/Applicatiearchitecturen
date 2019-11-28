/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import beans.DatabankVerbindingRemote;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author r0661567
 */
public class Controller extends HttpServlet {

    @EJB private DatabankVerbindingRemote verbinding;
    
   // public void init() {
    //    ServletContext applicatie;
      //  applicatie = getServletContext();   //voorlopig nutteloos, eventueel later??
    //}
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
        RequestDispatcher view;
        HttpSession session = request.getSession();
        session.setAttribute("Username", request.getUserPrincipal().getName());
        if (request.isUserInRole("Docent")) {
            session.setAttribute("Rol", "Docent");
            session.setAttribute("docentopleiding", verbinding.getDocentOpleiding(request.getUserPrincipal().getName()));
        }
        else if (request.isUserInRole("Student")) {
            session.setAttribute("Rol", "Student");
        }
        else if (request.isUserInRole("Externe")) {
            session.setAttribute("Rol", "Externe");
        }
        
        List machines = verbinding.getMachines();
        session.setAttribute("Machines", machines);    //eerder response dan session??
        
        if (request.getParameterMap().containsKey("actie")) {   //omdat login.jsp het veldje nie kan invullen, header maakt hier ook gebruik van
            switch (request.getParameter("actie")) {
                case "detail": {
                    session.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));    //eerder response dan session??
                   // System.out.print(verbinding.getMachine(request.getParameter("serie")));
                    view = request.getRequestDispatcher("detail.jsp");
                    break;
                }
                case "bewerkMachine": {
                    session.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));    //eerder response dan session??
                   // System.out.print(verbinding.getMachine(request.getParameter("serie")));
                    view = request.getRequestDispatcher("bewerkMachine.jsp");
                    break;
                }
                case "voegMachineToe": {
                    view = request.getRequestDispatcher("voegMachineToe.jsp");
                    break;
                }
                case "new": {
                    //nog checken dat serienummer wel beschikbaar is
                    verbinding.newMachine(request.getParameter("serienummer"), request.getParameter("opleiding"), request.getParameter("omschrijving"), request.getParameter("naam"), request.getParameter("aankoopprijs"), request.getParameter("huurprijs"), request.getParameter("lokaal"));
                    session.setAttribute("machine", verbinding.getMachine(request.getParameter("serienummer")));    //eerder response dan session??
                    view = request.getRequestDispatcher("detail.jsp");
                    break;
                }
                case "save": {
                    verbinding.updateMachine(request.getParameter("serie"), request.getParameter("opleiding"), request.getParameter("omschrijving"), request.getParameter("naam"), request.getParameter("aankoopprijs"), request.getParameter("huurprijs"), request.getParameter("lokaal"));
                    session.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));    //eerder response dan session??
                    view = request.getRequestDispatcher("detail.jsp");
                    break;
                }
                default: {
                    view = request.getRequestDispatcher("overzicht.jsp");
                    break;
                }
            }
        }
        else {
            view = request.getRequestDispatcher("overzicht.jsp");
        }
        
        view.forward(request, response);
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
