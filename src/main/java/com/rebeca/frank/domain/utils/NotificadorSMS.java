package com.rebeca.frank.domain.utils;

import com.rebeca.frank.domain.model.Veiculo;

public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(String message) {
        System.out.println("Notificação sendo enviada por SMS: " + message);
    }
}
