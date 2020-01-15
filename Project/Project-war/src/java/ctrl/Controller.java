package ctrl;
import beans.DatabankVerbindingRemote;
import java.io.IOException;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Controller extends HttpServlet {

    @EJB private DatabankVerbindingRemote verbinding;
  
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
        
        session.setAttribute("Machines", verbinding.getMachines());
        
        if (request.getParameterMap().containsKey("actie")) {   //omdat login.jsp het veldje niet kan invullen, header maakt hier ook gebruik van
            switch (request.getParameter("actie")) {
                case "detail": {
                    //System.out.print(verbinding.getMachine("Detail opvragen van " + request.getParameter("serie")));   //Debug info
                    session.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));
                    view = request.getRequestDispatcher("detail.jsp");
                    break;
                }
                case "mijnReservaties":{
                    request.setAttribute("reservaties", verbinding.getReservaties(request.getUserPrincipal().getName()));
                    view = request.getRequestDispatcher("mijnReservaties.jsp");
                    break;
                }
                case "reservatie": {
                    request.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));
                    Calendar date = Calendar.getInstance();
                    date.set(Calendar.HOUR, 0);
                    date.set(Calendar.MINUTE, 0);
                    date.set(Calendar.SECOND, 0);
                    Calendar vandaag = (Calendar)date.clone();
                    List<Integer> dates = new ArrayList<Integer>();
                    
                    for (int i = 0; i < 7; i++)
                    {
                        dates.add(date.get(Calendar.DAY_OF_MONTH));
                        dates.add(date.get(Calendar.MONTH)+ 1);
                        dates.add(date.get(Calendar.YEAR));
                        
                        date.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    request.setAttribute("dates", dates);

                    List lijst = verbinding.getVrijeMomenten(request.getParameter("serie"),vandaag);
                    if (request.isUserInRole("Docent")) {
                        lijst.addAll(verbinding.getReservatieMomenten(request.getParameter("serie"),vandaag));
                    }
                    request.setAttribute("reservaties", lijst);
                    
                    view = request.getRequestDispatcher("reservatie.jsp");
                    break;
                }
                case "Reserveer":{
                    if(request.isUserInRole("Externe")){
                        request.setAttribute("serie", request.getParameter("serie"));
                        request.setAttribute("jaar", request.getParameter("jaar"));
                        request.setAttribute("maand", request.getParameter("maand"));
                        request.setAttribute("dag", request.getParameter("dag"));
                        request.setAttribute("uur", request.getParameter("uur"));
                        request.setAttribute("prijs", verbinding.getPrijs(request.getParameter("serie")));
                        view = request.getRequestDispatcher("beslis.jsp");
                        break;
                    }
                    verbinding.reserveer(request.getParameter("serie"),request.getParameter("jaar"), request.getParameter("maand"), request.getParameter("dag"), request.getParameter("uur"), request.getUserPrincipal().getName());
                    
                    request.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));
                    Calendar date = Calendar.getInstance();
                    date.set(Calendar.HOUR, 0);
                    date.set(Calendar.MINUTE, 0);
                    date.set(Calendar.SECOND, 0);
                    Calendar vandaag = (Calendar)date.clone();
                    List<Integer> dates = new ArrayList<Integer>();
                    
                    for (int i = 0; i < 7; i++)
                    {
                        dates.add(date.get(Calendar.DAY_OF_MONTH));
                        dates.add(date.get(Calendar.MONTH)+ 1);
                        dates.add(date.get(Calendar.YEAR));
                        date.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    request.setAttribute("dates", dates);

                    List lijst = verbinding.getVrijeMomenten(request.getParameter("serie"),vandaag);
                    if (request.isUserInRole("Docent")) {
                        lijst.addAll(verbinding.getReservatieMomenten(request.getParameter("serie"),vandaag));
                    }
                    request.setAttribute("reservaties", lijst);
                    
                    view = request.getRequestDispatcher("reservatie.jsp");
                    break;
                }
                case "goed":{
                    verbinding.reserveer(request.getParameter("serie"),request.getParameter("jaar"), request.getParameter("maand"), request.getParameter("dag"), request.getParameter("uur"), request.getUserPrincipal().getName());
                    request.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));
                    Calendar date = Calendar.getInstance();
                    date.set(Calendar.HOUR, 0);
                    date.set(Calendar.MINUTE, 0);
                    date.set(Calendar.SECOND, 0);
                    Calendar vandaag = (Calendar)date.clone();
                    List<Integer> dates = new ArrayList<Integer>();
                    
                    for (int i = 0; i < 7; i++)
                    {
                        dates.add(date.get(Calendar.DAY_OF_MONTH));
                        dates.add(date.get(Calendar.MONTH)+ 1);
                        dates.add(date.get(Calendar.YEAR));
                        date.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    request.setAttribute("dates", dates);

                    List lijst = verbinding.getVrijeMomenten(request.getParameter("serie"),vandaag);
                    if (request.isUserInRole("Docent")) {
                        lijst.addAll(verbinding.getReservatieMomenten(request.getParameter("serie"),vandaag));
                    }
                    request.setAttribute("reservaties", lijst);
                    
                    view = request.getRequestDispatcher("reservatie.jsp");
                    break;
                }
                case "terug":{
                    request.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));
                    Calendar date = Calendar.getInstance();
                    date.set(Calendar.HOUR, 0);
                    date.set(Calendar.MINUTE, 0);
                    date.set(Calendar.SECOND, 0);
                    Calendar vandaag = (Calendar)date.clone();
                    List<Integer> dates = new ArrayList<Integer>();
                    
                    for (int i = 0; i < 7; i++)
                    {
                        dates.add(date.get(Calendar.DAY_OF_MONTH));
                        dates.add(date.get(Calendar.MONTH)+ 1);
                        dates.add(date.get(Calendar.YEAR));
                        date.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    request.setAttribute("dates", dates);

                    List lijst = verbinding.getVrijeMomenten(request.getParameter("serie"),vandaag);
                    if (request.isUserInRole("Docent")) {
                        lijst.addAll(verbinding.getReservatieMomenten(request.getParameter("serie"),vandaag));
                    }
                    request.setAttribute("reservaties", lijst);
                    
                    view = request.getRequestDispatcher("reservatie.jsp");
                    break;
                }
                case "VoegMomentToe":{
                    //System.out.println("Moment toevoegen voor " + request.getParameter("serie")); //Debug info
                    request.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));
                    view = request.getRequestDispatcher("NieuwMoment.jsp");
                    break;
                }
                case "Toegevoegd":{
                    verbinding.voegVrijToe(request.getParameter("serie"), request.getParameter("dag"), request.getParameter("maand"), request.getParameter("jaar"), request.getParameter("uur"));
                    request.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));
                    Calendar date = Calendar.getInstance();
                    date.set(Calendar.HOUR, 0);
                    date.set(Calendar.MINUTE, 0);
                    date.set(Calendar.SECOND, 0);
                    Calendar vandaag = (Calendar)date.clone();
                    List<Integer> dates = new ArrayList<Integer>();
                    
                    for (int i = 0; i < 7; i++)
                    {
                        dates.add(date.get(Calendar.DAY_OF_MONTH));
                        dates.add(date.get(Calendar.MONTH)+ 1);
                        dates.add(date.get(Calendar.YEAR));
                        date.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    request.setAttribute("dates", dates);

                    List lijst = verbinding.getVrijeMomenten(request.getParameter("serie"),vandaag);
                    if (request.isUserInRole("Docent")) {
                        lijst.addAll(verbinding.getReservatieMomenten(request.getParameter("serie"),vandaag));
                    }
                    request.setAttribute("reservaties", lijst);
                    
                    view = request.getRequestDispatcher("reservatie.jsp");
                    break;
                }
                case "bewerkMachine": {
                    //System.out.print("Bewerk machine: " + verbinding.getMachine(request.getParameter("serie")));   //Debug info
                    session.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));
                    view = request.getRequestDispatcher("bewerkMachine.jsp");
                    break;
                }
                case "voegMachineToe": {
                    view = request.getRequestDispatcher("voegMachineToe.jsp");
                    break;
                }
                case "new": {
                    if (verbinding.bestaatSerie(request.getParameter("serienummer"))) {
                        System.out.print("Serienummer al in gebruik");
                        view = request.getRequestDispatcher("voegMachineToe.jsp");
                    }
                    else {
                        verbinding.newMachine(request.getParameter("serienummer"), request.getParameter("opleiding"), request.getParameter("omschrijving"), request.getParameter("naam"), request.getParameter("aankoopprijs"), request.getParameter("huurprijs"), request.getParameter("lokaal"));
                        session.setAttribute("machine", verbinding.getMachine(request.getParameter("serienummer")));
                        view = request.getRequestDispatcher("detail.jsp");
                    }
                    break;
                }
                case "save": {
                    verbinding.updateMachine(request.getParameter("serie"), request.getParameter("opleiding"), request.getParameter("omschrijving"), request.getParameter("naam"), request.getParameter("aankoopprijs"), request.getParameter("huurprijs"), request.getParameter("lokaal"));
                    session.setAttribute("machine", verbinding.getMachine(request.getParameter("serie")));
                    view = request.getRequestDispatcher("detail.jsp");
                    break;
                }
                case "logout":{
                    session.invalidate();
                    request.logout();
                    view = request.getRequestDispatcher("login.jsp");
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