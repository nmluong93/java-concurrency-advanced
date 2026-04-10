package com.luong.sec09;

import com.luong.sec09.controller.DocumentController;
import com.luong.sec09.security.scopedvalue.AuthenticationService;
import com.luong.sec09.security.scopedvalue.SecurityContextHolder;

public class Lec07DocumentAccessWithScopedValue {

    private static final DocumentController documentController = new DocumentController(SecurityContextHolder::getContext);

    static void main() {

        documentAccessWorkflow(3, "password");

    }

    private static void documentAccessWorkflow(Integer userId, String password){
        AuthenticationService.loginAndExecute(userId, password, () -> {
            documentController.read();
            AuthenticationService.runAsAdmin(() -> {
                documentController.edit();
                documentController.delete();
            });
            documentController.delete();
        });
    }

}
