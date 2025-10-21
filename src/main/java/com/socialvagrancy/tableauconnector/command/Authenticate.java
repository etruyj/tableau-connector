//===================================================================
// Authenticate.java
//      Description:
//          This class handles the authentication code with the tableau
//          server.
//
// Created by etruyj
//===================================================================

package com.socialvagrancy.tableauconnector.command;

import com.socialvagrancy.tableauconnector.api.TableauConnector;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Authenticate {
    private static Logger log = LoggerFactory.getLogger(Authenticate.class);

    public static void withTableau(String token_name, String secret, String site, String expiration, TableauConnector tableau) throws Exception {
        log.info("Authenticating with Tableau server");
        checkPatExpiration(expiration);

        tableau.authenticate(token_name, secret, site);

    }

    public static void checkPatExpiration(String expiration_date) throws Exception {
        log.info("Checking PAT token expiration date.");
        
        LocalDate expiration = LocalDate.parse(expiration_date);
        
        if(expiration.isBefore(LocalDate.now())) {
            log.error("PAT Authentication Token has expired. Please generate a new token on Tableau.");
            throw new Exception("PAT Authentication Token has expired. Please generate a new token on Tableau.");
        } else if(expiration.isBefore(LocalDate.now().plusMonths(1))) {
            log.warn("PAT Authentication Token will expire in the next month. Please generate a new token on Tableau.");
        }
    }
}
