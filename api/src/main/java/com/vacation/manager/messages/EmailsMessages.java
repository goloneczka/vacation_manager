package com.vacation.manager.messages;


public class EmailsMessages {

    public final static String NOT_FOUND = "Enterprise not found";
    public final static String CREATE_FAILURE = "Nie udało się wysłać wiadomości z linkem aktywacyjnym";
    public final static String DELETE_FAILURE = "Enterprise could not be deleted";

    public static String ACTIVATE_MESSAGE(String email, int enterpriseId, String url){
        return "<div><h3>vacation-manager-app: aktywacja konta</h3>" +
                "<p>W celu dokonczenia rejestracji konta przejdz na podana strone " +
                "<a href='" + url + "/#/newAccount/" + email + "/" + enterpriseId+ "'> link activate " +
                "</a></p></div>";
    }

    public static String ACTIVATE_EMPLOYEE_MESSAGE(String email, int enterpriseId, String url, String passwd){
        return "<div><h3>vacation-manager-app: aktywacja konta pracownika</h3>" +
                "<p>W celu dokonczenia rejestracji konta przejdz na podana strone " +
                "<a href='" + url + "/#/newAccountEmployee/" + email + "/" + enterpriseId+ "'> link activate " +
                "</a>Twoje obecne haslo to: " + passwd + " zalecamy jego zmiane</p></div>";
    }
}
